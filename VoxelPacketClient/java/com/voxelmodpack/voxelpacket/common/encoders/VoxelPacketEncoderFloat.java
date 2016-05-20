package com.voxelmodpack.voxelpacket.common.encoders;

import java.nio.ByteBuffer;

import com.voxelmodpack.voxelpacket.common.data.Marshal;
import com.voxelmodpack.voxelpacket.common.interfaces.IVoxelPacketEncoder;

/**
 * Encodes floats
 * 
 * @author Adam Mummery-Smith
 *
 */
public class VoxelPacketEncoderFloat implements IVoxelPacketEncoder<Float>
{
	private int dataTypeId;

	public VoxelPacketEncoderFloat(int dataTypeId)
	{
		this.dataTypeId = dataTypeId;
	}

	@Override
	public int getDataTypeID()
	{
		return this.dataTypeId;
	}

	@Override
	public byte[] encode(Float object)
	{
		if (object != null)
		{
			int intValue = Float.floatToIntBits(object);
			return Marshal.unpackByte(intValue);
		}
		
		return new byte[0];
	}

	@Override
	public Float decode(ByteBuffer dataBuffer)
	{
		try
		{
			int value = dataBuffer.getInt();
			return Float.valueOf(Float.intBitsToFloat(value));
		}
		catch (Exception ex) {}
		
		return 0F;
	}
	
}
