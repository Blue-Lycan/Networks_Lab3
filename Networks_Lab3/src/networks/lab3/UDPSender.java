package networks.lab3;

import java.io.*;
import java.net.*;

public class UDPSender {
	
	public static void main(String args[]) throws Exception
	{
		DatagramSocket senderSocket = new DatagramSocket(9877);
		
		InetAddress IPAddress = InetAddress.getByName("localhost");
		
		byte[] sendData = new byte[1024];
		
		DatagramPacket sendPkt = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		
		senderSocket.send(sendPkt);
	}
	
}
