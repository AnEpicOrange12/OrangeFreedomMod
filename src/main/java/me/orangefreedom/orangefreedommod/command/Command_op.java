package me.orangefreedom.orangefreedommod.command;

import me.orangefreedom.orangefreedommod.rank.Rank;
import me.orangefreedom.orangefreedommod.util.DepreciationAggregator;
import me.orangefreedom.orangefreedommod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.OP, source = SourceType.BOTH)
@CommandParameters(description = "Makes a player operator", usage = "/<command> <playername>")
public class Command_op extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 1)
        {
            return false;
        }

        if (args[0].equalsIgnoreCase("all") || args[0].equalsIgnoreCase("everyone"))
        {
            msg("Correct usage: /opall");
            return true;
        }

        OfflinePlayer player = null;
        for (Player onlinePlayer : server.getOnlinePlayers())
        {
            if (args[0].equalsIgnoreCase(onlinePlayer.getName()))
            {
                player = onlinePlayer;
            }
        }

        // if the player is not online
        if (player == null)
        {
            if (plugin.al.isAdmin(sender) || senderIsConsole)
            {
                player = DepreciationAggregator.getOfflinePlayer(server, args[0]);
            }
            else
            {
                msg("That player is not online.");
                msg("You don't have permissions to give offline players orange powers.", ChatColor.RED);
                return true;
            }
        }

        FUtil.adminAction(sender.getName(), "Giving the orange power to " + player.getName(), false);
        player.setOp(true);

        return true;
    }
}
