package room4u.repository;

import room4u.annotations.RepositoryComponent;
import room4u.domain.Reservation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository en mémoire pour les réservations.
 * Ne contient aucune logique métier.
 */
@RepositoryComponent("reservationRepository")
public class InMemoryReservationRepository implements Repository<String, Reservation> {

    private final Map<String, Reservation> reservations = new HashMap<>();

    @Override
    public void ajouter(Reservation reservation) {
        reservations.put(reservation.id(), reservation);
    }

    @Override
    public Optional<Reservation> trouverParId(String id) {
        return Optional.ofNullable(reservations.get(id));
    }

    @Override
    public List<Reservation> toutes() {
        return Collections.unmodifiableList(new ArrayList<>(reservations.values()));
    }

    public List<Reservation> trouverParSalle(String salleId) {
        return reservations.values().stream()
                .filter(r -> r.salle().id().equals(salleId))
                .collect(Collectors.toList());
    }

    public List<Reservation> trouverParUtilisateur(String utilisateur) {
        return reservations.values().stream()
                .filter(r -> r.utilisateur().equals(utilisateur))
                .collect(Collectors.toList());
    }

    public void supprimer(String id) {
        reservations.remove(id);
    }
}
