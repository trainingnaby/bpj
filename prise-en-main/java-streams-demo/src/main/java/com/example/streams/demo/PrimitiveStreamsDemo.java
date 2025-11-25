package com.example.streams.demo;

import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class PrimitiveStreamsDemo {

    public static void run() {
        System.out.println("\n--- Primitive Streams: IntStream, mapToInt, range, sum/average ---");

        IntStream numbers = IntStream.rangeClosed(1, 10); // 1..10
        int sum = numbers.sum();
        System.out.println("IntStream 1..10 sum: " + sum);

        // mapToInt depuis un Stream<String> numérique
        int squaresSum = java.util.stream.Stream.of("1","2","3","4","5")
                .mapToInt(Integer::parseInt)
                .map(n -> n * n)
                .sum();
        System.out.println("mapToInt + squares sum: " + squaresSum);

        // average avec OptionalDouble
        OptionalDouble avg = IntStream.of(10, 20, 30).average();
        System.out.println("average(10,20,30): " + (avg.isPresent() ? avg.getAsDouble() : "NA"));

        // statistiques résumées
        IntSummaryStatistics stats = IntStream.range(0, 100).summaryStatistics();
        System.out.println("summaryStatistics 0..99: " + stats);
    }
}
