package room4u.service;

import room4u.domain.Creneau;
import room4u.domain.CreneauInvalideException;
import room4u.domain.Reservation;
import room4u.domain.Salle;
import room4u.domain.SalleIndisponibleException;
import room4u.domain.SalleUtils;
import room4u.patterns.factory.ReservationFactory;
import room4u.repository.InMemoryReservationRepository;
import room4u.repository.InMemorySalleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Implémentation asynchrone de la gestion des réservations Room4U.
 * Démontre l'utilisation de CompletableFuture, ainsi que la cohérence forte
 * vs cohérence à terme (eventual consistency).
 */
public class AsyncReservationServiceImpl implements AsyncReservationService {

    private final InMemorySalleRepository salleRepository;
    private final InMemoryReservationRepository reservationRepository;
    private final ReservationFactory reservationFactory;
    private final Executor executor;

    public AsyncReservationServiceImpl(InMemorySalleRepository salleRepository,
                                       InMemoryReservationRepository reservationRepository,
                                       ReservationFactory reservationFactory,
                                       Executor executor) {
        this.salleRepository = Objects.requireNonNull(salleRepository);
        this.reservationRepository = Objects.requireNonNull(reservationRepository);
        this.reservationFactory = Objects.requireNonNull(reservationFactory);
        this.executor = Objects.requireNonNull(executor);
    }

    @Override
    public CompletableFuture<Reservation> creerReservationStrong(String salleId, String utilisateur, Creneau creneau) {
        // Cohérence forte :
        // Le CompletableFuture n'est complété qu'après l'écriture dans le repository.
        return CompletableFuture.supplyAsync(() -> {
            Salle salle = chargerSalleOuException(salleId);
            verifierCreneau(creneau);
            verifierDisponibilite(salle, creneau);

            Reservation reservation = reservationFactory.creerReservation(salle, utilisateur, creneau);

            // Simulation d'une latence (accès base de données / service distant)
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            reservationRepository.ajouter(reservation);
            return reservation;
        }, executor);
    }

    @Override
    public CompletableFuture<Reservation> creerReservationEventual(String salleId, String utilisateur, Creneau creneau) {
        // Cohérence à terme :
        // On valide, on construit et on retourne immédiatement la réservation,
        // mais l'écriture dans le repository est réalisée en arrière-plan.
        Salle salle = chargerSalleOuException(salleId);
        verifierCreneau(creneau);
        verifierDisponibilite(salle, creneau);

        Reservation reservation = reservationFactory.creerReservation(salle, utilisateur, creneau);

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            reservationRepository.ajouter(reservation);
        }, executor);

        return CompletableFuture.completedFuture(reservation);
    }

    @Override
    public CompletableFuture<List<Reservation>> listerToutesLesReservations() {
        return CompletableFuture.supplyAsync(reservationRepository::toutes, executor);
    }

    @Override
    public CompletableFuture<List<Reservation>> listerReservationsPourSalle(String salleId) {
        return CompletableFuture.supplyAsync(() -> reservationRepository.trouverParSalle(salleId), executor);
    }

    private Salle chargerSalleOuException(String salleId) {
        return salleRepository.trouverParId(salleId)
                .orElseThrow(() -> new IllegalArgumentException("Aucune salle avec l'id " + salleId));
    }

    private void verifierCreneau(Creneau creneau) {
        if (creneau.fin().isBefore(LocalDateTime.now())) {
            throw new CreneauInvalideException("Le créneau est dans le passé");
        }
    }

    private void verifierDisponibilite(Salle salle, Creneau creneau) {
        List<Reservation> reservationsExistantes = reservationRepository.trouverParSalle(salle.id());
        if (!SalleUtils.estDisponible(salle, creneau, reservationsExistantes)) {
            throw new SalleIndisponibleException(
                    "La salle " + salle.id() + " n'est pas disponible sur ce créneau");
        }
    }
}
