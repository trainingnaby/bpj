package room4u.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room4u.domain.*;
import room4u.patterns.factory.DefaultReservationFactory;
import room4u.patterns.factory.ReservationFactory;
import room4u.patterns.singleton.ReservationIdGenerator;
import room4u.patterns.strategy.StandardPricingStrategy;
import room4u.repository.InMemoryReservationRepository;
import room4u.repository.InMemorySalleRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exemple de test d'intégration sur DefaultReservationService :
 * on branche les repositories réels et la factory.
 */
class DefaultReservationServiceIntegrationTest {

    private InMemorySalleRepository salleRepository;
    private InMemoryReservationRepository reservationRepository;
    private DefaultReservationService reservationService;

    private Salle salleDisponible;
    private Salle salleMaintenance;

    @BeforeEach
    void setUp() {
        salleRepository = new InMemorySalleRepository();
        reservationRepository = new InMemoryReservationRepository();

        ReservationIdGenerator idGenerator = ReservationIdGenerator.INSTANCE;
        ReservationFactory reservationFactory = new DefaultReservationFactory(idGenerator, new StandardPricingStrategy());

        reservationService = new DefaultReservationService(salleRepository, reservationRepository, reservationFactory);

        salleDisponible = new Salle("A", "Salle A", 4, Set.of("TABLEAU"), EtatSalle.DISPONIBLE);
        salleMaintenance = new Salle("B", "Salle B", 8, Set.of("VISIO"), EtatSalle.MAINTENANCE);

        salleRepository.ajouter(salleDisponible);
        salleRepository.ajouter(salleMaintenance);
    }

    @Test
    void creerReservationValidePersisteLaReservation() {
        Creneau creneau = new Creneau(LocalDateTime.now().plusHours(2), Duration.ofHours(1));

        Reservation reservation = reservationService.creerReservation("A", "alice", creneau);

        assertNotNull(reservation.id());
        assertEquals("alice", reservation.utilisateur());
        assertEquals(1, reservationRepository.toutes().size());
    }

    @Test
    void creerReservationSurSalleEnMaintenanceDeclencheException() {
        Creneau creneau = new Creneau(LocalDateTime.now().plusHours(2), Duration.ofHours(1));

        assertThrows(SalleIndisponibleException.class,
                () -> reservationService.creerReservation("B", "alice", creneau));
    }

    @Test
    void creerReservationSurCreneauPasseDeclencheException() {
        Creneau creneau = new Creneau(LocalDateTime.now().minusHours(2), Duration.ofHours(1));

        assertThrows(CreneauInvalideException.class,
                () -> reservationService.creerReservation("A", "alice", creneau));
    }

    @Test
    void creerReservationChevauchanteDeclencheException() {
        LocalDateTime base = LocalDateTime.now().plusHours(2);
        Creneau premier = new Creneau(base, Duration.ofHours(2));
        Creneau chevauchant = new Creneau(base.plusHours(1), Duration.ofHours(1));

        reservationService.creerReservation("A", "alice", premier);

        assertThrows(SalleIndisponibleException.class,
                () -> reservationService.creerReservation("A", "bob", chevauchant));
    }
}
