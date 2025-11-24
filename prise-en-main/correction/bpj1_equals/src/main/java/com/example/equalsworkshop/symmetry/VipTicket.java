package com.example.equalsworkshop.symmetry;

public class VipTicket extends Ticket {
	
	private final boolean loungeAccess;

	public VipTicket(String eventCode, int seat, boolean loungeAccess) {
		super(eventCode, seat);
		this.loungeAccess = loungeAccess;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof VipTicket)) {
			return false;
		}
		VipTicket other = (VipTicket) obj;

		return super.equals(other) && this.loungeAccess == other.loungeAccess;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + (Boolean.hashCode(loungeAccess) * 31);
	}

}
