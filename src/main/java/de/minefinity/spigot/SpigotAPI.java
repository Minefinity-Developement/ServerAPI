package de.minefinity.spigot;

import de.minefinity.global.GlobalAPI;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SpigotAPI extends JavaPlugin {

    @Getter @Setter
    SpigotAPI instance;

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
    public @NotNull Logger getLogger() {
        return super.getLogger();
    }
}
