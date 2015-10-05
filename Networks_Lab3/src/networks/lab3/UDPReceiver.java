package networks.lab3;

import java.io.*;
import java.net.*;

public class UDPReceiver {
	
	private int windowSize;
	private int maxSequenceNum;
	private DatagramSocket receiverSocket;
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
