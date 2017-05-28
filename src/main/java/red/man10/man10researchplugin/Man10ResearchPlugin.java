package red.man10.man10researchplugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class Man10ResearchPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    String prefix = "§l[§8§lResearch§f§l]§f";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("mresearch")){
            if(sender instanceof Player == false){
                sender.sendMessage(prefix + "command only runnable by player");
                return false;
            }
            Player p = (Player) sender;
            if(args.length == 0){
                help(p);
                return true;
            }
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("help")){
                    help(p);
                    return true;
                }
                if(args[0].equalsIgnoreCase("iteminfo")){
                    ItemStack item = p.getInventory().getItemInMainHand();
                    p.sendMessage(String.valueOf(item));
                    return false;
                }
                if(args[0].equalsIgnoreCase("signinfo")){
                    Block b = p.getTargetBlock((Set<Material>) null, 100);
                    if(b.getState() instanceof Sign == false){
                        p.sendMessage(prefix + "看板を見て実行してください");
                        return false;
                    }
                    Sign s = (Sign) b.getState();
                    p.sendMessage("§6§l[Sign Info]");
                    p.sendMessage("§6§nLocation world:" + s.getLocation().getWorld().getName() + " x:" + s.getLocation().getX() + " y:" + s.getLocation().getY() + " z:" + s.getLocation().getZ());
                    p.sendMessage("§6§l↓ Lines ↓");
                    p.sendMessage(s.getLine(0));
                    p.sendMessage(s.getLine(1));
                    p.sendMessage(s.getLine(2));
                    p.sendMessage(s.getLine(3));
                    p.sendMessage("§6§l↑ Lines ↑");
                    return true;
                }
                help(p);
            }
            help(p);
        }
        return false;
    }

    void help(Player p){
        p.sendMessage("§7=-=-=-=-=-=-=§f" + prefix + "§7=-=-=-=-=-=-=");
        p.sendMessage("§7/mresearch help ヘルプ表示");
        p.sendMessage("§7/mresearch iteminfo アイテム情報");
        p.sendMessage("§7/mresearch signinfo アイテム情報");
        p.sendMessage("§7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        p.sendMessage("§dCreated By Sho0");
        p.sendMessage("§bTwitter twitter.com/sho0_dev");
    }
}
