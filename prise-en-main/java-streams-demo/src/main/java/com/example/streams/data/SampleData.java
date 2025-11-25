package com.example.streams.data;

import com.example.streams.model.Person;

import java.util.List;

public class SampleData {
    public static List<Person> people() {
        return List.of(
                new Person("Alice", 30, "Paris", List.of("Java", "SQL", "Docker")),
                new Person("Bob", 22, "Lyon", List.of("Kotlin", "Git", "React")),
                new Person("Chloé", 35, "Paris", List.of("Java", "Spring", "Kubernetes")),
                new Person("David", 28, "Marseille", List.of("Python", "Pandas")),
                new Person("Emma", 42, "Toulouse", List.of("Java", "AWS", "Terraform")),
                new Person("Bob", 22, "Lyon", List.of("Kotlin", "Git", "React")) // duplicat volontaire pour distinct()
        );
    }

    public static List<String> sentences() {
        return List.of(
                "les streams rendent le code plus déclaratif",
                "map flatMap filter collect",
                "java vingt et un"
        );
    }
}
