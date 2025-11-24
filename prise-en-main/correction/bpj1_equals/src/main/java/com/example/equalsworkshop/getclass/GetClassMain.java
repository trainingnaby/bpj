package com.example.equalsworkshop.getclass;

import java.util.ArrayList;
import java.util.List;

public class GetClassMain {

	public static void main(String[] args) {
		Device device = new Device("SN12345");
		MobileDevice mobileDevice = new MobileDevice("SN12345", "IMEI67890");

		// la comparaison avec getClass() empêche l'égalité entre Device et MobileDevice
		System.out.println("device.equals(mobileDevice): " + device.equals(mobileDevice));
		System.out.println("mobileDevice.equals(device): " + mobileDevice.equals(device));
		
		// Utilisation dans une collection
		List<Device> inventory = new ArrayList<>();
		inventory.add(device);
		System.out.println("inventory.contains(mobileDevice): " + inventory.contains(mobileDevice));
		
		
	}

}
