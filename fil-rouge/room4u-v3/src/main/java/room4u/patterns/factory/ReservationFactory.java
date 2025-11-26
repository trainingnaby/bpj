package room4u.patterns.factory;

import room4u.domain.Creneau;
import room4u.domain.Reservation;
import room4u.domain.Salle;

/**
 * Factory de création des réservations.
 */
public interface ReservationFactory {

    Reservation creerReservation(Salle salle, String utilisateur, Creneau creneau);
}
