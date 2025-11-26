package com.example.minilab;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Menu {
    private static void pause() {
        try {
            System.out.println("\n(Entrée pour continuer)");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (Exception ignored) {}
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== Mini TP Java Memory ===");
        System.out.println("1) Stack vs Heap");
        System.out.println("2) JIT Warmup");
        System.out.println("3) Simple GC");
        System.out.println("Lancez les classes individuellement ou utilisez ces touches.");
        pause();
        StackVsHeap.main(new String[0]);
        pause();
        JitWarmup.main(new String[0]);
        pause();
        SimpleGCDemo.main(new String[0]);
    }
}
