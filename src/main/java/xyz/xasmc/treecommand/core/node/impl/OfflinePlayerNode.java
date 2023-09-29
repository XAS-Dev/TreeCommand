package xyz.xasmc.treecommand.core.node.impl;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import xyz.xasmc.treecommand.core.node.BaseNode;
import xyz.xasmc.treecommand.core.node.marker.Parseable;

import javax.annotation.Nullable;

public class OfflinePlayerNode extends BaseNode implements Parseable {
    protected boolean isCheckPlayerName = false;

    // ===== init =====

    public OfflinePlayerNode() {
    }

    public OfflinePlayerNode(boolean isCheckPlayerName) {
        this.isCheckPlayerName = isCheckPlayerName;
    }

    public OfflinePlayerNode(String nodeName, BaseNode parent, boolean isCheckPlayerName) {
        super(nodeName, parent);
        this.isCheckPlayerName = isCheckPlayerName;
    }


    // ===== Parseable =====

    @Override
    @Nullable
    public String[] getCompletion(CommandSender sender, String[] args) {
        OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();
        String[] result = new String[offlinePlayers.length];
        for (int i = 0; i < offlinePlayers.length; i++)
            result[i] = offlinePlayers[i].getName();
        return result;
    }

    @Override
    public int getArgsQuantity(String[] unprocessedArgs) {
        if (unprocessedArgs.length == 0) return -1;
        if (!this.isCheckPlayerName) return 1;
        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            String name = offlinePlayer.getName();
            if (name != null && name.equalsIgnoreCase(unprocessedArgs[0])) return 1;
        }
        return -1;
    }

    @Nullable
    @Override
    public Object parseArgument(CommandSender sender, String[] args) {
        return Bukkit.getOfflinePlayer(args[0]);
    }

    // ===== custom =====

    public void setCheckPlayerName(boolean checkPlayerName) {
        this.isCheckPlayerName = checkPlayerName;
    }
}