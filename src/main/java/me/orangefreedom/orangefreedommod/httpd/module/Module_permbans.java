package me.orangefreedom.orangefreedommod.httpd.module;

import java.io.File;
import me.orangefreedom.orangefreedommod.OrangeFreedomMod;
import me.orangefreedom.orangefreedommod.banning.PermbanList;
import me.orangefreedom.orangefreedommod.httpd.HTTPDaemon;
import me.orangefreedom.orangefreedommod.httpd.NanoHTTPD;

public class Module_permbans extends HTTPDModule
{

    public Module_permbans(OrangeFreedomMod plugin, NanoHTTPD.HTTPSession session)
    {
        super(plugin, session);
    }

    @Override
    public NanoHTTPD.Response getResponse()
    {
        File permbanFile = new File(plugin.getDataFolder(), PermbanList.CONFIG_FILENAME);
        if (permbanFile.exists())
        {
            return HTTPDaemon.serveFileBasic(new File(plugin.getDataFolder(), PermbanList.CONFIG_FILENAME));
        }
        else
        {
            return new NanoHTTPD.Response(NanoHTTPD.Response.Status.NOT_FOUND, NanoHTTPD.MIME_PLAINTEXT,
                    "Error 404: Not Found - The requested resource was not found on this server.");
        }
    }
}
