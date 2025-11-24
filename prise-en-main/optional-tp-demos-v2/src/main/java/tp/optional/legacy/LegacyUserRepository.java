package tp.optional.legacy;

import java.util.HashMap;
import java.util.Map;

/**
 * Repository "à l'ancienne" : renvoie null si l'utilisateur n'existe pas.
 */
public class LegacyUserRepository {

    private final Map<String, LegacyUser> storage = new HashMap<>();

    public LegacyUserRepository() {
        storage.put("alice@mail.com", new LegacyUser("alice@mail.com", "Paris"));
        storage.put("bob@mail.com", new LegacyUser("bob@mail.com", null)); // ville inconnue
    }

    /**
     * @param email email de l'utilisateur
     * @return LegacyUser si trouvé, null sinon.
     */
    public LegacyUser findByEmail(String email) {
        return storage.get(email); // null si pas trouvé
    }
}
