package com.voxelmodpack.voxelpacket.common.interfaces;


/**
 * Interface for objects which can handle inbound chat
 * 
 * @author Adam Mummery-Smith
 */
public interface IChatHandler
{
	/**
	 * Handle a chat packet, the implementor can return TRUE to cancel the normal dispatch of the
	 * packet to the normal packet handler or downstream handler (in case of override).
	 * 
	 * @return True to cancel further processing of the packet. False to route the packet normally.
	 */
	public abstract boolean handleChatMessage(String message);
}
