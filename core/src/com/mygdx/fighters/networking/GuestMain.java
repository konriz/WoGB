package com.mygdx.fighters.networking;

import java.util.Scanner;

public class GuestMain {

	public static void main(String[] args) {
		
		Client client = new Client();
		client.connect();
		
		System.out.println("Message to send:");
		Scanner sc = new Scanner(System.in);
		
		client.sendData(new ChatCommand(sc.nextLine()));
		sc.close();
		client.disconnect();

	}

}
