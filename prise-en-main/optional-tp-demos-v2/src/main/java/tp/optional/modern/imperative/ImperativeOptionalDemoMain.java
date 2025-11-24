package tp.optional.modern.imperative;

import java.util.Optional;

/**
 * Démo principale pour la version moderne (Optional, style impératif).
 */
public class ImperativeOptionalDemoMain {

	public static void main(String[] args) {
		UserRepository repository = new UserRepository();
		UserService service = new UserService(repository);

		System.out.println("=== getUserCityUppercase (imperative) ===");
		printOptional("alice@mail.com", service.getUserCityUppercase("alice@mail.com"));
		printOptional("bob@mail.com", service.getUserCityUppercase("bob@mail.com"));
		printOptional("inconnu@mail.com", service.getUserCityUppercase("inconnu@mail.com"));

		System.out.println();
		System.out.println("=== getUserCityUppercaseOrDefault (imperative) ===");
		System.out.println("alice@mail.com -> " + service.getUserCityUppercaseOrDefault("alice@mail.com", "inconnue"));
		System.out.println("bob@mail.com   -> " + service.getUserCityUppercaseOrDefault("bob@mail.com", "inconnue"));
		System.out.println(
				"inconnu@mail.com -> " + service.getUserCityUppercaseOrDefault("inconnu@mail.com", "inconnue"));

		System.out.println();
		System.out.println("=== sendCityBasedPromotion (imperative) ===");
		service.sendCityBasedPromotion("alice@mail.com");
		service.sendCityBasedPromotion("bob@mail.com");
		service.sendCityBasedPromotion("inconnu@mail.com");
	}

	private static void printOptional(String email, Optional<String> opt) {
		if (opt.isPresent()) {
			System.out.println(email + " -> '" + opt.get() + "'");
		} else {
			System.out.println(email + " -> EMPTY");
		}
	}
}
