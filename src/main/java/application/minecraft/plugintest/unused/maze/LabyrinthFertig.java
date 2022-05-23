package application.minecraft.plugintest.unused.maze;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//Hallo

public class LabyrinthFertig {
    private Integer pointX; //Eine Markierung zum Platzieren und Löschen von Blöcken.
    private Integer pointY;
    private Integer width; //Breite
    private Integer height; //Höhe
    private Object[][] map; //Array zum Speichern der Karte
    public LabyrinthFertig(int w, int h) { //Konstruktor
        width = w;
        height = h;
        if (w % 2 != 0 && h % 2 != 0 && 5 <= w && 5 <= h) {
            map = new Object[width][height];
            make();     //make Funktion wird aufgerufen
        } else {
            System.out.println("Sowohl die Höhe und die Breite müssen mindestens größer gleich fünf sein");
        }
    }

    int randomPos(int muki) {   //x,Gibt ungerade Zufallskoordinaten für beide y-Koordinaten zurück
        int result = 1 + 2 * (int) Math.floor((Math.random() * (muki - 1)) / 2);
        return result;
    }

    public void make() { //Erstellt eine "Karte" vom Gebiet

        pointX = randomPos(width);
        pointY = randomPos(height);

        for (int y = 0; y < height; y++) { //Komplettes Gebiet (Höhe x Breite) wird mit Wänden ausgefüllt.
            for (int x = 0; x < width; x++) {
                map[x][y] = 0;              //von 1 zu 0    zweidimensionales Array map wird komplett mit nullen beschrieben (Wände)
            }
        }
        map[pointX][pointY] = 1;            //von 0 zu 1    an zufälligem Punkt in map wird eine 1 geschrieben (Wand entfernt)
        dig();      //dig Funktion wird aufgerufen

    }

    private void dig() {
        if (isAbleContinueDig() && map[pointX][pointY].equals(1)) {     //von 0 zu 1    isAbleContinueDig wird aufgerufen und es wird überprüft ob an der Stelle schon 1 ist (Weg)
            map[pointX][pointY] = 1;                                //von 0 zu 1
            int direction = (int) Math.floor(Math.random() * 4);    //zufällige Richtung zum weiter graben wird ausgewählt (4 Möglichkeiten)
            switch (direction) {
                case 0:
                    if (pointY != 1) {
                        if (map[pointX][pointY - 2].equals(0)) {        //1 zu 0
                            map[pointX][pointY - 1] = 1;                //0 zu 1
                            pointY -= 2;
                            break;//u
                        }
                    }
                case 1:
                    if (pointY != height - 2) {
                        if (map[pointX][pointY + 2].equals(0)) {           //1 zu 0
                            map[pointX][pointY + 1] = 1;                   // 0 zu 1
                            pointY += 2;
                            break;//d
                        }
                    }
                case 2:
                    if (pointX != 1) {
                        if (map[pointX - 2][pointY].equals(0)) {        //1 zu 0
                            map[pointX - 1][pointY] = 1;                //0 zu 1
                            pointX -= 2;
                            break;//l
                        }
                    }
                case 3:
                    if (pointX != width - 2) {
                        if (map[pointX + 2][pointY].equals(0)) {            //1 zu 0
                            map[pointX + 1][pointY] = 1;                    //0 zu 1
                            pointX += 2;
                            break;//r
                        }
                    }
            }
            map[pointX][pointY] = 1;                                    //0 zu 1
            dig();
        } else if (isAbleDig()) {
            pointX = randomPos(width);
            pointY = randomPos(height);
            dig();
        }

    }

