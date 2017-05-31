package me.orangefreedom.orangefreedommod.command;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import me.orangefreedom.orangefreedommod.LogViewer.LogsRegistrationMode;
import me.orangefreedom.orangefreedommod.OrangeFreedomMod;
import me.orangefreedom.orangefreedommod.admin.Admin;
import me.orangefreedom.orangefreedommod.config.ConfigEntry;
import me.orangefreedom.orangefreedommod.rank.Rank;
import me.orangefreedom.orangefreedommod.util.FLog;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@CommandPermissions(level = Rank.ADMIN, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Register your connection with the TFM logviewer.", usage = "/<command> [off]")
public class Command_logs extends FreedomCommand
{

    @Override
    public boolean run(final CommandSender sender, final Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        LogsRegistrationMode mode = LogsRegistrationMode.UPDATE;

        if (args.length == 1)
        {
            mode = ("off".equals(args[0]) ? LogsRegistrationMode.DELETE : LogsRegistrationMode.UPDATE);
        }

        plugin.lv.updateLogsRegistration(sender, playerSender, mode);

        return true;
    }

}
