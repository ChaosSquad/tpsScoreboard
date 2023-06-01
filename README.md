# tpsScoreboard
Minecraft plugin which provides a gettps command to store tps values into the vanilla scoreboard.

## Install
1. Have a 1.19.4 Bukkit/Spigot/Paper server
2. Download the plugin from releases or build it yourself
3. Drag the plugin jar file into your plugins folder
4. Restart server

## Usage
Use the command /gettps to show the current tps.  
Store the current tps into a scoreboard using `/execute store result score <player> <score> run gettps`.  
Enable silent mode in config if you don't want to spam the console with tps messages.
