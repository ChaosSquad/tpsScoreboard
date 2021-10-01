package net.jandie1505.tpsScoreboard.config;

import net.jandie1505.tpsScoreboard.TpsScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    private static File configFile;
    private static FileConfiguration config;

    public static boolean writeTpsIntoScoreboard = false;
    public static String objectiveName = "tpsScore";
    public static String playerName = "#tps";
    public static int tickInterval = 100;

    public FileConfiguration getCustomConfig() {
        return config;
    }

    private static void createCustomConfig() {
        configFile = new File(TpsScoreboard.getPlugin().getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            TpsScoreboard.getPlugin().saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            System.out.println("Configuration Error");
            System.out.println("The plugin is now deactivating because there was an error with the config.");
            Bukkit.getPluginManager().disablePlugin(TpsScoreboard.getPlugin());
        }
    }

    public static void load(){
        createCustomConfig();
        writeTpsIntoScoreboard = config.getBoolean("writeTpsIntoScoreboard.enabled");
        objectiveName = config.getString("writeTpsIntoScoreboard.objectiveName");
        playerName = config.getString("writeTpsIntoScoreboard.playerName");
        tickInterval = config.getInt("writeTpsIntoScoreboard.tickInterval");
    }
}
