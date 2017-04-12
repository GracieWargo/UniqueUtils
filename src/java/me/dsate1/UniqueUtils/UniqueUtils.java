package me.dsate1.UniqueUtils.Main;

import net.pravian.aero.command.handler.AeroCommandHandler;
import net.pravian.aero.command.handler.SimpleCommandHandler;
import net.pravian.aero.config.YamlConfig;
import net.pravian.aero.plugin.AeroPlugin;
import net.pravian.aero.util.Loggers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import Commands.Command_smite;
import Listener.PlayerJoinMessage;

public class UniqueUtils extends AeroPlugin<Utils> {

    public static Utils plugin;

    @SuppressWarnings("rawtypes")
	public static AeroCommandHandler handler;
    public Loggers logger;
    public YamlConfig config;
    public YamlConfig mutedusers;


    @Override
    public void load() {
        Utils.plugin = this;

        Loggers.info("[UniqueUtils] Loading!");
        this.config = new YamlConfig(plugin, "config.yml", true);
        this.mutedusers = new YamlConfig(plugin, "muted-users.yml", true);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void enable() {

        Utils.plugin = this;
        new Staff(this);
        @SuppressWarnings("unused")
		PluginManager pm = Bukkit.getPluginManager();

        // Load Configs
        config.load();
        mutedusers.load();

        // Register command class 
        handler = new SimpleCommandHandler(plugin);
        handler.setCommandClassPrefix("Command_");
        handler.loadFrom(Command_smite.class.getPackage());
        handler.registerAll(handler.getCommandClassPrefix(), true);

        // Register Listeners

    }

    @Override
    public void disable() {

        Utils.plugin = this;

        // Stop services

        // Save
    }

}