package com.example.basics.demos;

import java.util.*;

// Destinataires newsletter: unicité par Set
public class NewsletterRecipients {
	private final Set<String> emails = new HashSet<>();

	public boolean add(String email) {
		return emails.add(email.toLowerCase(Locale.ROOT).trim());
	}

	public boolean contains(String email) {
		return emails.contains(email.toLowerCase(Locale.ROOT).trim());
	}

	public Set<String> snapshot() {
		return Set.copyOf(emails);
	}

	public static void demo() {
		var n = new NewsletterRecipients();
		n.add("alice@example.com");
		n.add("ALICE@example.com");
		n.add("bob@example.com");
		System.out.println("[NewsletterRecipients] " + n.snapshot()); // alice, bob
	}
}
