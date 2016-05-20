package com.voxelmodpack.voxelpacket.common;

import io.netty.buffer.Unpooled;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;

import com.voxelmodpack.voxelpacket.common.data.VoxelPacketCharset;
import com.voxelmodpack.voxelpacket.common.interfaces.IVoxelPacketHandler;
import com.voxelmodpack.voxelpacket.exceptions.InvalidShortcodeException;

/**
 * VoxelPackets pack abstract data types into sign update packets
 * 
 * @author Adam Mummery-Smith
 */
public class VoxelPacket
{
	public static final String VOXELPACKET_CHANNEL = "VOXELPACKET";

	/**
	 * Character set which is used to provide the encoders between string and byte arrays without mangling the data
	 */
	public static final VoxelPacketCharset CHARSET = new VoxelPacketCharset();
	
	/**
	 * Length of the packet header used in packet count calculations
	 */
	protected static final int HEADER_LENGTH = 18;
	
	/**
	 * Packet handler instance which handles inbound packets
	 */
	protected static IVoxelPacketHandler clientPacketHandler, serverPacketHandler;
	
	/**
	 * Next message ID
	 */
	protected static int nextMessageId = 0;
	
	/**
	 * Message ID
	 */
	public int packetMessageId;
	
	/**
	 * Data type index, only valid on the first message
	 */
	public int packetDataType;
	
	/**
	 * The shortcode for this packet
	 */
	public String packetShortCode;
	
	/**
	 * Relevant entity in this packet
	 */
	public Entity relevantEntity;
	
	public int messageLength;
	
	/**
	 * Data in this packet (payload only)
	 */
	public byte[] packetData;
	
	public VoxelPacket(String channel, PacketBuffer buffer)
	{
		if (channel.equals(VOXELPACKET_CHANNEL))
		{
			buffer.resetReaderIndex();
			this.packetDataType  = buffer.readInt();
			this.messageLength   = buffer.readInt();
			this.relevantEntity  = Util.getEntityWithID(buffer.readInt());
			this.packetShortCode = buffer.readStringFromBuffer(6);

			this.packetData = new byte[buffer.readableBytes()];
			buffer.readBytes(this.packetData);
		}
	}
	
	public void handleServerPacket()
	{
		clientPacketHandler.handleVoxelPacket(this, null);
	}
	
	public void handleClientPacket(EntityPlayerMP sender)
	{
		serverPacketHandler.handleVoxelPacket(this, sender);
	}
	
	public static PacketBuffer createPayload(int dataType, Entity relevantEntity, String shortCode, byte[] payload) throws InvalidShortcodeException
	{
		// Get next valid message id
		int messageId = nextMessageId++;
		
		if (shortCode.length() > 6 || shortCode.length() < 1)
			throw new InvalidShortcodeException("Invalid shortcode specified building voxel packet with ID " + messageId);

		// Index into the payload array
		int messageLength = payload.length;
		
		// Initialise the databuffer with the payload data
		PacketBuffer dataBuffer = new PacketBuffer(Unpooled.buffer());
		dataBuffer.writeInt(dataType);
		dataBuffer.writeInt(messageLength);
		dataBuffer.writeInt((relevantEntity != null) ? relevantEntity.hashCode() : -1);
		dataBuffer.writeString(shortCode);

		while (dataBuffer.writerIndex() < (HEADER_LENGTH))
		{
			dataBuffer.writeByte(32);
		}
		
		dataBuffer.writeBytes(payload);
		return dataBuffer;
	}		
    
	/**
	 * Register the specified handler as the packet handler for this packet
	 * @param handler
	 */
	public static void registerClientPacketHandler(IVoxelPacketHandler handler)
	{
		clientPacketHandler = handler;
	}
	
	/**
	 * Register the specified handler as the packet handler for this packet
	 * @param handler
	 */
	public static void registerServerPacketHandler(IVoxelPacketHandler handler)
	{
		serverPacketHandler = handler;
	}
}
