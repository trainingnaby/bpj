package com.example.tp.executor;

import java.util.List;
import java.util.concurrent.*;

public class InvokeAllAnyDemo {

	public static void main(String[] args) throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(3);

		List<Callable<String>> tasks = List.of(() -> {
			Thread.sleep(600);
			return "A";
		}, () -> {
			Thread.sleep(300);
			return "B";
		}, () -> {
			Thread.sleep(500);
			return "C";
		});

		String first = pool.invokeAny(tasks);
		System.out.println("[invokeAny] Premier = " + first);

		List<Future<String>> futures = pool.invokeAll(tasks);
		for (Future<String> fut : futures) {
			try {
				System.out.println("[invokeAll] -> " + fut.get());
			} catch (ExecutionException e) {
				System.out.println("[invokeAll] erreur: " + e.getCause());
			}
		}

		pool.shutdown();
	}
}
