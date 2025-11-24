package com.example.equalsworkshop.symmetry;

import java.util.HashSet;
import java.util.Set;

public class SymmetryMain {
	
	public static void main(String[] args) {
		Ticket ticket = new Ticket("EVENT123", 42);
		VipTicket vipTicket = new VipTicket("EVENT123", 42, true);
		
		// la symétrie de l'égalité est violée ici
		System.out.println("ticket.equals(vipTicket): " + ticket.equals(vipTicket));
		System.out.println("vipTicket.equals(ticket): " + vipTicket.equals(ticket));
		
		// Utilisation dans une collection
		Set<Ticket> gate = new HashSet<>();
		gate.add(ticket);
		System.out.println("gate.contains(vipTicket): " + gate.contains(vipTicket));
		System.out.println("gate.size(): " + gate.size());
	
		
	}

}
