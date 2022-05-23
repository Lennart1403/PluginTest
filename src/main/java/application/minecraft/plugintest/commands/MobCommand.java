package application.minecraft.plugintest.commands;

import application.minecraft.plugintest.PathfinderWalkToTile;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.navigation.NavigationAbstract;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.concurrent.TimeUnit;

public class MobCommand implements CommandExecutor  {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            //LivingEntity mob = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
            LivingEntity mob = (LivingEntity) p.getWorld().spawnEntity(new Location(p.getWorld(), 1.0, -60.0, 1.0), EntityType.ZOMBIE);
            //EntityInsentient mob = (EntityInsentient) p.getWorld().spawnEntity(new Location(p.getWorld(), 1.0, -60.0, 1.0), EntityType.ZOMBIE);
            Zombie z = (Zombie) mob;

            //Location loc = p.getTargetBlock((HashSet<Material>) null, 99).getLocation();
            Location loc = new Location(p.getWorld(), 2.0, -60.0, 1.0);


            z.teleport(new Location(p.getWorld(), 2.0, -60.0, 1.0));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            z.teleport(new Location(p.getWorld(), 3.0, -60.0, 1.0));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            z.teleport(new Location(p.getWorld(), 4.0, -60.0, 1.0));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            z.teleport(new Location(p.getWorld(), 5.0, -60.0, 1.0));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            z.teleport(new Location(p.getWorld(), 6.0, -60.0, 1.0));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            z.teleport(new Location(p.getWorld(), 7.0, -60.0, 1.0));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            z.teleport(new Location(p.getWorld(), 8.0, -60.0, 1.0));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            z.teleport(new Location(p.getWorld(), 9.0, -60.0, 1.0));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            z.teleport(new Location(p.getWorld(), 10.0, -60.0, 1.0));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            z.teleport(new Location(p.getWorld(), 11.0, -60.0, 1.0));

            //z.getNavigation().a(loc, 1.0);

            
            z.setCustomName("ChargingMob");
            z.setCustomNameVisible(true);
            z.setInvulnerable(true);
            z.setBaby(false); // zombie is babye? = false


            sender.sendMessage("§6§lMOBS: §fMob custom spawned!");
        }

        return true;
    }
}

