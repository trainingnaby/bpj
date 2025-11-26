package com.example.tp.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CFErrorHandling {

    static CompletableFuture<Integer> failing() {
        return CompletableFuture.supplyAsync(() -> {
            throw new IllegalStateException("Défaillance simulée");
        });
    }

    public static void main(String[] args) {
        int v1 = failing()
                .exceptionally(ex -> {
                    System.out.println("[exceptionally] " + ex);
                    return -1;
                })
                .join();
        System.out.println("v1 = " + v1);

        int v2 = CompletableFuture.supplyAsync(() -> 10)
                .thenApply(x -> x / 0)
                .handle((val, ex) -> {
                    if (ex != null) {
                        System.out.println("[handle] " + ex);
                        return 0;
                    }
                    return val;
                })
                .join();
        System.out.println("v2 = " + v2);

        int v3 = CompletableFuture.supplyAsync(() -> 5)
                .whenComplete((val, ex) -> {
                    System.out.println("[whenComplete] val=" + val + ", ex=" + ex);
                })
                .thenApply(x -> x + 1)
                .join();
        System.out.println("v3 = " + v3);

        int v4 = CompletableFuture.supplyAsync(() -> {
                    try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
                    return 99;
                })
                .completeOnTimeout(-999, 800, java.util.concurrent.TimeUnit.MILLISECONDS)
                .join();
        System.out.println("v4 = " + v4);

        try {
            CompletableFuture.supplyAsync(() -> {
                        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
                        return 1;
                    })
                    .orTimeout(500, TimeUnit.MILLISECONDS)
                    .join();
        } catch (Exception e) {
            System.out.println("[orTimeout] a bien expiré: " + e.getClass().getSimpleName());
        }
    }
}
