package application.minecraft.plugintest;


import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.navigation.Navigation;
import net.minecraft.world.level.pathfinder.PathEntity;
import org.bukkit.Location;

/*public class PathfinderWalkToTile extends PathfinderGoal {

    private double speed;

    private EntityInsentient entity;

    private Location loc;

    private Navigation navigation;

    public PathfinderGoalWalkToLoc(EntityInsentient entity, Location loc, double speed)
    {
        this.entity = entity;
        this.loc = loc;
        this.navigation = this.entity.getNavigation();
        this.speed = speed;
    }

    public boolean a()
    {
        return true;
    }

    public void c()
    {
        PathEntity pathEntity = this.navigation.a(loc.getX(), loc.getY(), loc.getZ());

        this.navigation.a(pathEntity, speed);
    }
}*/