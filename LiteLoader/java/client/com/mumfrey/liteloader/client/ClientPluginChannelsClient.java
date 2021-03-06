package com.mumfrey.liteloader.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.network.login.server.S02PacketLoginSuccess;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S3FPacketCustomPayload;

import com.mumfrey.liteloader.client.util.PrivateFields;
import com.mumfrey.liteloader.core.ClientPluginChannels;
import com.mumfrey.liteloader.core.exceptions.UnregisteredChannelException;

/**
 * Handler for client plugin channels
 * 
 * @author Adam Mummery-Smith
 */
public class ClientPluginChannelsClient extends ClientPluginChannels
{
	/**
	 * @param netHandler
	 * @param loginPacket
	 */
	void onPostLogin(INetHandlerLoginClient netHandler, S02PacketLoginSuccess loginPacket)
	{
		this.clearPluginChannels(netHandler);
	}

	/**
	 * @param netHandler
	 * @param loginPacket
	 */
	void onJoinGame(INetHandler netHandler, S01PacketJoinGame loginPacket)
	{
		this.sendRegisteredPluginChannels(netHandler);
	}

	/**
	 * Callback for the plugin channel hook
	 * 
	 * @param customPayload
	 */
	@Override
	public void onPluginChannelMessage(S3FPacketCustomPayload customPayload)
	{
		if (customPayload != null && customPayload.func_149169_c() != null) // getChannel
		{
			String channel = customPayload.func_149169_c(); // getChannel
			byte[] data = customPayload.func_149168_d(); // getData
			
			this.onPluginChannelMessage(channel, data);
		}
	}

	/**
	 * @param netHandler
	 * @param registrationData
	 */
	@Override
	protected void sendRegistrationData(INetHandler netHandler, byte[] registrationData)
	{
		if (netHandler instanceof INetHandlerLoginClient)
		{
			NetworkManager networkManager = PrivateFields.netManager.get(((NetHandlerLoginClient)netHandler));
			networkManager.scheduleOutboundPacket(new C17PacketCustomPayload(CHANNEL_REGISTER, registrationData));
		}
		else if (netHandler instanceof INetHandlerPlayClient)
		{
			ClientPluginChannelsClient.dispatch(new C17PacketCustomPayload(CHANNEL_REGISTER, registrationData));
		}
	}

	/**
	 * Send a message to the server on a plugin channel
	 * 
	 * @param channel Channel to send, must not be a reserved channel name
	 * @param data
	 */
	@Override
	protected boolean send(String channel, byte[] data, ChannelPolicy policy)
	{
		if (channel == null || channel.length() > 16 || CHANNEL_REGISTER.equals(channel) || CHANNEL_UNREGISTER.equals(channel))
			throw new RuntimeException("Invalid channel name specified"); 
		
		if (!policy.allows(this, channel))
		{
			if (policy.isSilent()) return false;
			throw new UnregisteredChannelException(channel);
		}
		
		C17PacketCustomPayload payload = new C17PacketCustomPayload(channel, data);
		return ClientPluginChannelsClient.dispatch(payload);
	}

	/**
	 * @param channel
	 * @param data
	 */
	static boolean dispatch(C17PacketCustomPayload payload)
	{
		try
		{
			Minecraft minecraft = Minecraft.getMinecraft();
			
			if (minecraft.thePlayer != null && minecraft.thePlayer.sendQueue != null)
			{
				minecraft.thePlayer.sendQueue.addToSendQueue(payload);
				return true;
			}
		}
		catch (Exception ex) {}
		
		return false;
	}
}
