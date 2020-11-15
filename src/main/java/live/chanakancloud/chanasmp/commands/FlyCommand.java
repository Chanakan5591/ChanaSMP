package live.chanakancloud.chanasmp.commands;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("chanasmp.commands.fly")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f You are not allowed to run this command!"));
            return true;
        }
        if(sender.getAllowFlight() == false){
            sender.setAllowFlight(true);
        	return true;
      	}
        return true;
    }
}
