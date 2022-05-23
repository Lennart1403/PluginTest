package application.minecraft.plugintest.commands;


import application.minecraft.plugintest.Labyrinth;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class MazeGenCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            Labyrinth maze=new Labyrinth(21,21);

            maze.build();
            getServer().reload();

        }
        return true;
    }
}
