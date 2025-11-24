package tp.optional.legacy;

/**
 * Démo principale pour la version "legacy" (sans Optional).
 */
public class LegacyOptionalDemoMain {

	public static void main(String[] args) {
		LegacyUserRepository repository = new LegacyUserRepository();
		LegacyUserService service = new LegacyUserService(repository);

		System.out.println("=== getUserCityUppercase (legacy) ===");
		System.out.println("alice@mail.com -> " + service.getUserCityUppercase("alice@mail.com"));
		System.out.println("bob@mail.com   -> " + service.getUserCityUppercase("bob@mail.com"));
		System.out.println("inconnu@mail.com -> " + service.getUserCityUppercase("inconnu@mail.com"));

		System.out.println();
		System.out.println("=== sendCityBasedPromotion (legacy) ===");
		service.sendCityBasedPromotion("alice@mail.com");
		service.sendCityBasedPromotion("bob@mail.com");
		service.sendCityBasedPromotion("inconnu@mail.com");
	}
}
