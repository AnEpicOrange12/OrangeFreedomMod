package me.orangefreedom.orangefreedommod.blocking.command;

import me.orangefreedom.orangefreedommod.OrangeFreedomMod;
import me.orangefreedom.orangefreedommod.admin.Admin;
import me.orangefreedom.orangefreedommod.rank.Rank;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum CommandBlockerRank
{

    ANYONE("a"),
    OP("o"),
    SUPER("s"),
    EXE("t"),
    CO("c"),
    NOBODY("n");
    //
    private final String token;

    private CommandBlockerRank(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return this.token;
    }

    public boolean hasPermission(CommandSender sender)
    {
        return fromSender(sender).ordinal() >= ordinal();
    }

    public static CommandBlockerRank fromSender(CommandSender sender)
    {
        if (!(sender instanceof Player))
        {
            return EXE;
        }

        Admin admin = OrangeFreedomMod.plugin().al.getAdmin(sender);
        if (admin != null)
        {
            if (admin.getRank() == Rank.SENIOR_ADMIN)
            {
                return CO;
            }
            return SUPER;
        }

        if (sender.isOp())
        {
            return OP;
        }

        return ANYONE;

    }

    public static CommandBlockerRank fromToken(String token)
    {
        for (CommandBlockerRank rank : CommandBlockerRank.values())
        {
            if (rank.getToken().equalsIgnoreCase(token))
            {
                return rank;
            }
        }
        return ANYONE;
    }
}
