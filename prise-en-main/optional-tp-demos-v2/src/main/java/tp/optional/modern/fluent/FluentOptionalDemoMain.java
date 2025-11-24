package tp.optional.modern.fluent;

import java.util.Optional;

/**
 * Démo principale pour la version moderne (Optional, style fluent).
 */
public class FluentOptionalDemoMain {

	public static void main(String[] args) {
		UserRepository repository = new UserRepository();
		UserService service = new UserService(repository);

		System.out.println("=== getUserCityUppercase (fluent) ===");
		printOptional("alice@mail.com", service.getUserCityUppercase("alice@mail.com"));
		printOptional("bob@mail.com", service.getUserCityUppercase("bob@mail.com"));
		printOptional("inconnu@mail.com", service.getUserCityUppercase("inconnu@mail.com"));

		System.out.println();
		System.out.println("=== getUserCityUppercaseOrDefault (fluent) ===");
		System.out.println("alice@mail.com -> " + service.getUserCityUppercaseOrDefault("alice@mail.com", "inconnue"));
		System.out.println("bob@mail.com   -> " + service.getUserCityUppercaseOrDefault("bob@mail.com", "inconnue"));
		System.out.println(
				"inconnu@mail.com -> " + service.getUserCityUppercaseOrDefault("inconnu@mail.com", "inconnue"));

		System.out.println();
		System.out.println("=== sendCityBasedPromotion (fluent) ===");
		service.sendCityBasedPromotion("alice@mail.com");
		service.sendCityBasedPromotion("bob@mail.com");
		service.sendCityBasedPromotion("inconnu@mail.com");
	}

	private static void printOptional(String email, Optional<String> opt) {
		System.out.println(email + " -> " + opt.map(v -> "'" + v + "'").orElse("EMPTY"));
	}
}
