package me.orangefreedom.orangefreedommod.command;

import me.orangefreedom.orangefreedommod.rank.Rank;
import me.orangefreedom.orangefreedommod.util.FUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Remove various server entities that may cause lag, such as dropped items, minecarts, and boats.", usage = "/<command> <carts>", aliases = "ew,rd")
public class Command_entitywipe extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        FUtil.adminAction(sender.getName(), "Removing all server entities.", true);
        msg((plugin.ew.wipeEntities(true)) + " entities removed.");

        return true;
    }
}
