package com.example.equalsworkshop.getclass;

import java.util.Objects;

public class Device {

	private final String serialNumber;

	public Device(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
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
		Device other = (Device) obj;
		return Objects.equals(this.serialNumber, other.serialNumber);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(serialNumber);
	}
	

}
