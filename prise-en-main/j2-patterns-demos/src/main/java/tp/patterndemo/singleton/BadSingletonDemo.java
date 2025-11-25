package tp.patterndemo.singleton;

/**
 * Exemple de "mauvais singleton" : global, mutable, pas thread-safe.
 */
public class BadSingletonDemo {

	static class GlobalConfig {
		// Champ static public -> accessible/modifiable directement partout
		public static String currentEnvironment = "DEV";
	}

	public static void main(String[] args) {
		System.out.println("Env initial : " + GlobalConfig.currentEnvironment);

		// N'importe quel code peut changer la config sans contrôle :
		GlobalConfig.currentEnvironment = "PROD";

		System.out.println("Env après modification sauvage : " + GlobalConfig.currentEnvironment);

		System.out.println("""
				Problèmes :
				- Pas de contrôle d'accès.
				- Difficile à tester (tests qui se marchent dessus).
				- En multi-thread, race conditions possibles.
				""");
	}
}
