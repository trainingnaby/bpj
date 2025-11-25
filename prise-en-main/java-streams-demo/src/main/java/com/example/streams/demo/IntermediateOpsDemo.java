package com.example.streams.demo;

import com.example.streams.data.SampleData;
import com.example.streams.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IntermediateOpsDemo {

    public static void run() {
        System.out.println("\n--- Opérations intermédiaires: map, filter, flatMap, distinct, sorted, peek ---");

        List<Person> people = SampleData.people();

        // filter: garder les adultes de plus de 30 ans
        var over30 = people.stream()
                .filter(p -> p.getAge() > 30)
                .collect(Collectors.toList());
        System.out.println("filter (>30): " + over30);

        // map: transformer vers les noms en MAJUSCULES
        var upperNames = people.stream()
                .map(p -> p.getName().toUpperCase())
                .collect(Collectors.toList());
        System.out.println("map (name -> UPPER): " + upperNames);

        // flatMap: aplatir les skills de toutes les personnes
        var allSkills = people.stream()
                .flatMap(p -> p.getSkills().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("flatMap + distinct + sorted (skills): " + allSkills);

        // distinct sur des objets (nécessite equals/hashCode)
        var distinctPeople = people.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct (people): " + distinctPeople);

        // peek: debug non-terminal (à utiliser avec parcimonie)
        var debugFlow = people.stream()
                .peek(p -> System.out.println("peek 1: " + p.getName()))
                .filter(p -> p.getAge() >= 25)
                .peek(p -> System.out.println("peek 2 (>=25): " + p.getName()))
                .map(Person::getCity)
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("pipeline final (villes): " + debugFlow);
    }
}
