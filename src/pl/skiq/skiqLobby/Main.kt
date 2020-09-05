package pl.skiq.skiqLobby

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin
import java.sql.Time
import java.time.Instant

class Main : JavaPlugin(), Listener {

    init {
        print("[SkiqLobby] Inicjalizacja")
    }

//    fun getPlayerExact(
//    name: String): Player {
//        return server.getPlayerExact(name);
//    }

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this);
        logger.info { "[SkiqLobby] Wlaczanie..." }
        this.getCommand("gm").executor = CommandGameMode()
        this.getCommand("gamemode").executor = CommandGameMode()

    }

    override fun onDisable() {
        logger.info { "[SkiqLobby] Wylaczanie..." }
    }

    public val serwer = object {
        val serw = server
    }

    @EventHandler
    public fun playerJoin(event: PlayerJoinEvent/*, player: Player*/) {
        event.joinMessage =
            "${ChatColor.DARK_RED}${event.player.displayName} przyszedl podgladac. Czas: ${ChatColor.RED}${Time.from(
                Instant.now()
            )}"
        if (event.player.hasPermission("skiqlobby.vip") && (!event.player.hasPermission("skiqlobby.admin")))
            Bukkit.broadcastMessage("&casd3")
        Bukkit.broadcastMessage("&6asd4")
        if (!event.player.hasPlayedBefore())
            event.player.gameMode = GameMode.ADVENTURE

    }

    @EventHandler
    public fun playerLeft(event: PlayerQuitEvent) {
        event.quitMessage = null
    }


}
