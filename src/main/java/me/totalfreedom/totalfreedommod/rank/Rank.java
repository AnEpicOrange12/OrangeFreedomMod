package me.totalfreedom.totalfreedommod.rank;

import java.security.acl.Owner;
import lombok.Getter;
import org.bukkit.ChatColor;

public enum Rank implements Displayable
{
    IMPOSTOR("an", "Impostor", Type.PLAYER, "Imp", ChatColor.YELLOW),
    NON_OP("a", "Non-Op", Type.PLAYER, "", ChatColor.BLACK),
    OP("an", "Op", Type.PLAYER, "OP", ChatColor.RED),
    ADMIN("an", "Admin", Type.ADMIN, "Admin", ChatColor.RED),
    SUPER_ADMIN("a", "Super Admin", Type.ADMIN, "SA", ChatColor.AQUA),
    DETECTIVE_ADMIN("a", "Detective Admin", Type.ADMIN, "DA", ChatColor.YELLOW),
    TF_ADMIN("a", "Total Freedom Admin", Type.ADMIN, "TFA", ChatColor.DARK_GREEN),
    CONTROL_ADMIN("the", "Control Admin", Type.ADMIN, "CA", ChatColor.DARK_GRAY),
    GENERAL_ADMIN("a", "General Admin", Type.ADMIN, "GA", ChatColor.DARK_BLUE),
    ORANGE_ADMIN("a", "Orange Admin", Type.ADMIN, "OA", ChatColor.DARK_PURPLE),
    TCA("a", "Telnet Clan Admin", Type.ADMIN, "TCA", ChatColor.GREEN),
    TELNET_ADMIN("a", "Telnet Admin", Type.ADMIN, "STA", ChatColor.DARK_GREEN),
    SENIOR_ADMIN("a", "Senior Admin", Type.ADMIN, "SrA", ChatColor.GOLD),
    SYSADMIN("a", "System Admin", Type.ADMIN, "System Admin", ChatColor.RED),
    EXECUTIVE("an", "Executive", Type.ADMIN, "Executive", ChatColor.GRAY),
    COOWNER("the", "Co-Owner", Type.ADMIN, "Co-Owner", ChatColor.DARK_AQUA),
    WEBDEV("the", "Web-Developer", Type.ADMIN, "Web-Dev", ChatColor.DARK_GRAY),
    SYS_CONSOLE("the", "System-Console", Type.ADMIN_CONSOLE, "System-Console", ChatColor.RED),
    EXE_CONSOLE("the", "Executive-Console", Type.ADMIN_CONSOLE, "Executive-Console", ChatColor.WHITE),
    CO_CONSOLE("the", "Co-Owner-Console", Type.ADMIN_CONSOLE, "CO-Console", ChatColor.DARK_AQUA),
    WEB_CONSOLE("the", "Web-Console", Type.ADMIN_CONSOLE, "Web_Console", ChatColor.DARK_GRAY),
    TELNET_CONSOLE("the", "Telnet-Console", Type.ADMIN_CONSOLE, "Telnet-Console", ChatColor.DARK_PURPLE),
    SENIOR_CONSOLE("the", "Senior-Console", Type.ADMIN_CONSOLE, "Senior-Console", ChatColor.DARK_PURPLE);
    @Getter
    private final Type type;
    @Getter
    private final String name;
    private final String determiner;
    @Getter
    private final String tag;
    @Getter
    private final String coloredTag;
    @Getter
    private final ChatColor color;

    private Rank(String determiner, String name, Type type, String abbr, ChatColor color)
    {
        this.type = type;
        this.name = name;
        this.determiner = determiner;
        this.tag = abbr.isEmpty() ? "" : "[" + abbr + "]";
        this.coloredTag = abbr.isEmpty() ? "" : ChatColor.DARK_GRAY + "[" + color + abbr + ChatColor.DARK_GRAY + "]" + color;
        this.color = color;
    }

    @Override
    public String getColoredName()
    {
        return color + name;
    }

    @Override
    public String getColoredLoginMessage()
    {
        return determiner + " " + color + ChatColor.ITALIC + name;
    }

    public boolean isConsole()
    {
        return getType() == Type.ADMIN_CONSOLE;
    }

    public int getLevel()
    {
        return ordinal();
    }

    public boolean isAtLeast(Rank rank)
    {
        if (getLevel() < rank.getLevel())
        {
            return false;
        }

        if (!hasConsoleVariant() || !rank.hasConsoleVariant())
        {
            return true;
        }

        return getConsoleVariant().getLevel() >= rank.getConsoleVariant().getLevel();
    }

    public boolean isAdmin()
    {
        return getType() == Type.ADMIN || getType() == Type.ADMIN_CONSOLE;
    }

    public boolean hasConsoleVariant()
    {
        return getConsoleVariant() != null;
    }

    public Rank getConsoleVariant()
    {
        switch (this)
        {
            case TELNET_ADMIN:
            case TELNET_CONSOLE:
                return TELNET_CONSOLE;
            case SENIOR_ADMIN:
            case SENIOR_CONSOLE:
                return SENIOR_CONSOLE;
            case SYSADMIN:
            case SYS_CONSOLE:
                return SYS_CONSOLE;
            case EXECUTIVE:
            case EXE_CONSOLE:
                return EXE_CONSOLE;
            case COOWNER:
            case CO_CONSOLE:
                return CO_CONSOLE;
            case WEBDEV:
            case WEB_CONSOLE:
                return WEB_CONSOLE;
                    
            default:
                return null;
        }
    }

    public Rank getPlayerVariant()
    {
        switch (this)
        {
            case EXECUTIVE:
            case EXE_CONSOLE:
                return EXECUTIVE;
            case COOWNER:
            case CO_CONSOLE:
                return COOWNER;
            default:
                return null;
        }
    }

    public static Rank findRank(String string)
    {
        try
        {
            return Rank.valueOf(string.toUpperCase());
        }
        catch (Exception ignored)
        {
        }

        return Rank.NON_OP;
    }

    public static enum Type
    {

        PLAYER,
        ADMIN,
        ADMIN_CONSOLE;

        public boolean isAdmin()
        {
            return this != PLAYER;
        }
    }

}
