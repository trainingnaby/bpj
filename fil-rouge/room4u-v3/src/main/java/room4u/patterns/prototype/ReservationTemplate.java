package room4u.patterns.prototype;

import room4u.domain.Creneau;
import room4u.domain.Reservation;
import room4u.domain.Salle;
import room4u.patterns.factory.ReservationFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Prototype de réservation récurrente : même salle, même utilisateur, même durée.
 */
public final class ReservationTemplate {

    private final Salle salle;
    private final String utilisateur;
    private final Duration duree;

    public ReservationTemplate(Salle salle, String utilisateur, Duration duree) {
        this.salle = Objects.requireNonNull(salle);
        this.utilisateur = Objects.requireNonNull(utilisateur);
        this.duree = Objects.requireNonNull(duree);
    }

    public Salle salle() {
        return salle;
    }

    public String utilisateur() {
        return utilisateur;
    }

    public Duration duree() {
        return duree;
    }

    public Reservation creerPourDate(LocalDateTime debut, ReservationFactory factory) {
        Creneau c = new Creneau(debut, duree);
        return factory.creerReservation(salle, utilisateur, c);
    }
}
