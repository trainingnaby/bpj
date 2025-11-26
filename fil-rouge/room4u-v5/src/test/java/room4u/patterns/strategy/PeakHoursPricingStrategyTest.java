package room4u.patterns.strategy;

import org.junit.jupiter.api.Test;
import room4u.domain.Creneau;
import room4u.domain.Salle;
import room4u.domain.EtatSalle;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PeakHoursPricingStrategyTest {

    private final PeakHoursPricingStrategy strategy = new PeakHoursPricingStrategy();

    @Test
    void appliqueMajoratonEnHeuresDePointe() {
        Salle salle = new Salle("S1", "Salle", 5, Set.of(), EtatSalle.DISPONIBLE);
        LocalDateTime debut = LocalDateTime.now().withHour(10).withMinute(0);
        if (debut.isBefore(LocalDateTime.now())) {
            debut = debut.plusDays(1);
        }
        Creneau c = new Creneau(debut, Duration.ofHours(1));

        BigDecimal prix = strategy.calculerPrix(salle, c);

        assertEquals(new BigDecimal("20.00"), prix);
    }

    @Test
    void nAppliquePasDeMajorationHorsHeuresDePointe() {
        Salle salle = new Salle("S1", "Salle", 5, Set.of(), EtatSalle.DISPONIBLE);
        LocalDateTime debut = LocalDateTime.now().withHour(20).withMinute(0);
        if (debut.isBefore(LocalDateTime.now())) {
            debut = debut.plusDays(1);
        }
        Creneau c = new Creneau(debut, Duration.ofHours(1));

        BigDecimal prix = strategy.calculerPrix(salle, c);

        assertEquals(new BigDecimal("10.00"), prix);
    }
}
