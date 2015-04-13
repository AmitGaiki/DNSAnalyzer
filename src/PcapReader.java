
import java.util.ArrayList;


import org.jnetpcap.Pcap;
import org.jnetpcap.nio.JMemory.Type;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.tcpip.Udp;

/**
 * This class holds all methods for reading packets from a Pcap file.
 * @author amit
 */
public class PcapReader{
     
	/**
	 * This methods reads the number of packets from the given file and returns list of packets.
	 * @param filename name of the pcap file
	 * @param numOfPackets total packets to read from the file
	 * @return list of packets read from the file
	 */
     public static ArrayList<Packet> readFile(String filename,int numOfPackets){
    	 Pcap captor;
    	 StringBuilder errbuffer = new StringBuilder();
		 captor = Pcap.openOffline(filename, errbuffer);
		 if (captor == null) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Unable to open pcap file.");
			System.out.println(errbuffer);
		}
		 
		PcapPacketHandler<ArrayList<Packet>> handler = new PcapPacketHandler<ArrayList<Packet>>(){

			public void nextPacket(PcapPacket packet, ArrayList<Packet> listOfPackets) {
				// TODO Auto-generated method stub
				final Udp udp = new Udp();
				if(packet.hasHeader(udp.getId())){
					Packet finalPacket = new Packet(packet);
					if(!listOfPackets.add(finalPacket)){
						System.out.println("Unable to add to list");
					}
						
				}
			}
			
	};
	
	ArrayList<Packet> packetList = new ArrayList<Packet>(100);
	if(numOfPackets == 0){
		captor.loop(Pcap.LOOP_INFINITE,handler,packetList);
	}
	else{
		captor.loop(numOfPackets,handler,packetList);
	}
	captor.close();
	
	return packetList;
	}
     
	 /**
	  * This method is used to get a packet from the given pcap file
	  * @param filename name of the pcap file
	  * @return The retrieved packet
	  */
     public static Packet readPacket(String filename){
    	 Pcap captor;
    	 StringBuilder errbuffer = new StringBuilder();
    	 final PcapPacket packet = new PcapPacket(Type.POINTER);
		 captor = Pcap.openOffline(filename, errbuffer);
		 if (captor == null) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Unable to open pcap file.");
			System.out.println(errbuffer);
		}
				// TODO Auto-generated method stub
				captor.nextEx(packet);
				final Udp udp = new Udp();
				if(packet.hasHeader(udp.getId())){
					return new Packet(packet);
				}
		return null;
	
     }
     
	 /**
	  * This method reads and returns a packet from pcap file at a certain offset value in the file.
	  * @param filename name of the pcap file
	  * @param offset offset count from the start of file
	  * @return packet at the given offset location.
	  */
     public static Packet readPacket(String filename,int offset){
    	 Pcap captor;
    	 int i = 0;
    	 StringBuilder errbuffer = new StringBuilder();
    	 final PcapPacket packet = new PcapPacket(Type.POINTER);
		 captor = Pcap.openOffline(filename, errbuffer);
		 if (captor == null) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Unable to open pcap file.");
			System.out.println(errbuffer);
		}
				// TODO Auto-generated method stub
		 for(i=0;i<=offset;i++){
				captor.nextEx(packet);
		 }
				final Udp udp = new Udp();
				if(packet.hasHeader(udp.getId())){
					return new Packet(packet);
				}
		return null;
     }
     
     public static void main(String[] args){
    	 String file = "data\\DNS_Traffic000.pcap";
    	 byte[] payload = PayloadRetriever.getUDPPayload(PcapReader.readPacket(file,0));
    	 System.out.println(Convert.toHex(payload));
     }
   
     
    
}
