package net.jandie1505.tpsScoreboard;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import net.jandie1505.configmanager.ConfigManager;
import net.minecraft.server.MinecraftServer;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

public class TpsScoreboard extends JavaPlugin {
    private ConfigManager configManager;

    public void onLoad(){
        this.configManager = new ConfigManager(this.getDefaultConfig(), false, this.getDataFolder(), "config.json");
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));

        CommandAPICommand getTpsCommand = new CommandAPICommand("gettps")
                .withPermission("tpsscoreboard.gettps")
                .executes((sender, args) -> {

                    MinecraftServer minecraftServer = ((CraftServer) this.getServer()).getServer();
                    double tps = minecraftServer.recentTps[0];

                    if (tps > 20.0) {
                        tps = 20.0;
                    }

                    if (!this.configManager.getConfig().optBoolean("silentMode", false)) {
                        sender.sendMessage("§eCurrent TPS: §b" + tps);
                    }

                    return (int) Math.round(tps);

                });

        getTpsCommand.register();

        this.getLogger().info("tpsScoreboard was loaded");
    }

    public void onEnable() {
        this.configManager.reloadConfig();
        CommandAPI.onEnable();

        this.getLogger().info("tpsScoreboard was enabled");
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    private JSONObject getDefaultConfig() {
        JSONObject config = new JSONObject();

        config.put("silentMode", false);

        return config;
    }
}
