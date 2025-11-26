package com.example.tp.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * DEMO 7 — CompletableFuture (bases) avec EXECUTOR DÉDIÉ
 *
 * Objectif : montrer un pipeline non bloquant en contrôlant
 * explicitement les threads utilisés (au lieu du common ForkJoinPool).
 *
 * Points clés :
 * - Création d'un Executor dédié (2 threads nommés)
 * - supplyAsync / runAsync qui utilisent cet executor
 * - thenApplyAsync / thenAcceptAsync attachés au même executor
 * - Arrêt propre de l'executor à la fin
 */
public class CFBasics {

    public static void main(String[] args) {
        System.out.println("[main] start " + Thread.currentThread().getName());

        // 1) Un executor DÉDIÉ, pour bien voir quels threads s'exécutent
        ThreadFactory namedFactory = r -> {
            Thread t = new Thread(r);
            t.setName("cf-exec-" + t.getId());
            t.setDaemon(false);
            return t;
        };
        ExecutorService executor = Executors.newFixedThreadPool(2, namedFactory);

        // 2) Chaîne simple: supplyAsync -> thenApply -> thenApplyAsync -> thenAcceptAsync
        CompletableFuture<Void> pipeline =
            CompletableFuture.supplyAsync(() -> {
                log("step1: préparation de la valeur");
                sleep(300);
                return 10;
            }, executor)
            .thenApply(x -> {
                log("step2: calcul synchrone (même thread si possible)");
                return x * 2;
            })
            .thenApplyAsync(x -> {
                log("step3-async: calcul asynchrone (repasse par l'executor)");
                return x + 5;
            }, executor)
            .thenAcceptAsync(result -> {
                log("sink: consommation du résultat = " + result);
            }, executor);

        // 3) Exemple minimal avec runAsync (pas de valeur à transporter)
        CompletableFuture<Void> sideEffect =
            CompletableFuture.runAsync(() -> {
                log("runAsync: effet de bord (ex: log, purge, notif)");
                sleep(200);
            }, executor);

        // On attend les deux pour la démo
        CompletableFuture.allOf(pipeline, sideEffect).join();

        // 4) Fermeture PROPRE de l'executor dédié
        executor.shutdown();
        System.out.println("[main] end");
    }

    private static void log(String msg) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + msg);
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
