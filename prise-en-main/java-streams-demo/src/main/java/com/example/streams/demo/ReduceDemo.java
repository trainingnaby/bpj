package com.example.streams.demo;

import com.example.streams.data.SampleData;
import com.example.streams.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ReduceDemo {

    public static void run() {
        System.out.println("\n--- Reduce: somme, max, fusion ---");

        List<Person> people = SampleData.people();

        // reduce: somme des âges (avec identité)
        int totalAge = people.stream()
                .map(Person::getAge)
                .reduce(0, Integer::sum);
        System.out.println("reduce somme des âges: " + totalAge);

        // reduce: max (sans identité -> Optional)
        Optional<Integer> maxAge = people.stream()
                .map(Person::getAge)
                .reduce(Integer::max);
        System.out.println("reduce max âge: " + maxAge.orElse(null));

        // reduce: concaténer toutes les compétences dans une chaîne
        String skillsConcat = people.stream()
                .flatMap(p -> p.getSkills().stream())
                .distinct()
                .sorted()
                .reduce((a, b) -> a + " | " + b)
                .orElse("");
        System.out.println("reduce concat skills: " + skillsConcat);

        // reduce 3-arguments : accumulation immuable d'un total conditionnel
        record Acc(int count, int sum) {}
        Acc acc = people.stream()
                .filter(p -> p.getAge() >= 25)
                .map(Person::getAge)
                .reduce(new Acc(0, 0),
                        (a, age) -> new Acc(a.count()+1, a.sum()+age),
                        (a1, a2) -> new Acc(a1.count()+a2.count(), a1.sum()+a2.sum()));
        System.out.println("reduce (>=25) count=" + acc.count() + ", sum=" + acc.sum());

        // Variante: on aurait pu utiliser Collectors.averagingInt / summarizingInt
    }
}
