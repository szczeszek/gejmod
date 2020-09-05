package pl.skiq.skiqLobby

import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandGameMode: CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {
        if (sender is Player) {
            var p: Player = sender;
            when (command?.name) {
                "gm", "gamemode" -> {
                    if (p.hasPermission("skiqlobby.gamemode")) {
                        if ((args != null) && args.isNotEmpty()) {
                            val mode: Int = when (args[0].toLowerCase()) {
                                "0", "survival", "s" -> 0;
                                "1", "creative", "c" -> 1
                                "2", "adventure", "a" -> 2
                                "3", "spectator", "sp" -> 3;
                                else -> {
                                    p.sendMessage("${ChatColor.RED}Poprawne uzycie: /gm <tryb> [gracz]")
                                    return true}
                            }
                            if (args.size >= 2) {
                                if (Main().server.getPlayerExact(args[1]) == null) {
                                    p.sendMessage("${ChatColor.RED}Gracz jest offline.")
                                    return true
                                }
                                else{
                                    p = Main().server.getPlayerExact(args[1])}
                            }
                            when (mode){
                                0 -> p.gameMode = GameMode.SURVIVAL
                                1 -> p.gameMode = GameMode.CREATIVE
                                2 -> p.gameMode = GameMode.ADVENTURE
                                3 -> p.gameMode = GameMode.SPECTATOR
                                4 -> p.gameMode = GameMode.SPECTATOR
                            }
                        }
                        else{
                            p.sendMessage("${ChatColor.RED}Poprawne uzycie: /gm <tryb> [gracz]")}
                    }
                }
            }
        }
        return true
    }

}