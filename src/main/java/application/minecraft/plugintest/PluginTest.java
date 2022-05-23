package application.minecraft.plugintest;

import application.minecraft.plugintest.commands.GodCommand;
import application.minecraft.plugintest.commands.MazeGenCommand;
import application.minecraft.plugintest.commands.MobCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class PluginTest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        // Plugin startup logic
        //this.getServer().getPluginManager().registerEvents(this, this);
        getCommand("god").setExecutor(new GodCommand());
        getCommand("chargemob").setExecutor(new MobCommand());
        getCommand("mazegen").setExecutor(new MazeGenCommand());


        System.out.println("Successfully established your connection!!!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Goodbye!");
    }

}
