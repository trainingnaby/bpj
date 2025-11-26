package room4u.patterns.factory;

import room4u.domain.Creneau;
import room4u.domain.EtatReservation;
import room4u.domain.Reservation;
import room4u.domain.Salle;
import room4u.patterns.singleton.ReservationIdGenerator;
import room4u.patterns.strategy.PricingStrategy;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Implémentation par défaut de la factory de réservations.
 */
public class DefaultReservationFactory implements ReservationFactory {

    private final ReservationIdGenerator idGenerator;
    private final PricingStrategy pricingStrategy;

    public DefaultReservationFactory(ReservationIdGenerator idGenerator, PricingStrategy pricingStrategy) {
        this.idGenerator = Objects.requireNonNull(idGenerator);
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy);
    }

    @Override
    public Reservation creerReservation(Salle salle, String utilisateur, Creneau creneau) {
        String id = idGenerator.nextId();
        BigDecimal prix = pricingStrategy.calculerPrix(salle, creneau);
        return new Reservation(id, utilisateur, salle, creneau, EtatReservation.CONFIRMEE, prix);
    }
}
