package application.minecraft.plugintest;


import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.entity.monster.EntityZombie;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;

import java.lang.reflect.Field;
import java.util.List;

/*public class CustomZombie extends EntityZombie
{
    public CustomZombie(org.bukkit.World world) //You can also directly use the nms world class but this is easier if you are spawning this entity.
    {
        super(((CraftWorld)world).getHandle());
    }

}


    public static Object getPrivateField(String fieldName, Class clazz, Object object) {
        Field field;
        Object o = null;

        try {
            field = clazz.getDeclaredField(fieldName);

            field.setAccessible(true);

            o = field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return o;
    }
        }*/

