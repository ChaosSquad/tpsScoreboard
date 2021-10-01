package net.jandie1505.tpsScoreboard;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import net.jandie1505.tpsScoreboard.config.Config;
import net.jandie1505.tpsScoreboard.tasks.Lag;
import net.jandie1505.tpsScoreboard.tasks.tpsScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TpsScoreboard extends JavaPlugin {
    private static TpsScoreboard plugin;

    public void onLoad(){
        plugin = this;
        Config.load();
        CommandAPI.onLoad(true);

        CommandAPICommand getTpsCommand = new CommandAPICommand("gettps")
                .withPermission("tpsscoreboard.gettps")
                .executesPlayer((sender, args) -> {
                    Player p = (Player) sender;
                    p.sendMessage("§eCurrent TPS: " + Lag.getTPS());
                    return (int) Lag.getTPS();
                })
                .executesConsole((sender, args) -> {
                    System.out.println("§eCurrent TPS: " + Lag.getTPS());
                    return (int) Lag.getTPS();
                })
                .executesProxy((proxy, args) -> {
                    return (int) Lag.getTPS();
                });

        getTpsCommand.register();

        System.out.println("[tpsScoreboard] tpsScoreboard was loaded");
    }

    public void onEnable(){
        Config.load();
        CommandAPI.onEnable(this);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
        if(Config.writeTpsIntoScoreboard){
            try {
                tpsScoreboard.init();
                Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new tpsScoreboard(), 0, Config.tickInterval);
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("[tpsScoreboard] An error occured with scheduling the task which writes the current TPS into the scoreboard. Please check the config.");
            }
        }

        System.out.println("[tpsScoreboard] tpsScoreboard was enabled");
    }

    public static TpsScoreboard getPlugin(){
        return plugin;
    }
}
