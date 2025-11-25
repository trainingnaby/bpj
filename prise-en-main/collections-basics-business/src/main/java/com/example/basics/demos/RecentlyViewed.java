package com.example.basics.demos;

import java.util.*;

// Produits récemment consultés (Deque bornée): LIFO en tête, coupe en queue
public class RecentlyViewed {
	private final ArrayDeque<String> deque = new ArrayDeque<>();
	private final int capacity;

	public RecentlyViewed(int capacity) {
		this.capacity = capacity;
	}

	public void see(String productId) {
		deque.remove(productId); // éviter doublons
		deque.addFirst(productId); // plus récent en tête (LIFO pour la vue)
		if (deque.size() > capacity)
			deque.removeLast(); // couper le plus ancien
	}

	public List<String> list() {
		return List.copyOf(deque);
	}

	public static void demo() {
		var rv = new RecentlyViewed(3);
		rv.see("A");
		rv.see("B");
		rv.see("C");
		rv.see("B");
		rv.see("D");
		System.out.println("[RecentlyViewed] " + rv.list()); // D, B, C
	}
}
