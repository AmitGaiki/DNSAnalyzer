
import java.io.Serializable;

import org.jnetpcap.packet.PcapPacket;

/**
 * Packet class extends PcapPacket and implements serializable interface. It is
 * used because PcapPacket does not implement serializable.
 *
 * @author amit
 */
public class Packet extends PcapPacket implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * setter for Packet that instantiates the PcapPacket class by calling the constructor
	 * @param instance of PcapPacket.
	 */
	Packet(PcapPacket packet) {
		super(packet);
	}
}
