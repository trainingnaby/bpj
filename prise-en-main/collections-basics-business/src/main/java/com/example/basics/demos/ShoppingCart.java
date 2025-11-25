package com.example.basics.demos;

import java.util.*;

// Panier e-commerce: ordre d'ajout, doublons autorisés (2x le même article)
public class ShoppingCart {
	private final List<String> items = new ArrayList<>();

	public void add(String sku) {
		items.add(sku);
	}

	public void remove(String sku) {
		items.remove(sku);
	} // retire la 1ère occurrence

	public List<String> list() {
		return List.copyOf(items);
	}

	public long count(String sku) {
		return items.stream().filter(s -> s.equals(sku)).count();
	}

	public static void demo() {
		var cart = new ShoppingCart();
		cart.add("A-123");
		cart.add("B-999");
		cart.add("A-123");
		System.out.println("[ShoppingCart] " + cart.list() + " qty(A-123)=" + cart.count("A-123"));
	}
}
