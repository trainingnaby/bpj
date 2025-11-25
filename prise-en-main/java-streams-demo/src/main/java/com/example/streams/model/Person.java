package com.example.streams.model;

import java.util.List;
import java.util.Objects;

public class Person {
    private final String name;
    private final int age;
    private final String city;
    private final List<String> skills;

    public Person(String name, int age, String city, List<String> skills) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.skills = List.copyOf(skills);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public List<String> getSkills() {
        return skills;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    @Override
    public String toString() {
        return name + " (" + age + ", " + city + ", skills=" + skills + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(city, person.city) && Objects.equals(skills, person.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, city, skills);
    }
}
