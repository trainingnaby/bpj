package tp.optional.showcase;

import java.util.Optional;

/**
 * Showcase des principales méthodes de Optional, sur des exemples simples.
 */
public class OptionalShowcase {

    public static void main(String[] args) {
        System.out.println("=== of / ofNullable / empty ===");
        Optional<String> opt1 = Optional.of("Room4U");
        Optional<String> opt2 = Optional.ofNullable(null);
        Optional<String> opt3 = Optional.empty();

        System.out.println("opt1 = " + opt1);
        System.out.println("opt2 = " + opt2);
        System.out.println("opt3 = " + opt3);

        System.out.println("\n=== map ===");
        Optional<Integer> length = opt1.map(String::length);
        System.out.println("length of 'Room4U' = " + length.orElse(-1));

        System.out.println("\n=== filter ===");
        Optional<String> filtered = opt1.filter(s -> s.startsWith("Room"));
        System.out.println("filter startsWith 'Room' -> " + filtered);

        System.out.println("\n=== map vs flatMap ===");
        Optional<Optional<String>> nested = opt1.map(Optional::of);
        System.out.println("map -> Optional<Optional<String>> : " + nested);

        Optional<String> flattened = opt1.flatMap(Optional::of);
        System.out.println("flatMap -> Optional<String> : " + flattened);

        System.out.println("\n=== orElse / orElseGet / orElseThrow ===");
        String v1 = opt1.orElse("default");
        String v2 = opt2.orElse("default");
        String v3 = opt2.orElseGet(() -> "generatedDefault");
        System.out.println("opt1.orElse('default') = " + v1);
        System.out.println("opt2.orElse('default') = " + v2);
        System.out.println("opt2.orElseGet(...)   = " + v3);

        try {
            opt2.orElseThrow(() -> new IllegalStateException("Valeur manquante"));
        } catch (Exception e) {
            System.out.println("opt2.orElseThrow(...) -> exception : " + e);
        }

        System.out.println("\n=== ifPresent / ifPresentOrElse ===");
        opt1.ifPresent(v -> System.out.println("ifPresent sur opt1 : " + v));
        opt3.ifPresentOrElse(
                v -> System.out.println("ifPresentOrElse sur opt3 (present) : " + v),
                () -> System.out.println("ifPresentOrElse sur opt3 (absent)")
        );
    }
}
