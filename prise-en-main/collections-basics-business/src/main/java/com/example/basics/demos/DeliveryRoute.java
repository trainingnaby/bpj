package com.example.basics.demos;

import java.util.*;

// Tournée de livraisons: ajouter en début/fin selon l'urgence
public class DeliveryRoute {
	private final ArrayDeque<String> stops = new ArrayDeque<>();

	public void addUrgentStop(String stop) {
		stops.addFirst(stop);
	} // en tête

	public void addRegularStop(String stop) {
		stops.addLast(stop);
	} // en queue

	public Optional<String> nextStop() {
		return stops.isEmpty() ? Optional.empty() : Optional.of(stops.removeFirst());
	}

	public List<String> snapshot() {
		return List.copyOf(stops);
	}

	public static void demo() {
		var r = new DeliveryRoute();
		r.addRegularStop("S1");
		r.addRegularStop("S2");
		r.addUrgentStop("URG");
		System.out.println("[DeliveryRoute] order=" + r.snapshot() + ", next=" + r.nextStop().orElse("-"));
	}
}
