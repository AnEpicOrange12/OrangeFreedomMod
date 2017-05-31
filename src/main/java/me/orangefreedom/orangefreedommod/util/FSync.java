package me.orangefreedom.orangefreedommod.util;

import me.orangefreedom.orangefreedommod.OrangeFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FSync
{

    public static void playerMsg(final Player player, final String message)
    {
        final OrangeFreedomMod plugin = OrangeFreedomMod.plugin();
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                FUtil.playerMsg(player, message);
            }

        }.runTask(plugin);
    }

    public static void playerKick(final Player player, final String reason)
    {
        final OrangeFreedomMod plugin = OrangeFreedomMod.plugin();
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                player.kickPlayer(reason);
            }

        }.runTask(plugin);
    }

    public static void adminChatMessage(final CommandSender sender, final String message)
    {
        final OrangeFreedomMod plugin = OrangeFreedomMod.plugin();
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                plugin.cm.adminChat(sender, message);
            }

        }.runTask(plugin);
    }

    public static void autoEject(final Player player, final String kickMessage)
    {
        final OrangeFreedomMod plugin = OrangeFreedomMod.plugin();
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                plugin.ae.autoEject(player, kickMessage);
            }

        }.runTask(plugin);
    }

    public static void bcastMsg(final String message, final ChatColor color)
    {
        final OrangeFreedomMod plugin = OrangeFreedomMod.plugin();
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                FUtil.bcastMsg(message, color);
            }

        }.runTask(plugin);
    }
}
