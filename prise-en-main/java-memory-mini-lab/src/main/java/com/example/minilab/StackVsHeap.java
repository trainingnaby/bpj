package com.example.minilab;

import java.util.ArrayList;
import java.util.List;

public class StackVsHeap {
    static int depth = 0;

    static void recurse(int n) {
        depth++;
        int a = n + 1;   // variables locales sur la stack
        long b = a * 2L; // toujours sur la stack
        if (depth % 500 == 0) {
            System.out.println("Profondeur " + depth + " (a=" + a + ", b=" + b + ")");
        }
        recurse(a);
    }

    public static void main(String[] args) {
        System.out.println("[Stack] Essayez -Xss256k puis -Xss2m");
        try {
            recurse(0);
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError à la profondeur: " + depth);
        }

        System.out.println("\n[Heap] Allocations d'objets (byte[])");
        List<byte[]> keeper = new ArrayList<>();
        try {
            for (int i = 0; i < 100; i++) {
                keeper.add(new byte[1024 * 1024]); // 1 MB sur la heap
                System.out.println("Heap ~" + (i + 1) + " MB alloués");
                // objets éphémères qui meurent vite (pas stockés)
                for (int k = 0; k < 50_000; k++) {
                	byte[] tmp = new byte[256];
                }
            }
        } catch (OutOfMemoryError oom) {
            System.out.println("OutOfMemoryError: augmentez -Xmx ou réduisez le nombre d'allocations.");
        }
    }
}
