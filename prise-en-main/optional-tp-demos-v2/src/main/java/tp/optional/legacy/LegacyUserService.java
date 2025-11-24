package tp.optional.legacy;

/**
 * Service "legacy" qui utilise null pour exprimer l'absence.
 */
public class LegacyUserService {

    private final LegacyUserRepository repository;

    public LegacyUserService(LegacyUserRepository repository) {
        this.repository = repository;
    }

    /**
     * Retourne la ville de l'utilisateur en majuscules.
     *
     * Cas possibles :
     * - utilisateur inexistant -> retourne null (ambigu)
     * - utilisateur avec ville null -> retourne "INCONNUE"
     * - utilisateur avec ville non null -> retourne la ville en majuscules
     */
    public String getUserCityUppercase(String email) {
        LegacyUser user = repository.findByEmail(email);
        if (user == null) {
            return null;
        }
        String city = user.getCity();
        if (city == null) {
            return "INCONNUE";
        }
        return city.toUpperCase();
    }

    /**
     * Envoie une "promotion" basée sur la ville de l'utilisateur.
     */
    public void sendCityBasedPromotion(String email) {
        LegacyUser user = repository.findByEmail(email);
        if (user == null) {
            System.out.println("[LEGACY] Aucun utilisateur pour l'email : " + email);
            return;
        }
        String city = user.getCity();
        if (city == null) {
            System.out.println("[LEGACY] Ville inconnue pour l'utilisateur " + email
                    + " -> on n'envoie pas de promo.");
            return;
        }
        if ("Paris".equalsIgnoreCase(city)) {
            System.out.println("[LEGACY] Envoi d'une promo spéciale PARIS à " + email);
        } else {
            System.out.println("[LEGACY] Envoi d'une promo standard à " + email
                    + " (ville = " + city + ")");
        }
    }
}
