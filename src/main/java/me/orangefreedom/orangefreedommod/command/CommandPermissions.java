package me.orangefreedom.orangefreedommod.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import me.orangefreedom.orangefreedommod.rank.Rank;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandPermissions
{

    Rank level();

    SourceType source();

    boolean blockHostConsole() default false;
}
