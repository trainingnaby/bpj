package com.example.equalsworkshop.symmetry;

import java.util.Objects;

public class Ticket {

	private final String eventCode;

	private final int seat;

	public Ticket(String eventCode, int seat) {
		this.eventCode = eventCode;
		this.seat = seat;
	}

	public String getEventCode() {
		return eventCode;
	}

	public int getSeat() {
		return seat;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Ticket)) {
			return false;
		}
		Ticket other = (Ticket) obj;
		return Objects.equals(this.eventCode, other.eventCode) && this.seat == other.seat;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(eventCode, seat);
	}
	
	@Override
	public String toString() {
		return "Ticket[eventCode=" + eventCode + ", seat=" + seat + "]";
	}

}
