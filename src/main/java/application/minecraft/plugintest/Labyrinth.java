package application.minecraft.plugintest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class Labyrinth {
    private Integer pointX; //Eine Markierung zum Platzieren und Löschen von Blöcken.
    private Integer pointY; //Eine Markierung zum Platzieren und Löschen von Blöcken.
    private Integer width; //Breite
    private Integer height; //Höhe
    private Object[][] map; //Array zum Speichern der Karte

    public Labyrinth(int w, int h) { //Konstruktor
        width = w;
        height = h;
        if (w % 2 != 0 && h % 2 != 0 && 5 <= w && 5 <= h) { //nur mit ungerader Höhe und Breite und jeweils größer als 5 möglich
            map = new Object[width][height];
            makeMap();     //makeMap Funktion wird aufgerufen
        } else {
            System.out.println("Sowohl die Höhe und die Breite müssen mindestens größer gleich fünf sein und ungerade");
        }
    }

    int randomPos(int muki) {   //Gibt ungerade Zufallszahl zurück
        int result = 1 + 2 * (int) Math.floor((Math.random() * (muki - 1)) / 2);
        return result;
    }

    private void makeMap() { //Erstellt eine "Karte" vom Gebiet

        pointX = randomPos(width);
        pointY = randomPos(height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[x][y] = 0;              //von 1 zu 0    zweidimensionales Array map wird komplett mit nullen beschrieben (Wände)
                //System.out.println(map[x][y]);
            }
        }
        map[pointX][pointY] = 1;            //von 0 zu 1    an zufälligem Punkt in map wird eine 1 geschrieben (Wand entfernt)
        dig();      //dig Funktion wird aufgerufen

    }

    private void dig() {
        if (isAbleContinueDig() && map[pointX][pointY].equals(1)) {     //Wenn isAbleContinueDig und an der Stelle schon 1 ist (Weg)
            map[pointX][pointY] = 1;
            int direction = (int) Math.floor(Math.random() * 4);    //zufällige Richtung zum weiter graben wird ausgewählt (4 Möglichkeiten)
            switch (direction) {
                case 0:
                    if (pointY != 1) {
                        if (map[pointX][pointY - 2].equals(0)) {
                            map[pointX][pointY - 1] = 1;                //Wand wird entfernt
                            pointY -= 2;        //weiter gehen auf nächsten Punkt
                            break;//u
                        }
                    }
                case 1:
                    if (pointY != height - 2) {                     //nur wenn man nicht Labyrinthgrenzen verlässt
                        if (map[pointX][pointY + 2].equals(0)) {
                            map[pointX][pointY + 1] = 1;                   //Wand wird entfernt
                            pointY += 2;        //weiter gehen auf nächsten Punkt
                            break;//d
                        }
                    }
                case 2:
                    if (pointX != 1) {
                        if (map[pointX - 2][pointY].equals(0)) {
                            map[pointX - 1][pointY] = 1;                //Wand wird entfernt
                            pointX -= 2;        //weiter gehen auf nächsten Punkt
                            break;//l
                        }
                    }
                case 3:
                    if (pointX != width - 2) {                      //nur wenn man nicht Labyrinthgrenzen verlässt
                        if (map[pointX + 2][pointY].equals(0)) {
                            map[pointX + 1][pointY] = 1;                    //Wand wird entfernt
                            pointX += 2;        //weiter gehen auf nächsten Punkt
                            break;//r
                        }
                    }
            }
            map[pointX][pointY] = 1;
            dig();
        } else if (isAbleDig()) {               //wenn isAbleDig noch true
            pointX = randomPos(width);          //neuen zufälligen Punkt in der Fläche auswählen
            pointY = randomPos(height);
            dig();
        }

    }

    private boolean isAbleDig() { //Schaut nach, ob es noch einen Platz zum Graben gibt
        boolean result;
        int cnt = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x % 2 != 0 && y % 2 != 0) {     //jeder zweite Block wird überprüft (Schachbrett)

                    if (!map[x][y].equals(1)) {         //Wenn nicht Gang
                        cnt++;
                    }
                }
            }
        }
        if (cnt == 0) {
            result = false;     //weitergraben nicht mehr möglich
        } else {
            result = true;      //weitergraben möglich
        }
        return result;
    }

    private boolean isAbleContinueDig() {   //Stellt fest, ob noch Platz zum Graben in alle Richtungen vorhanden ist

        if (pointY != 1) {                                  //damit man nicht ausenwand durchbricht
            if (map[pointX][pointY - 2].equals(0)) {         // Überprüfe ob von pointY ausgehend noch ein Block ist (nach "hinten")
                return true;
            }
        }
        if (pointY != height - 2) {                             //damit man nicht ausenwand durchbricht
            if (map[pointX][pointY + 2].equals(0)) {        //Überprüfe ob von pointY ausgehend noch ein Block ist (nach "vorne")
                return true;
            }
        }
        if (pointX != 1) {                                  //damit man nicht ausenwand durchbricht
            if (map[pointX - 2][pointY].equals(0)) {        //Überprüfe ob von pointX ausgehend noch ein Block ist (nach "hinten")
                return true;
            }
        }
        if (pointX != width - 2) {                             //damit man nicht ausenwand durchbricht
            if (map[pointX + 2][pointY].equals(0)) {        //Überprüfe ob von pointY ausgehend noch ein Block ist (nach "vorne")
                return true;
            }
        }
        return false;
    }

    public void build() {
        try {
            Path labyrinth = Paths.get("C:\\Users\\pahll\\MinecraftProject\\world\\datapacks\\FirstTry\\data\\maze\\functions\\labyrinth.mcfunction");
            // Datei anlegen, wenn sie noch nicht existiert
            if (!Files.exists(labyrinth))
                Files.createFile(labyrinth);
            else {
                BufferedWriter cleaner = Files.newBufferedWriter(labyrinth);
                cleaner.close();
            }

            Path labyrinthtxt = Paths.get("C:\\Users\\pahll\\MinecraftProject\\world\\datapacks\\FirstTry\\data\\maze\\functions\\labyrinth.txt");
            // Datei anlegen, wenn sie noch nicht existiert
            if (!Files.exists(labyrinthtxt))
                Files.createFile(labyrinthtxt);
            else {
                BufferedWriter cleaner = Files.newBufferedWriter(labyrinthtxt);
                cleaner.close();
            }

            //Boden des Labyrinths erzeugen und den Kasten in dem Sich das Labyrinth befindet durch Luft ersetzen, damit es nicht in anderen Struktuern hineingeneriert
            // Und Aus- und Eingang erschaffen
            BufferedWriter meinWriter = Files.newBufferedWriter(labyrinth, StandardOpenOption.APPEND);
            //meinWriter.write("fill ~" + (height-1) + " ~ ~" + (width-1) + " ~0 ~-1 ~0 minecraft:chiseled_sandstone" + "\n");    //Vom Spieler ausgehend
            //meinWriter.write("fill ~" + (height-1) + " ~ ~" + (width-1) + " ~0 ~2 ~0 air" + "\n");      //Vom Spieler ausgehend
            //meinWriter.write("tp @p 0 10 1" + "\n");
            meinWriter.write("fill " + (height - 1) + " -61 " + (width - 1) + " 0 -60 0 dirt" + "\n");       //Von 0/0 ausgehend
            meinWriter.write("fill " + (height - 1) + " -60 " + (width - 1) + " 0 -58 0 air" + "\n");         //Von 0/0 ausgehend
            meinWriter.close();

            for (int y = 0; y < map[0].length; y++) {

                System.out.println("");
                for (int x = 0; x < map.length; x++) {
                    meinWriter = Files.newBufferedWriter(labyrinth, StandardOpenOption.APPEND);

                    BufferedWriter meinWritertxt = Files.newBufferedWriter(labyrinthtxt, StandardOpenOption.APPEND);
                    meinWritertxt.write((int) map[x][y] + "\n");
                    meinWritertxt.close();

                    if (map[x][y].equals(0)) {
                        //meinWriter.write("fill ~" + x + " ~ ~" + y + " ~" + x + " ~2 ~" + y + " minecraft:moss_block" + "\n");       //Vom Spieler ausgehend
                        meinWriter.write("fill " + x + " -60 " + y + " " + x + " -58 " + y + " stone" + "\n");     //Von 0/0 ausgehend
                        meinWriter.close();
                        System.out.print("##");
                        //System.out.println(map[x][y]);
                    } else {
                        //meinWriter.write("fill ~" + x + " ~ ~" + y + " ~" + x + " ~2 ~" + y + " air" + "\n");         //Vom Spieler ausgehend
                        meinWriter.write("fill " + x + " -60 " + y + " " + x + " -58 " + y + " air" + "\n");       //Von 0/0 ausgehend
                        meinWriter.close();
                        System.out.print("  ");
                        //System.out.println(map[x][y]);
                    }
                    // Macht Ein- und Ausgang
                    meinWriter = Files.newBufferedWriter(labyrinth, StandardOpenOption.APPEND);
                    //meinWriter.write("fill ~" + 0 + " ~ ~" + 1 + " ~" + 0 + " ~2 ~" + 1 + " air" + "\n");                                       //Vom Spieler ausgehend
                    //meinWriter.write("fill ~" + (width-1) + " ~ ~" + (height-2) + " ~" + (width-1) + " ~2 ~" + (height-2) + " air" + "\n");     //Vom Spieler ausgehend
                    meinWriter.write("fill 0 -60 1 0 -58 1 air" + "\n");                                                                          //Von 0/0 ausgehend
                    meinWriter.write("fill " + (width - 1) + " -60 " + (height - 2) + " " + (width - 1) + " -58 " + (height - 2) + " air" + "\n");         //Von 0/0 ausgehend
                    meinWriter.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
