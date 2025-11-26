package room4u.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Représente un créneau de réservation.
 * Classe immuable.
 */
public final class Creneau {

    private final LocalDateTime debut;
    private final Duration duree;

    public Creneau(LocalDateTime debut, Duration duree) {
        if (debut == null) {
            throw new CreneauInvalideException("debut ne doit pas être null");
        }
        if (duree == null || duree.isNegative() || duree.isZero()) {
            throw new CreneauInvalideException("duree doit être positive");
        }
        this.debut = debut;
        this.duree = duree;
    }

    public LocalDateTime debut() {
        return debut;
    }

    public Duration duree() {
        return duree;
    }

    public LocalDateTime fin() {
        return debut.plus(duree);
    }

    public boolean overlap(Creneau other) {
        Objects.requireNonNull(other, "other ne doit pas être null");
        LocalDateTime thisFin = this.fin();
        LocalDateTime otherFin = other.fin();
        return this.debut.isBefore(otherFin) && other.debut.isBefore(thisFin);
    }

    public boolean estDansLePasse() {
        return fin().isBefore(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Creneau{" +
                "debut=" + debut +
                ", duree=" + duree +
                '}';
    }
}
