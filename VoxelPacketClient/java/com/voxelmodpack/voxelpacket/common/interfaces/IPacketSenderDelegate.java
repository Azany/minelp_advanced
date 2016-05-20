package com.voxelmodpack.voxelpacket.common.interfaces;

import net.minecraft.network.PacketBuffer;

/**
 * Delegate interface for functions which send packets
 * 
 * @author Adam Mummery-Smith
 *
 */
public interface IPacketSenderDelegate
{
	/**
	 * Delegate function, send the packet using this delegate
	 * 
	 * @param channel
	 * @param payload
	 */
	public abstract void sendPayload(String channel, PacketBuffer payload);
}
