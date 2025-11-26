package com.example.tp.threads;

public class SynchronizedCounter {

    static class CounterUnsafe {
        private int value = 0;
        public void inc() { value++; }
        public int get() { return value; }
    }

    static class CounterSafe {
        private int value = 0;
        public synchronized void inc() { value++; }
        public synchronized int get() { return value; }
    }

    public static void main(String[] args) throws InterruptedException {
        int threads = 4;
        int incrementsPerThread = 10_000;

        CounterUnsafe cu = new CounterUnsafe();
        Thread[] arr1 = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            arr1[i] = new Thread(() -> {
                for (int k = 0; k < incrementsPerThread; k++) cu.inc();
            }, "unsafe-" + i);
            arr1[i].start();
        }
        for (Thread t : arr1) t.join();
        System.out.println("[UNSAFE] Attendu = " + (threads * incrementsPerThread) + ", obtenu = " + cu.get());

        CounterSafe cs = new CounterSafe();
        Thread[] arr2 = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            arr2[i] = new Thread(() -> {
                for (int k = 0; k < incrementsPerThread; k++) cs.inc();
            }, "safe-" + i);
            arr2[i].start();
        }
        for (Thread t : arr2) t.join();
        System.out.println("[SAFE]   Attendu = " + (threads * incrementsPerThread) + ", obtenu = " + cs.get());
    }
}
