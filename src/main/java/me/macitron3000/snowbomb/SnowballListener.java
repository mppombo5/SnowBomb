package me.macitron3000.snowbomb;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SnowballListener implements Listener {
    // Hook for main plugin, to access config and whatnot.
    private final SnowBomb plugin;

    public SnowballListener(SnowBomb plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSnowballLand(ProjectileHitEvent event) {
        Projectile proj = event.getEntity();
        FileConfiguration config = this.plugin.getConfig();
        if ( !(proj instanceof Snowball) || !config.getBoolean("enable") ) {
            return;
        }

        // `event` has different methods for returning the player or block
        // that was hit, but we just want the location.
        Location loc = null;
        Block b = event.getHitBlock();
        Entity e = event.getHitEntity();
        if (b != null) {
            loc = b.getLocation();
        } else if (e != null) {
            loc = e.getLocation();
        } else {
            // No idea when this would happen
            return;
        }

        // Now spawn an explosion at `loc`
        float power = (float) config.getDouble("explosion-power");
        boolean setFire = config.getBoolean("set-fire");
        boolean breakBlocks = config.getBoolean("break-blocks");
        loc.getWorld().createExplosion(loc, power, setFire, breakBlocks);
    }
}
