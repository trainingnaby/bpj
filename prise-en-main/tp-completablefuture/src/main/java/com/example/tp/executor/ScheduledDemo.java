package com.example.tp.executor;

import java.util.concurrent.*;

public class ScheduledDemo {

	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

		scheduler.schedule(() -> System.out.println("[once] après 1s"), 1, TimeUnit.SECONDS);

		ScheduledFuture<?> rate = scheduler.scheduleAtFixedRate(() -> {
			System.out.println("[fixedRate] " + System.currentTimeMillis() / 1000);
			try {
				Thread.sleep(700);
			} catch (InterruptedException ignored) {
			}
		}, 500, 500, TimeUnit.MILLISECONDS);

		ScheduledFuture<?> delay = scheduler.scheduleWithFixedDelay(() -> {
			System.out.println("    [fixedDelay] " + System.currentTimeMillis() / 1000);
			try {
				Thread.sleep(700);
			} catch (InterruptedException ignored) {
			}
		}, 500, 500, TimeUnit.MILLISECONDS);

		Thread.sleep(3000);
		rate.cancel(true);
		delay.cancel(true);

		scheduler.shutdown();
		scheduler.awaitTermination(2, TimeUnit.SECONDS);
		System.out.println("[main] Fin ScheduledDemo");
	}
}
