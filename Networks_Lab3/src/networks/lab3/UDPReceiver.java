package networks.lab3;

import java.io.*;
import java.net.*;

public class UDPReceiver {
	
	String[] window;
	
	public static void main(String args[])throws Exception
	{
		DatagramSocket receiverSocket = new DatagramSocket(9876);
		
		byte[] rcvData = new byte[1024];
		
		DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);
		
		receiverSocket.receive(rcvPkt);
		
		InetAddress IPAddress = rcvPkt.getAddress();
			
		int port = rcvPkt.getPort();					
	}
	
	/*public DatagramPacket ReceiveData(){
		
		byte[] receiveData = new byte[1024];
		
		return new DatagramPacket(receiveData, receiveData.length);
	}*/
	
	//Initializing a new window 
	public void newWindow(int windowSize){
		window = new String[windowSize];
		
		for (int i = 0; i < windowSize; i++){
			window[i] = String.valueOf(i);
		}
	}
	
	//Shifts window to the right	
	public String[] shiftWindow(String[] window, int sequenceNumber) {
    
        for (int i = 0; i < window.length - 1; i++) {
            window[i] = window[i + 1];
        }
        
        return window;
    }
	
	//Prints the window
	public void printWindow(String[] window){
		
		System.out.print("[");
		for (int i = 0; i < window.length; i++){
			System.out.println(window[i] + ",");
		}
		System.out.print("]");
	}
	
}