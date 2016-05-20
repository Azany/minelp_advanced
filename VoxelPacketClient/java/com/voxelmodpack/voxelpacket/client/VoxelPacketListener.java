package com.voxelmodpack.voxelpacket.client;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.IChatComponent;

import com.mojang.authlib.GameProfile;
import com.mumfrey.liteloader.ChatFilter;
import com.mumfrey.liteloader.PluginChannelListener;
import com.mumfrey.liteloader.ServerPlayerListener;
import com.mumfrey.liteloader.ServerPluginChannelListener;
import com.voxelmodpack.voxelpacket.common.VoxelPacket;
import com.voxelmodpack.voxelpacket.common.interfaces.IChatHandler;
import com.voxelmodpack.voxelpacket.server.VoxelPacketServer;

public class VoxelPacketListener implements PluginChannelListener, ServerPluginChannelListener, ServerPlayerListener, ChatFilter
{
	private static IChatHandler chatHandler; 
	
	public VoxelPacketListener()
	{
	}
	
	@Override
	public String getName()
	{
		return "VoxelPacket";
	}
	
	@Override
	public String getVersion()
	{
		return "2.2.0";
	}
	
	@Override
	public void init(File configPath)
	{
		VoxelPacketClient.getInstance();
		VoxelPacketServer.initInstance();
	}
	
	@Override
	public void upgradeSettings(String version, File configPath, File oldConfigPath)
	{
	}
	
	@Override
	public List<String> getChannels()
	{
		return Arrays.asList(new String[] { VoxelPacket.VOXELPACKET_CHANNEL });
	}
	
	@Override
	public void onCustomPayload(String channel, PacketBuffer data)
	{
		try
		{
			VoxelPacket packet = new VoxelPacket(channel, data);
			packet.handleServerPacket();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Override
	public void onCustomPayload(EntityPlayerMP sender, String channel, PacketBuffer data)
	{
		try
		{
			VoxelPacket packet = new VoxelPacket(channel, data);
			packet.handleClientPacket(sender);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void registerPacketHandler(IChatHandler handler)
	{
		VoxelPacketListener.chatHandler = handler;
	}
	
	@Override
	public boolean onChat(IChatComponent chat, String message, ReturnValue<IChatComponent> newMessage)
	{
		if (VoxelPacketListener.chatHandler != null)
		{
			return !VoxelPacketListener.chatHandler.handleChatMessage(message);
		}
		
		return true;
	}

	@Override
	public void onPlayerConnect(EntityPlayerMP player, GameProfile profile)
	{
	}

	@Override
	public void onPlayerLoggedIn(EntityPlayerMP player)
	{
		VoxelPacketServer.getInstance().queryPlayer(player, "SUBS");
	}

	@Override
	public void onPlayerRespawn(EntityPlayerMP player, EntityPlayerMP oldPlayer, int newDimension, boolean playerWonTheGame)
	{
	}

	@Override
	public void onPlayerLogout(EntityPlayerMP player)
	{
		VoxelPacketServer.getInstance().onPlayerQuit(player);
	}

	@Override
	public boolean onChat(S02PacketChat chatPacket, IChatComponent chat, String message) {
		return false;
	}

	@Override
	public void onCustomPayload(String channel, int length, byte[] data) {

	}

	@Override
	public void onJoinGame(INetHandler netHandler, S01PacketJoinGame joinGamePacket) {

	}

	@Override
	public void onCustomPayload(EntityPlayerMP sender, String channel, int length, byte[] data) {

	}
}
