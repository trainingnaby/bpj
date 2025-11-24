package com.example.equalsworkshop.mutable;

import java.util.Set;

public class MutableMain {

	public static void main(String[] args) {
		Subscriber subscriber = new Subscriber("alex@hotmail.com");

		// creation d'un ensemble de subscribers positionné
		// dans le hashSet en fonction du hashCode initial
		Set<Subscriber> subscribers = new java.util.HashSet<>();
		subscribers.add(subscriber);

		System.out.println("subscribers.contains(subscriber): " + subscribers.contains(subscriber));

		// mutation de l'état interne impactant equals et hashCode
		subscriber.setEmail("alex@gmail.com");

		// recherche du subscriber dans le hashSet après mutation
		System.out.println("subscribers.contains(subscriber) after email change: " + subscribers.contains(subscriber));
		
		// le nouveau hashCode ne correspond plus à la position initiale dans le HashSet : la recherche
		// se fera sur un autre "bucket" et ne trouvera pas l'objet
		
		

	}

}
