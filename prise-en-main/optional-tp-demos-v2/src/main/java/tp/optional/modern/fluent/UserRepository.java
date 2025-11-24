package tp.optional.modern.fluent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Repository moderne : utilise Optional pour exprimer "pas trouvé".
 */
public class UserRepository {

    private final Map<String, User> storage = new HashMap<>();

    public UserRepository() {
        storage.put("alice@mail.com", new User("alice@mail.com", "Paris"));
        storage.put("bob@mail.com", new User("bob@mail.com", null)); // ville inconnue
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(storage.get(email));
    }
}
