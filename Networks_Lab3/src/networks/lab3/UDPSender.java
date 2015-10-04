package networks.lab3;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UDPSender {
	
	private int windowSize;
	private int maxSequenceNum;
	private int[] packetsDropped;
	
	public static void main(String args[]) throws Exception
	{
		UDPSender sender = new UDPSender();
		
		DatagramSocket senderSocket = new DatagramSocket(9877);
		
		InetAddress IPAddress = InetAddress.getByName("localhost"); // Server IP
		
		byte[] sendData = new byte[1024];
		
		DatagramPacket sendPkt = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		
		senderSocket.send(sendPkt);
	}
	
	UDPSender(){
		this.getUserInput();
	}
	
	// Get user input
	private void getUserInput(){
		Scanner sc = new Scanner(System.in);
	
		try {
			System.out.print("Enter the window's size on sender: ");
			this.windowSize = sc.nextInt();
			
			System.out.print("Enter the maximum sequence number on sender: ");
			this.maxSequenceNum = sc.nextInt();
			sc.nextLine(); // Clear scanner for upcoming next line
			
			System.out.print("Enter packets to be dropped, seperated by a space: ");
			String input = sc.nextLine();
		    StringTokenizer strToken = new StringTokenizer(input);
		    int count = strToken.countTokens();
		    packetsDropped = new int[count];
		    
		    for(int i = 0; i < count; i++){
		        packetsDropped[i] = Integer.parseInt((String)strToken.nextElement());
		    }
		}
		
		catch(Exception e) {
			System.out.println("Invalid input, please try again.");
			this.getUserInput(); // Retry
		}	
	}
}
