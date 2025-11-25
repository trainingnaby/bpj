package tp.patterndemo.strategy;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Version avec Strategy pattern pour le calcul de prix.
 */
public class StrategyPricingDemo {

	interface PricingStrategy {
		BigDecimal computePrice(BigDecimal basePrice);
	}

	static class StandardPricing implements PricingStrategy {
		@Override
		public BigDecimal computePrice(BigDecimal basePrice) {
			return basePrice;
		}
	}

	static class PremiumPricing implements PricingStrategy {
		@Override
		public BigDecimal computePrice(BigDecimal basePrice) {
			return basePrice.multiply(new BigDecimal("0.9"));
		}
	}

	static class VipPricing implements PricingStrategy {
		@Override
		public BigDecimal computePrice(BigDecimal basePrice) {
			return basePrice.multiply(new BigDecimal("0.8"));
		}
	}

	enum CustomerType {
		STANDARD, PREMIUM, VIP
	}

	static class PriceCalculator {
		private final Map<CustomerType, PricingStrategy> strategies;

		PriceCalculator(Map<CustomerType, PricingStrategy> strategies) {
			this.strategies = strategies;
		}

		BigDecimal computePrice(CustomerType type, BigDecimal basePrice) {
			PricingStrategy strategy = strategies.get(type);
			if (strategy == null) {
				throw new IllegalArgumentException("Aucune stratégie pour " + type);
			}
			return strategy.computePrice(basePrice);
		}
	}

	public static void main(String[] args) {
		Map<CustomerType, PricingStrategy> strategies = Map.of(CustomerType.STANDARD, new StandardPricing(),
				CustomerType.PREMIUM, new PremiumPricing(), CustomerType.VIP, new VipPricing());

		PriceCalculator calculator = new PriceCalculator(strategies);
		BigDecimal base = new BigDecimal("100");

		System.out.println("STANDARD: " + calculator.computePrice(CustomerType.STANDARD, base));
		System.out.println("PREMIUM : " + calculator.computePrice(CustomerType.PREMIUM, base));
		System.out.println("VIP     : " + calculator.computePrice(CustomerType.VIP, base));

		System.out.println("""
				Avantages :
				- Chaque règle de prix dans sa propre classe.
				- Pour ajouter SUPER_VIP, on crée une nouvelle stratégie et une nouvelle entrée dans la map.
				- Tests ciblés par stratégie possible.
				""");
	}
}
