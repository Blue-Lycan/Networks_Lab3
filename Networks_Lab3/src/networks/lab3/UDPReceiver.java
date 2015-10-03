package networks.lab3;

import java.io.*;
import java.net.*;

public class UDPReceiver {
	
	public static void main(String args[])throws Exception
	{
		DatagramSocket receiverSocket = new DatagramSocket(9876);
		
		byte[] rcvData = new byte[1024];
		
		DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);
		
		receiverSocket.receive(rcvPkt);
		
		InetAddress IPAddress = rcvPkt.getAddress();
			
		int port = rcvPkt.getPort();					
	}
}

