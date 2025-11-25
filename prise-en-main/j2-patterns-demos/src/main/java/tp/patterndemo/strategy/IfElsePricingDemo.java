package tp.patterndemo.strategy;

import java.math.BigDecimal;

/**
 * Anti-pattern : un gros if/else pour gérer différentes règles de prix.
 */
public class IfElsePricingDemo {

	enum CustomerType {
		STANDARD, PREMIUM, VIP
	}

	static BigDecimal computePrice(CustomerType type, BigDecimal basePrice) {
		if (type == CustomerType.STANDARD) {
			return basePrice;
		} else if (type == CustomerType.PREMIUM) {
			return basePrice.multiply(new BigDecimal("0.9"));
		} else if (type == CustomerType.VIP) {
			return basePrice.multiply(new BigDecimal("0.8"));
		} else {
			throw new IllegalArgumentException("Type inconnu");
		}
	}

	public static void main(String[] args) {
		BigDecimal base = new BigDecimal("100");

		System.out.println("STANDARD: " + computePrice(CustomerType.STANDARD, base));
		System.out.println("PREMIUM : " + computePrice(CustomerType.PREMIUM, base));
		System.out.println("VIP     : " + computePrice(CustomerType.VIP, base));

		System.out.println("""
				Problèmes :
				- Si on ajoute un type SUPER_VIP, on grossit encore ce if/else.
				- Tests unitaires plus difficiles à cibler par type.
				""");
	}
}
