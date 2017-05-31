package me.orangefreedom.orangefreedommod.rank;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Title implements Displayable
{

    DEVELOPER("a", "OrangeFreedom Developer", ChatColor.DARK_PURPLE, "OF-Dev"),
    FORUMADMIN("a", "Forum Administrator", ChatColor.DARK_GREEN, "Forum Admin"),
    DISCORDADMIN("a", "Discord Administrator", ChatColor.LIGHT_PURPLE, "Discord Admin"),
    MA("a", "Master-Architect", ChatColor.YELLOW, "Master-Architect"),
    EXECUTIVE("an", "Executive Administrator", ChatColor.DARK_AQUA, "Executive"),
    COOWNER("a", "Co-Owner", ChatColor.BLUE, "Co-Owner"),
    QUEEN("the", "Queen", ChatColor.LIGHT_PURPLE, "Queen"),
    FRIEND("a", "Friend", ChatColor.GOLD, "Friend"),
    OWNER("the", "Owner", ChatColor.DARK_BLUE, "Owner");

    private final String determiner;
    @Getter
    private final String name;
    @Getter
    private final String tag;
    @Getter
    private final String coloredTag;
    @Getter
    private final ChatColor color;

    private Title(String determiner, String name, ChatColor color, String tag)
    {
        this.determiner = determiner;
        this.name = name;
        this.tag = "[" + tag + "]";
        this.coloredTag = ChatColor.DARK_GRAY + "[" + color + tag + ChatColor.DARK_GRAY + "]" + color;
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

}
