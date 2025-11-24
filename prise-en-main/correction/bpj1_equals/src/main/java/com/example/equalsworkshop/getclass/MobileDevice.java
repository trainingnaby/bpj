package com.example.equalsworkshop.getclass;

import java.util.Objects;

public class MobileDevice extends Device {

	private final String imei;
	
	public MobileDevice(String serialNumber, String imei) {
		super(serialNumber);
		this.imei = imei;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		// Utilisation de getClass() au lieu de instanceof
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		MobileDevice other = (MobileDevice) obj;
		return super.equals(other) && 
			   java.util.Objects.equals(this.imei, other.imei);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSerialNumber(), imei);
	}

}
