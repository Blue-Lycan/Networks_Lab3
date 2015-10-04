package networks.lab3;

import java.io.*;
import java.net.*;

public class UDPReceiver {
	
	private int windowSize;
	private int maxSequenceNum;
	private DatagramSocket receiverSocket;
	
	UDPReceiver() throws Exception{
		this.receiverSocket = new DatagramSocket(9876);
	}
	
	public void listenForData() throws Exception{
		
		byte[] rcvData = new byte[1024];
		DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);
		System.out.println("Waiting for data...");
		this.receiverSocket.receive(rcvPkt);
		System.out.println("I received data!");
		processData(rcvData);
	}
	
	private void processData(byte[] data){
		
		String bytesAsString = new String(data);
		System.out.println("String from bytes is: " + bytesAsString);

		String[] dataParts = bytesAsString.split("\\|"); // Split string based on pipe character
		
		if(dataParts[0].equals("initial")){ // If received data is the initial data
			System.out.println("Handling initial String.");
			this.windowSize = Integer.parseInt(dataParts[1]);
			this.maxSequenceNum = Integer.parseInt(dataParts[2]);
		}
		else if(dataParts[0].equals("data")){
			// Handle data after initial
		}
		else{
			
		}
	}
	public static void main(String args[])throws Exception
	
	{
		UDPReceiver receiver = new UDPReceiver();
}}