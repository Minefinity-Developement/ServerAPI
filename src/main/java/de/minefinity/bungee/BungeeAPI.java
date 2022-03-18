package de.minefinity.bungee;

import de.minefinity.global.GlobalAPI;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BungeeAPI extends Plugin {

    @Getter @Setter
    BungeeAPI instance;

    @Getter @Setter
    GlobalAPI globalAPI;

    @Override
    public void onEnable() {
        this.setInstance(this);
        this.setGlobalAPI(new GlobalAPI());
        this.getLogger().log(Level.INFO, "Die Server API (Spigot) startet");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public Logger getLogger() {
        return super.getLogger();
    }
}
