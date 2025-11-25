package tp.patterndemo.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Démo : copie profonde (deep copy) d'un objet avec liste mutable.
 */
public class DeepCopyDemo {

	static class Report {
		String title;
		List<String> lines;

		Report(String title, List<String> lines) {
			this.title = title;
			this.lines = lines;
		}

		// Copie profonde
		Report deepCopy() {
			return new Report(this.title, new ArrayList<>(this.lines));
		}

		@Override
		public String toString() {
			return "Report{title='%s', lines=%s}".formatted(title, lines);
		}
	}

	public static void main(String[] args) {
		Report original = new Report("Rapport original", new ArrayList<>(List.of("L1", "L2")));

		Report copy = original.deepCopy();

		System.out.println("Avant modification :");
		System.out.println("original = " + original);
		System.out.println("copy     = " + copy);

		// On modifie la liste de la copie
		copy.lines.add("L3");

		System.out.println("\nAprès modification de copy.lines :");
		System.out.println("original = " + original);
		System.out.println("copy     = " + copy);

		System.out.println("""
				Observation :
				- original et copy ont des listes distinctes (deep copy).
				- Pattern Prototype : créer rapidement des variantes d'un "template" d'objet.
				""");
	}
}
