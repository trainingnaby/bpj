package com.example.basics.demos;

import java.util.*;

// Présence réunion/formation: vérifier rapidement si "X est présent ?"
public class Attendance {
	private final Set<String> present = new HashSet<>();

	public void checkIn(String name) {
		present.add(name);
	}

	public void checkOut(String name) {
		present.remove(name);
	}

	public boolean isPresent(String name) {
		return present.contains(name);
	}

	public static void demo() {
		var a = new Attendance();
		a.checkIn("Alice");
		a.checkIn("Bob");
		a.checkOut("Alice");
		System.out.println("[Attendance] Bob? " + a.isPresent("Bob") + ", Alice? " + a.isPresent("Alice"));
	}
}
