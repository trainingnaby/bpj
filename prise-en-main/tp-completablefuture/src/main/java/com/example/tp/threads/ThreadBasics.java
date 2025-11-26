package com.example.tp.threads;

public class ThreadBasics {

	static class MonThread extends Thread {
		@Override
		public void run() {
			System.out.println("[MonThread] Hello depuis " + Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[main] Démarrage sur " + Thread.currentThread().getName());

		Thread t1 = new MonThread();
		t1.setName("T-heritage");
		t1.start();

		Runnable r = () -> System.out.println("[Runnable] Hello depuis " + Thread.currentThread().getName());
		Thread t2 = new Thread(r, "T-runnable");
		t2.start();

		Thread t3 = new Thread(() -> {
			try {
				Thread.sleep(300);
			} catch (InterruptedException ignored) {
			}
			System.out.println("[lambda] Fin dans " + Thread.currentThread().getName());
		}, "T-lambda");
		t3.start();

		t1.join();
		t2.join();
		t3.join();
		System.out.println("[main] Tous les threads sont terminés.");
	}
}
