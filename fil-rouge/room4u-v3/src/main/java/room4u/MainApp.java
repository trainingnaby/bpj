package room4u;

import room4u.annotations.AnnotationExplorer;
import room4u.domain.Creneau;
import room4u.domain.EtatSalle;
import room4u.domain.Salle;
import room4u.patterns.factory.DefaultReservationFactory;
import room4u.patterns.factory.ReservationFactory;
import room4u.patterns.prototype.ReservationTemplate;
import room4u.patterns.proxy.LoggingReservationServiceProxy;
import room4u.patterns.singleton.ReservationIdGenerator;
import room4u.patterns.strategy.PeakHoursPricingStrategy;
import room4u.patterns.strategy.PricingStrategy;
import room4u.repository.InMemoryReservationRepository;
import room4u.repository.InMemorySalleRepository;
import room4u.service.DefaultReservationService;
import room4u.service.DefaultSalleService;
import room4u.service.ReservationService;
import room4u.service.SalleService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

/**
 * Point d'entrée de l'application.
 * Sert de démonstration des patterns (Factory, Singleton, Prototype, Strategy, Proxy) dans Room4U.
 */
public class MainApp {
    public static void main(String[] args) {
        System.out.println("Room4U - Jour 3 (Matin) - Patterns de conception");

        // Couches basses : repositories
        InMemorySalleRepository salleRepository = new InMemorySalleRepository();
        InMemoryReservationRepository reservationRepository = new InMemoryReservationRepository();

        // Couches métier : service de salles
        SalleService salleService = new DefaultSalleService(salleRepository);

        // Patterns : Singleton + Strategy + Factory
        ReservationIdGenerator idGenerator = ReservationIdGenerator.INSTANCE;
        PricingStrategy pricingStrategy = new PeakHoursPricingStrategy(); // on pourrait basculer sur StandardPricingStrategy
        ReservationFactory reservationFactory = new DefaultReservationFactory(idGenerator, pricingStrategy);

        // Service core + Proxy
        ReservationService coreReservationService =
                new DefaultReservationService(salleRepository, reservationRepository, reservationFactory);
        ReservationService reservationService =
                new LoggingReservationServiceProxy(coreReservationService);

        // Exploration des annotations (UseCase / RepositoryComponent)
        AnnotationExplorer.printUseCases(SalleService.class, ReservationService.class);
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
        System.out.println("Salles avec capacite >= 8 et VISIO : " +
                salleService.rechercherParCapaciteEtEquipement(8, "VISIO"));

        // Création d'un créneau valide
        LocalDateTime now = LocalDateTime.now();
        Creneau creneauFutur = new Creneau(now.plusHours(2), Duration.ofHours(2));

        // Création d'une réservation valide
        System.out.println("Création d'une réservation valide sur la salle A...");
        var reservation = reservationService.creerReservation("A", "alice", creneauFutur);
        System.out.println("Réservation créée (avec prix) : " + reservation);

        // Tentative de réservation sur une salle en maintenance
        try {
            System.out.println("Tentative de réservation sur salle C (MAINTENANCE)...");
            reservationService.creerReservation("C", "bob", creneauFutur);
        } catch (Exception e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }

        // Tentative de réservation sur un créneau passé
        try {
            System.out.println("Tentative de réservation sur un créneau passé...");
            Creneau creneauPasse = new Creneau(now.minusHours(3), Duration.ofHours(1));
            reservationService.creerReservation("A", "charlie", creneauPasse);
        } catch (Exception e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }

        // Tentative de réservation sur un créneau qui chevauche la première
        try {
            System.out.println("Tentative de réservation qui chevauche la première...");
            Creneau creneauChevauche = new Creneau(now.plusHours(3), Duration.ofHours(1));
            reservationService.creerReservation("A", "dave", creneauChevauche);
        } catch (Exception e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }

        // Prototype : créer des réservations récurrentes à partir d'un template
        System.out.println("Création de réservations récurrentes via ReservationTemplate...");
        ReservationTemplate template =
                new ReservationTemplate(salleA, "alice", Duration.ofHours(1));

        LocalDate baseDate = LocalDate.now().plusDays(1);
        for (int i = 0; i < 3; i++) {
            LocalDateTime debut = LocalDateTime.of(baseDate.plusDays(i), LocalTime.of(10, 0));
            var resRecurrente = template.creerPourDate(debut, reservationFactory);
            // On passe par le repository directement pour ne pas refaire toute la validation ici
            reservationRepository.ajouter(resRecurrente);
            System.out.println("Réservation récurrente créée : " + resRecurrente);
        }

        // Afficher les réservations pour la salle A
        List<room4u.domain.Reservation> reservationsSalleA =
                reservationService.listerReservationsPourSalle("A");
        System.out.println("Réservations pour la salle A : " + reservationsSalleA);

        // Annuler la première réservation et vérifier
        System.out.println("Annulation de la réservation " + reservation.id());
        reservationService.annulerReservation(reservation.id());
        System.out.println("Réservations pour la salle A après annulation : " +
                reservationService.listerReservationsPourSalle("A"));
    }
}
