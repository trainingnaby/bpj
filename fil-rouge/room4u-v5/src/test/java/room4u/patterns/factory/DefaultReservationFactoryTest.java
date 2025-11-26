package room4u.patterns.factory;

import org.junit.jupiter.api.Test;
import room4u.domain.Creneau;
import room4u.domain.EtatReservation;
import room4u.domain.EtatSalle;
import room4u.domain.Reservation;
import room4u.domain.Salle;
import room4u.patterns.singleton.ReservationIdGenerator;
import room4u.patterns.strategy.PricingStrategy;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exemple de test unitaire avec un stub de PricingStrategy.
 */
class DefaultReservationFactoryTest {

    private static class FixedPricingStrategy implements PricingStrategy {
        @Override
        public BigDecimal calculerPrix(Salle salle, Creneau creneau) {
            return new BigDecimal("42.00");
        }
    }

    @Test
    void creerReservationUtiliseLeGeneratorEtLaStrategieDePrix() {
        ReservationIdGenerator idGenerator = ReservationIdGenerator.INSTANCE;
        PricingStrategy pricingStrategy = new FixedPricingStrategy();
        DefaultReservationFactory factory = new DefaultReservationFactory(idGenerator, pricingStrategy);

        Salle salle = new Salle("S1", "Salle", 5, Set.of(), EtatSalle.DISPONIBLE);
        Creneau creneau = new Creneau(LocalDateTime.now().plusHours(1), Duration.ofHours(1));

        Reservation reservation = factory.creerReservation(salle, "alice", creneau);

        assertNotNull(reservation.id());
        assertEquals("alice", reservation.utilisateur());
        assertEquals(salle, reservation.salle());
        assertEquals(EtatReservation.CONFIRMEE, reservation.etat());
        assertEquals(new BigDecimal("42.00"), reservation.prix());
    }
}
