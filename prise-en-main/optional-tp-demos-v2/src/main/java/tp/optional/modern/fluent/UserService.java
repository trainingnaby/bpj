package tp.optional.modern.fluent;

import java.util.Optional;

/**
 * Service réécrit avec Optional en style fluent (map / flatMap / ifPresentOrElse).
 */
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<String> getUserCityUppercase(String email) {
        return repository.findByEmail(email)
                .flatMap(User::getCityOptional)
                .map(String::toUpperCase);
    }

    public String getUserCityUppercaseOrDefault(String email, String defaultCity) {
        return getUserCityUppercase(email)
                .orElse(defaultCity.toUpperCase());
    }

    public void sendCityBasedPromotion(String email) {
        repository.findByEmail(email)
                .flatMap(User::getCityOptional)
                .ifPresentOrElse(
                        city -> {
                            if ("Paris".equalsIgnoreCase(city)) {
                                System.out.println("[FLUENT] Envoi promo spéciale PARIS à " + email);
                            } else {
                                System.out.println("[FLUENT] Envoi promo standard à " + email
                                        + " (ville = " + city + ")");
                            }
                        },
                        () -> System.out.println("[FLUENT] Pas de ville exploitable pour " + email
                                + " (utilisateur absent ou ville null)")
                );
    }
}
