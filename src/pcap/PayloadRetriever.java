package pcap;


import org.jnetpcap.protocol.tcpip.Udp;

/**
 * This class implements all method used for getting udp payload from the packets captured.
 * @author amit
 */
public class PayloadRetriever {
	
	/**
	 * used to find the payload of the packet.
	 * @param packet instance of Packet class.
	 * @return payload in bytes for the packet
	 */
    public static byte[] getUDPPayload(Packet packet){
    	byte[] payload = null;
    	final Udp udp = new Udp();
    	if(packet.hasHeader(udp)){
    		if(udp.hasPayload()){
    			payload = udp.getPayload();
    		}
    	}
    	return payload;	
    }
}
