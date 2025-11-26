package com.example.tp.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class CFComposeAndCombine {

    static CompletableFuture<Integer> remoteA() {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
            return 10;
        });
    }

    static CompletableFuture<Integer> remoteB(int x) {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
            return x * 2;
        });
    }

    static CompletableFuture<Integer> remoteC() {
        return CompletableFuture.supplyAsync(() -> 7);
    }

    public static void main(String[] args) {
        Integer composeResult = remoteA()
                .thenCompose(a -> remoteB(a))
                .join();
        System.out.println("[thenCompose] => " + composeResult);

        Integer combineResult = remoteA()
                .thenCombine(remoteC(), Integer::sum)
                .join();
        System.out.println("[thenCombine] => " + combineResult);

        var custom = Executors.newFixedThreadPool(2);
        Integer withExec = CompletableFuture.supplyAsync(() -> 4, custom)
                .thenApplyAsync(x -> x * 3, custom)
                .join();
        custom.shutdown();
        System.out.println("[custom-exec] => " + withExec);
    }
}
