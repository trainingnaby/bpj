package room4u;

import room4u.annotations.AnnotationExplorer;
import room4u.domain.Creneau;
import room4u.domain.EtatSalle;
import room4u.domain.Salle;
import room4u.patterns.factory.DefaultReservationFactory;
import room4u.patterns.factory.ReservationFactory;
import room4u.patterns.singleton.ReservationIdGenerator;
import room4u.patterns.strategy.PeakHoursPricingStrategy;
import room4u.patterns.strategy.PricingStrategy;
import room4u.repository.InMemoryReservationRepository;
import room4u.repository.InMemorySalleRepository;
import room4u.service.AsyncReservationService;
import room4u.service.AsyncReservationServiceImpl;
import room4u.service.DefaultSalleService;
import room4u.service.SalleService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Démonstration CompletableFuture, accès non bloquant, cohérence forte vs cohérence à terme.
 */
public class MainApp {
    public static void main(String[] args) throws Exception {
        System.out.println("Room4U - Jour 3 (Après-midi) - Async & Cohérence");

        // Repositories
        InMemorySalleRepository salleRepository = new InMemorySalleRepository();
        InMemoryReservationRepository reservationRepository = new InMemoryReservationRepository();

        // Service de salles (synchrone)
        SalleService salleService = new DefaultSalleService(salleRepository);

        // Patterns de création
        ReservationIdGenerator idGenerator = ReservationIdGenerator.INSTANCE;
        PricingStrategy pricingStrategy = new PeakHoursPricingStrategy();
        ReservationFactory reservationFactory = new DefaultReservationFactory(idGenerator, pricingStrategy);

        // Executor pour l'async
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Service asynchrone
        AsyncReservationService asyncReservationService =
                new AsyncReservationServiceImpl(salleRepository, reservationRepository, reservationFactory, executor);

        // Exploration des annotations
        AnnotationExplorer.printUseCases(SalleService.class, AsyncReservationService.class);
        AnnotationExplorer.printRepositories(InMemorySalleRepository.class, InMemoryReservationRepository.class);

        // Initialisation des données
        Salle salleA = new Salle("A", "Salle A", 4, Set.of("VISIO", "TABLEAU"), EtatSalle.DISPONIBLE);
        Salle salleB = new Salle("B", "Salle B", 10, Set.of("TABLEAU"), EtatSalle.DISPONIBLE);
        Salle salleC = new Salle("C", "Salle C", 8, Set.of("VISIO"), EtatSalle.MAINTENANCE);

        salleRepository.ajouter(salleA);
        salleRepository.ajouter(salleB);
        salleRepository.ajouter(salleC);

        System.out.println("Toutes les salles : " + salleService.listerToutesLesSalles());
        System.out.println("Salles disponibles : " + salleService.listerSallesDisponibles());

        LocalDateTime now = LocalDateTime.now();

        // Scénario 1 : cohérence forte + parallélisme
        System.out.println("=== Scénario 1 : cohérence forte (strong consistency) ===");
        Creneau c1 = new Creneau(now.plusHours(2), Duration.ofHours(1));
        Creneau c2 = new Creneau(now.plusHours(3), Duration.ofHours(1));

        CompletableFuture<?> fAlice = asyncReservationService.creerReservationStrong("A", "alice", c1)
                .thenAccept(r -> System.out.println("Réservation strong (alice) : " + r));
        CompletableFuture<?> fBob = asyncReservationService.creerReservationStrong("A", "bob", c2)
                .thenAccept(r -> System.out.println("Réservation strong (bob) : " + r));

        // On attend la fin des deux opérations asynchrones
        CompletableFuture.allOf(fAlice, fBob).join();

        List<room4u.domain.Reservation> strongReservations =
                asyncReservationService.listerReservationsPourSalle("A").get();
        System.out.println("Réservations pour la salle A (après strong) : " + strongReservations.size());

        // Scénario 2 : cohérence à terme
        System.out.println("\n=== Scénario 2 : cohérence à terme (eventual consistency) ===");
        Creneau c3 = new Creneau(now.plusHours(5), Duration.ofHours(1));

        CompletableFuture<room4u.domain.Reservation> fCharlie =
                asyncReservationService.creerReservationEventual("A", "charlie", c3);

        room4u.domain.Reservation resCharlie = fCharlie.get();
        System.out.println("Réservation eventual (charlie) retournée immédiatement : " + resCharlie);

        System.out.println("Juste après l'appel eventual, réservations pour A : "
                + reservationRepository.trouverParSalle("A").size());

        Thread.sleep(800L);

        System.out.println("Après un léger délai, réservations pour A : "
                + reservationRepository.trouverParSalle("A").size());

        executor.shutdown();
    }
}