    private boolean isAbleDig() { //Schaut nach, ob es noch einen Platz zum Graben gibt
        boolean result;
        int cnt = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x % 2 != 0 && y % 2 != 0) {

                    if (!map[x][y].equals(1)) {         //0 zu 1
                        cnt++;
                    }
                }
            }
        }
        if (cnt == 0) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    private boolean isAbleContinueDig() {   //Stellt fest, ob noch Platz zum Graben in alle Richtungen vorhanden ist

        if (pointY != 1) {
            if (map[pointX][pointY - 2].equals(0)) {         // Überprüfe ob von pointY ausgehend noch ein Block ist        //1 zu 0
                return true;
            }
        }
        if (pointY != height - 2) {
            if (map[pointX][pointY + 2].equals(0)) {        //1 zu 0
                return true;
            }
        }
        if (pointX != 1) {
            if (map[pointX - 2][pointY].equals(0)) {        //1 zu 0
                return true;
            }
        }
        if (pointX != width - 2) {
            if (map[pointX + 2][pointY].equals(0)) {        //1 zu 0
                return true;
            }
        }
        return false;
    }

    public void build() {
        try {
            Path labyrinth = Paths.get("C:\\Users\\pahll\\MinecraftProject\\world\\datapacks\\FirstTry\\data\\maze\\functions\\labyrinth9.mcfunction");

            //"./target/classes/application/minecraft/plugintest/labyrinth.mcfunction"
            // Datei anlegen, wenn sie noch nicht existiert
            if (!Files.exists(labyrinth))
                Files.createFile(labyrinth);
            else {
                BufferedWriter cleaner = Files.newBufferedWriter(labyrinth);
                cleaner.close();
            }
            //Boden des Labyrinths erzeugen und den Kasten in dem Sich das Labyrinth befindet durch Luft ersetzen, damit es nicht in anderen Struktuern hineingeneriert
            // Und Aus- und Eingang erschaffen
            BufferedWriter meinWriter = Files.newBufferedWriter(labyrinth, StandardOpenOption.APPEND);
            //meinWriter.write("fill ~" + (height-1) + " ~ ~" + (width-1) + " ~0 ~-1 ~0 dirt" + "\n");    //Vom Spieler ausgehend
            //meinWriter.write("fill ~" + (height-1) + " ~ ~" + (width-1) + " ~0 ~2 ~0 air" + "\n");      //Vom Spieler ausgehend
            meinWriter.write("fill " + (height-1) + " 9 " + (width-1) + " 0 10 0 dirt" + "\n");       //Von 0/0 ausgehend
            meinWriter.write("fill " + (height-1) + " 10 " + (width-1) + " 0 12 0 air" + "\n");         //Von 0/0 ausgehend
            meinWriter.close();


            for (int y = 0; y < map[0].length; y++) {

                System.out.println("");
                for (int x = 0; x < map.length; x++) {

                    meinWriter = Files.newBufferedWriter(labyrinth, StandardOpenOption.APPEND);
                    if (map[x][y].equals(0)) {                                                                      //1 zu 0
                        //meinWriter.write("fill ~" + x + " ~ ~" + y + " ~" + x + " ~2 ~" + y + " stone" + "\n");       //Vom Spieler ausgehend
                        meinWriter.write("fill " + x + " 10 " + y + " " + x + " 12 " + y + " stone" + "\n");     //Von 0/0 ausgehend
                        meinWriter.close();
                        System.out.print("##");
                    } else {
                        //meinWriter.write("fill ~" + x + " ~ ~" + y + " ~" + x + " ~2 ~" + y + " air" + "\n");         //Vom Spieler ausgehend
                        meinWriter.write("fill " + x + " 10 " + y + " " + x + " 12 " + y + " air" + "\n");       //Von 0/0 ausgehend
                        meinWriter.close();
                        System.out.print("  ");
                    }
                    // Macht Ein- und Ausgang
                    meinWriter = Files.newBufferedWriter(labyrinth, StandardOpenOption.APPEND);
                    //meinWriter.write("fill ~" + 0 + " ~ ~" + 1 + " ~" + 0 + " ~2 ~" + 1 + " air" + "\n");                                       //Vom Spieler ausgehend
                    //meinWriter.write("fill ~" + (width-1) + " ~ ~" + (height-2) + " ~" + (width-1) + " ~2 ~" + (height-2) + " air" + "\n");     //Vom Spieler ausgehend
                    meinWriter.write("fill 0 10 1 0 12 1 air" + "\n");                                                                          //Von 0/0 ausgehend
                    meinWriter.write("fill " + (width-1) + " 10 " + (height-2) + " " + (width-1) + " 12 " + (height-2) + " air" + "\n");         //Von 0/0 ausgehend
                    meinWriter.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object[][] getMaze() {
        return map;
    }

    public static void main(String[] args) {
        LabyrinthFertig maze=new LabyrinthFertig(21,21);
        maze.build();
        System.out.println(maze.getMaze());
    }

}
