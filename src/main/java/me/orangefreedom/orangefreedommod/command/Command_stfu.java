package me.orangefreedom.orangefreedommod.command;

import me.orangefreedom.orangefreedommod.player.FPlayer;
import me.orangefreedom.orangefreedommod.rank.Rank;
import me.orangefreedom.orangefreedommod.util.FUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Mutes a player with brute force.", usage = "/<command> [[-s] <player> [reason] | list | purge | all]", aliases = "mute")
public class Command_stfu extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            return false;
        }

        if (args[0].equals("list"))
        {
            msg("Muted players:");
            FPlayer info;
            int count = 0;
            for (Player mp : server.getOnlinePlayers())
            {
                info = plugin.pl.getPlayer(mp);
                if (info.isMuted())
                {
                    msg("- " + mp.getName());
                    count++;
                }
            }
            if (count == 0)
            {
                msg("- none");
            }

            return true;
        }

        if (args[0].equals("purge"))
        {
            FUtil.adminAction(sender.getName(), "Un-zipping the mouth of all players.", true);
            FPlayer info;
            int count = 0;
            for (Player mp : server.getOnlinePlayers())
            {
                info = plugin.pl.getPlayer(mp);
                if (info.isMuted())
                {
                    info.setMuted(false);
                    count++;
                }
            }
            msg("Unmuted " + count + " players.");
            return true;
        }

        if (args[0].equals("all"))
        {
            FUtil.adminAction(sender.getName(), "Zipping the mouth of all non-Superadmins", true);

            FPlayer playerdata;
            int counter = 0;
            for (Player player : server.getOnlinePlayers())
            {
                if (!plugin.al.isAdmin(player))
                {
                    playerdata = plugin.pl.getPlayer(player);
                    playerdata.setMuted(true);
                    counter++;
                }
            }

            msg("Muted " + counter + " players.");
            return true;
        }

        // -s option (smite)
        boolean smite = args[0].equals("-s");
        if (smite)
        {
            args = ArrayUtils.subarray(args, 1, args.length);

            if (args.length < 1)
            {
                return false;
            }
        }

        final Player player = getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(FreedomCommand.PLAYER_NOT_FOUND);
            return true;
        }

        String reason = null;
        if (args.length > 1)
        {
            reason = StringUtils.join(args, " ", 1, args.length);
        }

        FPlayer playerdata = plugin.pl.getPlayer(player);
        if (playerdata.isMuted())
        {
            FUtil.adminAction(sender.getName(), "Un-zipping the mouth of " + player.getName(), true);
            playerdata.setMuted(false);
            msg("Unmuted " + player.getName());

            msg(player, "Your mouth is un-zipped.", ChatColor.RED);
        }
        else
        {
            if (plugin.al.isAdmin(player))
            {
                msg(player.getName() + " is a superadmin, and can't be muted.");
                return true;
            }

            FUtil.adminAction(sender.getName(), "Zips the mouth of " + player.getName(), true);
            playerdata.setMuted(true);

            if (smite)
            {
                Command_smite.smite(player);
            }

            if (reason != null)
            {
                msg(player, "You have been zipped. Reason: " + reason, ChatColor.RED);
            }
            else
            {
                msg(player, "You mouth is zipped.", ChatColor.RED);
            }

            msg("Muted " + player.getName());

        }

        return true;
    }
}
