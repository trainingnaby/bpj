package room4u.patterns.strategy;

import room4u.domain.Creneau;
import room4u.domain.Salle;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Stratégie de prix standard : 10 unités par heure entamée.
 */
public class StandardPricingStrategy implements PricingStrategy {

    private static final BigDecimal PRICE_PER_HOUR = BigDecimal.TEN;

    @Override
    public BigDecimal calculerPrix(Salle salle, Creneau creneau) {
        long minutes = creneau.duree().toMinutes();
        long hours = (minutes + 59) / 60; // arrondi à l'heure supérieure
        if (hours == 0) {
            hours = 1;
        }
        return PRICE_PER_HOUR.multiply(BigDecimal.valueOf(hours)).setScale(2, RoundingMode.HALF_UP);
    }
}
