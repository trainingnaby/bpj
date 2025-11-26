package com.example.tp.executor;

import java.util.concurrent.*;

public class ExecutorAndFutureBasics {

	public static void main(String[] args) throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(3);

		Future<Integer> f = pool.submit(() -> {
			System.out.println("[task] Calcul en " + Thread.currentThread().getName());
			Thread.sleep(800);
			return 42;
		});

		System.out.println("[main] Je fais autre chose...");
		try {
			Integer res = f.get(2, TimeUnit.SECONDS);
			System.out.println("[main] Résultat = " + res);
		} catch (TimeoutException e) {
			System.out.println("[main] Trop long, j'annule");
			f.cancel(true);
		}

		Future<Integer> g = pool.submit(() -> {
			throw new IllegalStateException("Oups !");
		});
		try {
			g.get();
		} catch (ExecutionException ee) {
			System.out.println("[main] Exception via Future : " + ee.getCause());
		}

		pool.shutdown();
	}
}
