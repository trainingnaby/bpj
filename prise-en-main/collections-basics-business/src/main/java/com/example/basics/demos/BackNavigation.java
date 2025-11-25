package com.example.basics.demos;

import java.util.*;

// Navigation arrière (LIFO): pile de pages visitées
public class BackNavigation {
	private final ArrayDeque<String> stack = new ArrayDeque<>(); // LIFO

	public void visit(String page) {
		stack.push(page);
	} // push en tête

	public Optional<String> back() {
		return stack.isEmpty() ? Optional.empty() : Optional.of(stack.pop());
	}

	public int size() {
		return stack.size();
	}

	public static void demo() {
		var nav = new BackNavigation();
		nav.visit("/home");
		nav.visit("/catalog");
		nav.visit("/product/42");
		System.out.println("[BackNavigation] back-> " + nav.back().orElse("-") + ", size=" + nav.size());
	}
}
