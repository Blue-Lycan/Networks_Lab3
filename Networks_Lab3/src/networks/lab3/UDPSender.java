package networks.lab3;

/**
 * @author Drew Antonich & Anthony Schwartz
 * 
 * UDPSender
 * 
 * Sender class of a UDP selective repeat implementation.
 * 
 * This class first sends initial data to receiver to agree
 * upon a window size and maximum sequence number. For this implementation,
 * desired packets to be dropped can be added to simulate a real-world situation.
 * 
 * The data being sent to the receiver will either be the initial data or the regular
 * data containing a sequence number.
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UDPSender {
	
	private int windowSize;
	private int maxSequenceNum;
	private int[] packetsDropped;
	private DatagramSocket senderSocket;
	private InetAddress HostIPAddress;
	
	UDPSender() throws Exception {
		
		this.getUserInput();
		this.senderSocket = new DatagramSocket(9877); // Initialize socket for sender
		this.HostIPAddress = InetAddress.getByName("localhost"); // Host IP Address
	}
	
	// Get user input
	private void getUserInput(){
		
		Scanner scanner = new Scanner(System.in);
	
		try {
			System.out.print("Enter the window's size on sender: ");
			this.windowSize = scanner.nextInt();
			
			System.out.print("Enter the maximum sequence number on sender: ");
			this.maxSequenceNum = scanner.nextInt();
			scanner.nextLine(); // Clear scanner for upcoming next line
			
			System.out.print("Enter packets to be dropped, seperated by a space: ");
			String input = scanner.nextLine(); // Get line of desired packets to be dropped
		    StringTokenizer strToken = new StringTokenizer(input);
		    int count = strToken.countTokens();
		    packetsDropped = new int[count];
		    
		    for(int i = 0; i < count; i++){ // Add each pack number to array
		        packetsDropped[i] = Integer.parseInt((String)strToken.nextElement());
		    }
		}
		
		catch(Exception e) {
			System.out.println("Invalid input, please try again.");
			this.getUserInput(); // Retry
		}
		scanner.close(); // Close user input.
	}
	
	private void sendInitialData() throws Exception{
		
		byte[] testByteArray = this.buildInitialData().getBytes(); // Build initial data and convert to bytes
		DatagramPacket packetToSend = new DatagramPacket(
				testByteArray, testByteArray.length, this.HostIPAddress, 9876); // Configure packet to be sent
		this.senderSocket.send(packetToSend); // Send packet
	}
	
	/**
	 * Builds String to be the initial String sent to receiver.
	 * 
	 * The String contains the window size and max sequence number.
	 * 
	 * The String is pipe delimited to allow for the receiver to split and
	 * access each part of the data String.
	 * 
	 * @return String
	 */
	private String buildInitialData(){
		
		String initialString = "initial|"; // Mark this data as the initial data
		initialString += (Integer.toString(this.windowSize) + "|"); // append window size
		initialString += (Integer.toString(this.maxSequenceNum) + "|"); // append max sequence number
		
		System.out.println("The initial string is: " + initialString);
		
		return initialString;
	}
	
	public static void main(String args[]) throws Exception
		
	{
		UDPSender sender = new UDPSender();
		sender.sendInitialData();
	}
}
