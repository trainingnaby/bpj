package com.example.minilab;

import java.util.ArrayList;
import java.util.List;

public class SimpleGCDemo {
    private static volatile int SINK;           // anti-optimisation JIT

    public static void main(String[] args) throws Exception {
        System.out.println("Simple GC Demo | Utilisez -Xms64m -Xmx64m -Xlog:gc* pour voir des cycles plus fréquents");

        // tableay d'objets qui survivent un peu (pour remplir Survivor/Old)
        List<byte[]> bag = new ArrayList<>();
        long total = 0;

        // petite pause pour attacher un outil si besoin
        Thread.sleep(1000);

        for (int sec = 1; sec <= 30; sec++) {               // un peu plus long
            for (int i = 0; i < 500_000; i++) {             // plus d’allocations
                byte[] tmp = new byte[256];                 // HEAP
                tmp[0] = (byte) i;                          // touche le tableau
                SINK ^= tmp[0];                             // empêche l’élimination

                // garder 1 objet de temps en temps pour survivre à la young GC
                if ((i & 0x7FFF) == 0) {                    // ~toutes les 32768 allocs
                    bag.add(tmp);
                }
            }
            // éviter une croissance infinie de "bag"
            if (bag.size() > 2000) bag.clear();

            total += 500_000L * 256;
            System.out.printf("t=%2ds | alloué cumul ~ %.1f MB | bag=%d%n",
                    sec, total / (1024.0 * 1024.0), bag.size());

            Thread.sleep(300);
        }
        System.out.println("Terminé. Regardez les lignes 'Pause Young'.");
    }
}
