package tp.optional.modern.imperative;

import java.util.Optional;

/**
 * Service réécrit avec Optional, en style impératif (pas de map/flatMap).
 */
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<String> getUserCityUppercase(String email) {
        Optional<User> userOpt = repository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }

        User user = userOpt.get();
        Optional<String> cityOpt = user.getCityOptional();
        if (cityOpt.isEmpty()) {
            return Optional.empty();
        }

        String cityUpper = cityOpt.get().toUpperCase();
        return Optional.of(cityUpper);
    }

    public String getUserCityUppercaseOrDefault(String email, String defaultCity) {
        Optional<String> cityOpt = getUserCityUppercase(email);
        if (cityOpt.isPresent()) {
            return cityOpt.get();
        }
        return defaultCity.toUpperCase();
    }

    public void sendCityBasedPromotion(String email) {
        Optional<User> userOpt = repository.findByEmail(email);
        if (userOpt.isEmpty()) {
            System.out.println("[IMPERATIVE] Aucun utilisateur pour l'email : " + email);
            return;
        }

        User user = userOpt.get();
        Optional<String> cityOpt = user.getCityOptional();
        if (cityOpt.isEmpty()) {
            System.out.println("[IMPERATIVE] Pas de ville exploitable pour " + email
                    + " (utilisateur sans ville)");
            return;
        }

        String city = cityOpt.get();
        if ("Paris".equalsIgnoreCase(city)) {
            System.out.println("[IMPERATIVE] Envoi promo spéciale PARIS à " + email);
        } else {
            System.out.println("[IMPERATIVE] Envoi promo standard à " + email
                    + " (ville = " + city + ")");
        }
    }
}
