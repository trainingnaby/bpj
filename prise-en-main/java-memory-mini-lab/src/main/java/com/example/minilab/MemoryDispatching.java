package com.example.minilab;

public class MemoryDispatching {

	// ====== Un petit objet pour la démo (vit sur la HEAP) ======
	static final class Person {
		String name; // String et Person sont des objets -> HEAP
		int age; // champ primitif -> fait partie de l'objet en HEAP
		Person friend; // référence vers un autre Person -> HEAP (la ref elle-même est stockée dans
						// l'objet)

		Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return name + "(" + age + ")";
		}
	}

	public static void main(String[] args) throws Exception {
		// ====== STACK (main frame) ======
		int localPrimitive = 42; // primitif -> STACK (dans la frame de main)
		Person alice = new Person("Alice", 30); // objet -> HEAP, référence 'alice' -> STACK
		Person bob = new Person("Bob", 25); // objet -> HEAP, référence 'bob' -> STACK
		alice.friend = bob; // HEAP->HEAP (modifie le champ de l'objet Alice, qui est en HEAP)

		int[] numbers = new int[3]; // tableau -> HEAP, référence 'numbers' -> STACK
		numbers[0] = 1;

		System.out.println("== main ==");
		printRefs("main", localPrimitive, alice, bob, numbers);

		// Passe des RÉFÉRENCES (copiées sur la STACK) à une autre méthode
		step1(localPrimitive, alice, numbers);

		System.out.println("\nPause 3s pour pouvoir attacher jvisualvm/jmc si vous voulez...");
		Thread.sleep(30_000);
	}

	private static void step1(int value, Person p, int[] arr) {
		// ====== STACK (frame step1) ======
		int x = value + 1; // primitif -> STACK
		Person tmp = new Person("Eve", 20); // nouvel objet -> HEAP, ref 'tmp' -> STACK

		// On modifie l'OBJET pointé par 'p' (Alice) et le tableau 'arr' -> effets
		// visibles aussi dans main()
		p.age++;
		arr[1] = 99;

		System.out.println("\n== step1 ==");
		printRefs("step1", x, p, tmp, arr);

		// Appel imbriqué pour visualiser plusieurs frames (STACK)
		step2(p);
	}

	private static void step2(Person someone) {
		// ====== STACK (frame step2) ======
		int depthMarker = 123; // primitif -> STACK
		System.out.println("\n== step2 ==");
		printRefs("step2", depthMarker, someone, null, null);

		// Rien d'autre : on sort -> la FRAME est détruite, mais les OBJETS HEAP vivent
		// encore si référencés
	}

	// Petit utilitaire pour visualiser l'identité des objets HEAP
	// (identityHashCode)
	private static void printRefs(String where, int val, Person a, Person b, int[] arr) {
		System.out.printf("[%s] primitive=%d%n", where, val);
		if (a != null) {
			System.out.printf("[%s] Person a=%s | id=%s%n", where, a, hexId(a));
		}
		if (b != null) {
			System.out.printf("[%s] Person b=%s | id=%s%n", where, b, hexId(b));
		}
		if (arr != null) {
			System.out.printf("[%s] int[] arr length=%d | id=%s | contents={%d,%d,%d}%n", where, arr.length, hexId(arr),
					arr[0], arr[1], arr[2]);
		}
	}

	private static String hexId(Object o) {
		// Ce n’est PAS une adresse mémoire, mais un identifiant stable (hash
		// d'identité)
		return "0x" + Integer.toHexString(System.identityHashCode(o));
	}
}
