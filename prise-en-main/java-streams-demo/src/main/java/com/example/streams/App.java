package com.example.streams;

import com.example.streams.demo.CollectorsDemo;
import com.example.streams.demo.IntermediateOpsDemo;
import com.example.streams.demo.PrimitiveStreamsDemo;
import com.example.streams.demo.ReduceDemo;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Java Streams Demo ===");
        IntermediateOpsDemo.run();
        CollectorsDemo.run();
        ReduceDemo.run();
        PrimitiveStreamsDemo.run();
        System.out.println("=== Fin de la démo ===");
    }
}
