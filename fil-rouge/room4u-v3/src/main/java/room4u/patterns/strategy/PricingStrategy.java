package room4u.patterns.strategy;

import room4u.domain.Creneau;
import room4u.domain.Salle;

import java.math.BigDecimal;

/**
 * Stratégie de calcul du prix d'une réservation.
 */
public interface PricingStrategy {

    BigDecimal calculerPrix(Salle salle, Creneau creneau);
}
