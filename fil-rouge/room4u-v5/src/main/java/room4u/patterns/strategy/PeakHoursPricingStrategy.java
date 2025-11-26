package room4u.patterns.strategy;

import room4u.domain.Creneau;
import room4u.domain.Salle;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * Stratégie de prix avec majoration en heures de pointe.
 */
public class PeakHoursPricingStrategy implements PricingStrategy {

    private final StandardPricingStrategy delegate = new StandardPricingStrategy();

    @Override
    public BigDecimal calculerPrix(Salle salle, Creneau creneau) {
        BigDecimal base = delegate.calculerPrix(salle, creneau);
        LocalTime start = creneau.debut().toLocalTime();
        // Heures de pointe : 9h-18h
        boolean peak = !start.isBefore(LocalTime.of(9, 0)) && !start.isAfter(LocalTime.of(18, 0));
        if (peak) {
            return base.multiply(BigDecimal.valueOf(2));
        }
        return base;
    }
}
