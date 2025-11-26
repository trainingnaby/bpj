package room4u.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Méthodes utilitaires pour manipuler les salles et les réservations.
 */
public final class SalleUtils {

    private SalleUtils() { }

    public static List<Salle> filtrerParCapacite(List<Salle> salles, int capaciteMin) {
        return salles.stream()
                .filter(s -> s.capacite() >= capaciteMin)
                .collect(Collectors.toList());
    }

    public static List<Salle> filtrerParEquipement(List<Salle> salles, String equipement) {
        Objects.requireNonNull(equipement, "equipement ne doit pas être null");
        return salles.stream()
                .filter(s -> s.equipements().contains(equipement))
                .collect(Collectors.toList());
    }

    public static boolean estDisponible(Salle salle, Creneau creneau, List<Reservation> reservationsExistantes) {
        return reservationsExistantes.stream()
                .filter(r -> r.salle().equals(salle))
                .noneMatch(r -> r.creneau().overlap(creneau));
    }

    public static Map<EtatSalle, List<Salle>> regrouperParEtat(List<Salle> salles) {
        return salles.stream()
                .collect(Collectors.groupingBy(Salle::etat));
    }

    public static List<Salle> filtrer(List<Salle> salles, Predicate<Salle> predicate) {
        return salles.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
