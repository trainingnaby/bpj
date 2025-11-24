package tp.optional.legacy;

/**
 * Utilisateur "legacy" : la ville peut être null.
 */
public class LegacyUser {

	private final String email;
	private final String city; // peut être null

	public LegacyUser(String email, String city) {
		this.email = email;
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public String getCity() {
		return city;
	}
}
