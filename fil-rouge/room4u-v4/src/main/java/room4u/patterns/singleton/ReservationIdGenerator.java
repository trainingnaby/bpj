package room4u.patterns.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Singleton responsable de la génération des identifiants de réservation.
 */
public enum ReservationIdGenerator {
    INSTANCE;

    private final AtomicInteger sequence = new AtomicInteger(1);

    public String nextId() {
        return "R-" + sequence.getAndIncrement();
    }
}
