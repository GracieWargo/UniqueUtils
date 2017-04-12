package Commands;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dsate1.UniqueUtils.Main.Referances;

import me.dsate1.UniqueUtils.Main.UniqueUtils;

import net.pravian.aero.command.CommandOptions;
import net.pravian.aero.command.SimpleCommand;

@CommandOptions(permission = "utils.smite")
public class Command_smite extends SimpleCommand<Utils> {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { 		
     if (args.length == 0){
        		sender.sendMessage(ChatColor.RED + "Wrong arguments please provide:");
        		sender.sendMessage(ChatColor.RED + "/smite <player> [reason]");
        }

        final Player player = getPlayer(args[0]);

        String reason = null;
        if (args.length > 1)
        {
            reason = StringUtils.join(args, " ", 1, args.length);
        }

        if (player == null)
        {
            msg(Referances.PLAYER_NOT_FOUND);
            return true;
        }

        smite(player, reason);
        return true;
    }

    public static void smite(Player player)
    {
        smite(player, null);
    }

    public static void smite(Player player, String reason)
    {
        Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " has been a naughty, naughty boy.");

        if (reason != null)
        {
        	Bukkit.broadcastMessage(ChatColor.RED + "  Reason: " + reason);
        }

        // Deop

        // Set gamemode to survival
        player.setGameMode(GameMode.SURVIVAL);

        // Clear inventory
        player.getInventory().clear();

        // Strike with lightning effect
        final Location targetPos = player.getLocation();
        final World world = player.getWorld();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                final Location strike_pos = new Location(world, targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                world.strikeLightning(strike_pos);
            }
            
        }

        // Kill
        player.setHealth(0.0);

        if (reason != null)
        {
            player.sendMessage(ChatColor.RED + "You've been smitten. Reason: " + reason);
        }
    }
}