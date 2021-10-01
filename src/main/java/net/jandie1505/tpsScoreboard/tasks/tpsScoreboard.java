package net.jandie1505.tpsScoreboard.tasks;

import net.jandie1505.tpsScoreboard.config.Config;
import org.bukkit.Bukkit;

public class tpsScoreboard implements Runnable{

    @Override
    public void run() {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players set " + Config.playerName + " " + Config.objectiveName + " " + (int) Lag.getTPS());
    }

    public static void init(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "scoreboard objectives add " + Config.objectiveName +" dummy");
    }
}
