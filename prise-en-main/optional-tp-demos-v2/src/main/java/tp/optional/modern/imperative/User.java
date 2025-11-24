package tp.optional.modern.imperative;

import java.util.Optional;

/**
 * Utilisateur moderne : l'accès à la ville se fait via Optional.
 */
public class User {

    private final String email;
    private final String city; // potentiellement null en interne

    public User(String email, String city) {
        this.email = email;
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public Optional<String> getCityOptional() {
        return Optional.ofNullable(city);
    }
}
