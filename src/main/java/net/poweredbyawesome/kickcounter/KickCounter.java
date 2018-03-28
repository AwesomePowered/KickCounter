package net.poweredbyawesome.kickcounter;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public final class KickCounter extends JavaPlugin implements Listener {

    private Map<UUID, AtomicInteger> counter;

    @Override
    public void onEnable() {
        counter = new HashMap<>();
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("kickcounter").setExecutor(new CommandKickCounter(this));
    }

    @Override
    public void onDisable() {
        for (UUID uuid : counter.keySet()) {
            getConfig().set("players."+uuid.toString(), counter.get(uuid).intValue());
        }
        saveConfig();
    }

    public AtomicInteger getKick(UUID uuid) {
        counter.computeIfAbsent(uuid, k -> new AtomicInteger(getConfig().getInt("players."+uuid.toString(), 0)));
        return counter.get(uuid);
    }

    @EventHandler
    public void onKick(PlayerKickEvent ev) {
        getKick(ev.getPlayer().getUniqueId()).incrementAndGet();
    }

}