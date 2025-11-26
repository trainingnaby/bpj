package room4u.service;

import room4u.annotations.UseCase;
import room4u.domain.Creneau;
import room4u.domain.Reservation;

import java.util.List;

/**
 * Service métier pour la gestion des réservations.
 */
public interface ReservationService {

    @UseCase("Créer une réservation")
    Reservation creerReservation(String salleId, String utilisateur, Creneau creneau);

    @UseCase("Lister toutes les réservations")
    List<Reservation> listerToutesLesReservations();

    @UseCase("Lister les réservations pour une salle")
    List<Reservation> listerReservationsPourSalle(String salleId);

    @UseCase("Annuler une réservation")
    void annulerReservation(String reservationId);
}
