package me.macitron3000.snowbomb;

import org.bukkit.plugin.java.JavaPlugin;

public final class SnowBomb extends JavaPlugin {

    @Override
    public void onEnable() {
        // Send default config to data dir
        saveDefaultConfig();

        // Register listener
        getServer().getPluginManager().registerEvents(new SnowballListener(this), this);

        // :D
        getLogger().info("SnowBomb has been enabled!");
    }

    @Override
    public void onDisable() {
        // D:
        getLogger().info("SnowBomb has been disabled. Thank you for using!");
    }
}
