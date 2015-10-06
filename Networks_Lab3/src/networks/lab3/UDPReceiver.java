package networks.lab3;

import java.io.*;
import java.net.*;

public class UDPReceiver {
	
	private int windowSize;
	private int maxSequenceNum;
	private DatagramSocket receiverSocket;
	private int senderPort;
	private InetAddress senderIP;
	private String[] window;
	
	UDPReceiver() throws Exception{
		this.receiverSocket = new DatagramSocket(9876);
	}
	
	public void listenForData() throws Exception{
		
		byte[] rcvData = new byte[1024];
		DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);
		System.out.println("Waiting for data...");
		this.receiverSocket.receive(rcvPkt);
		System.out.println("I received data!");
		this.senderPort = rcvPkt.getPort(); // Get the sender's port #
		this.senderIP = rcvPkt.getAddress(); // Get the sender's IPAddress
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
			// sendAck(-1)
		}
		else if(dataParts[0].equals("data")){
			// Handle data after initial
		}
		else{
			
		}
		
	}
	
	private void sendAck(int i) throws IOException{
		
		String ack = "";
		byte[] stuffToReturn;
		
		if(i == -1){ // If act is for the initial data
			//send initial ack
			ack = buildAck(i);
			stuffToReturn = ack.getBytes(); // Converting built ack into a byte array
			DatagramPacket packetToSend = new DatagramPacket(
					stuffToReturn, stuffToReturn.length, this.senderIP, 9876); // Configure packet to be sent
			this.receiverSocket.send(packetToSend); // Send configured initial packet
		}
		else{
			//send ack for received packet
//			DatagramPacket packetToSend = new DatagramPacket(
//					stuffToReturn, stuffToReturn.length, this.senderIP, 9876); // Configure packet to be sent
		}
	}
	
	private String buildAck(int i){
		
		String acknowledgement = "";
		if (i == -1){ // If building inital ack
			acknowledgement = "initial|1";
		}
		else {
			// build ack for a regular sequence number
		}
		return acknowledgement;
	}
	
	public void DropPacket(){
		//When to drop packet?
	}
	
	public void newWindow(int windowSize){
		
		window = new String[windowSize];
		
		for (int i = 0; i < windowSize; i++){
			window[i] = String.valueOf(i);
		}
	}
	
	//shifts window to the right
	public String[] shiftWindow(String[] window, int sequenceNumber) {
		    
		for (int i = 0; i < window.length - 1; i++) {
			window[i] = window[i + 1];
			}
		return window;
	}
	
	public void printWindow(String[] window){
		
		System.out.print("[");
		for (int i = 0; i < window.length; i++){
			System.out.println(window[i] + ",");
		}
		System.out.print("]");
	}
	
	public static void main(String args[])throws Exception
	
	{
		UDPReceiver receiver = new UDPReceiver();
		
		while(true){
			receiver.listenForData();
		}
	}
}
