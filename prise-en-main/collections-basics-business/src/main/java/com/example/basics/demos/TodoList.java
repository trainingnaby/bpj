package com.example.basics.demos;

import java.util.*;

// To-do basique: garder l'ordre, monter/descendre les tâches
public class TodoList {
	private final List<String> tasks = new ArrayList<>();

	public void add(String t) {
		tasks.add(t);
	}

	public void moveUp(int index) {
		if (index <= 0 || index >= tasks.size())
			return;
		Collections.swap(tasks, index, index - 1);
	}

	public void moveDown(int index) {
		if (index < 0 || index >= tasks.size() - 1)
			return;
		Collections.swap(tasks, index, index + 1);
	}

	public List<String> list() {
		return List.copyOf(tasks);
	}

	public static void demo() {
		var todo = new TodoList();
		todo.add("payer facture");
		todo.add("appeler client");
		todo.add("envoyer devis");
		todo.moveUp(2); // remonter "envoyer devis"
		System.out.println("[TodoList] " + todo.list());
	}
}
