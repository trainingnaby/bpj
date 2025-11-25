package com.example.streams.demo;

import com.example.streams.data.SampleData;
import com.example.streams.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorsDemo {

    public static void run() {
        System.out.println("\n--- Collecteurs: toList, toSet, toMap, groupingBy, partitioningBy, joining, summarizingInt ---");

        List<Person> people = SampleData.people();

        // toList / toSet
        List<String> names = people.stream().map(Person::getName).collect(Collectors.toList());
        Set<String> cities = people.stream().map(Person::getCity).collect(Collectors.toSet());
        System.out.println("toList (names): " + names);
        System.out.println("toSet (cities): " + cities);

        // toMap (nom -> âge), gérer les doublons de clé avec merge (garder le plus grand âge)
        Map<String, Integer> nameToAge = people.stream()
                .collect(Collectors.toMap(
                        Person::getName,
                        Person::getAge,
                        Integer::max
                ));
        System.out.println("toMap (name->age, merge=max): " + nameToAge);

        // groupingBy (ville -> personnes)
        Map<String, List<Person>> byCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity));
        System.out.println("groupingBy (city): " + byCity);

        // partitioningBy (adulte ?)
        Map<Boolean, List<Person>> adults = people.stream()
                .collect(Collectors.partitioningBy(Person::isAdult));
        System.out.println("partitioningBy (isAdult): " + adults);

        // joining sur les noms triés
        String joinedNames = people.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println("joining (noms triés, distinct): " + joinedNames);

        // summarizingInt (statistiques d'âge)
        var stats = people.stream()
                .collect(Collectors.summarizingInt(Person::getAge));
        System.out.println("summarizingInt (ages): " + stats);
    }
}
