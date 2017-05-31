package me.orangefreedom.orangefreedommod.command;

import me.orangefreedom.orangefreedommod.rank.Rank;
import me.orangefreedom.orangefreedommod.util.FUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.ADMIN, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Automatically ops user.", usage = "/<command>")
public class Command_opme extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        FUtil.adminAction(sender.getName(), "Giving the orange power to " + sender.getName(), false);
        sender.setOp(true);
        sender.sendMessage(FreedomCommand.YOU_ARE_OP);

        return true;
    }
}
