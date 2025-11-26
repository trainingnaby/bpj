package com.example.minilab;

public class JitWarmup {

	private static long hot(int iters) {
		long s = 0;
		for (int i = 0; i < iters; i++) {
			s += (i * 31L) ^ (s >>> 3);
		}
		return s;
	}

	public static void main(String[] args) {
		System.out.println("Essayez -Xint (interprété) vs défaut (-Xmixed)");
		int warmup = 3, runs = 5, iters = 20_000_000;
		for (int i = 0; i < warmup; i++) {
			long t0 = System.nanoTime();
			long r = hot(iters);
			long ms = (System.nanoTime() - t0) / 1_000_000;
			System.out.printf("Warmup %d: res=%d, %d ms%n", i + 1, r, ms);
		}
		System.out.println("--- Mesure ---");
		for (int i = 0; i < runs; i++) {
			long t0 = System.nanoTime();
			long r = hot(iters);
			long ms = (System.nanoTime() - t0) / 1_000_000;
			System.out.printf("Run %d: res=%d, %d ms%n", i + 1, r, ms);
		}
	}
}
