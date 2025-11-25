package room4u.service;

import room4u.domain.Creneau;
import room4u.domain.CreneauInvalideException;
import room4u.domain.EtatReservation;
import room4u.domain.Reservation;
import room4u.domain.Salle;
import room4u.domain.SalleIndisponibleException;
import room4u.domain.SalleUtils;
import room4u.repository.InMemoryReservationRepository;
import room4u.repository.InMemorySalleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implémentation par défaut de ReservationService.
 */
public class DefaultReservationService implements ReservationService {

    private final InMemorySalleRepository salleRepository;
    private final InMemoryReservationRepository reservationRepository;
    private final AtomicInteger sequence = new AtomicInteger(1);

    public DefaultReservationService(InMemorySalleRepository salleRepository,
                                     InMemoryReservationRepository reservationRepository) {
        this.salleRepository = Objects.requireNonNull(salleRepository);
        this.reservationRepository = Objects.requireNonNull(reservationRepository);
    }

    @Override
    public Reservation creerReservation(String salleId, String utilisateur, Creneau creneau) {
        Salle salle = chargerSalleOuException(salleId);
        verifierCreneau(creneau);
        verifierDisponibilite(salle, creneau);

        String id = "R-" + sequence.getAndIncrement();
        Reservation reservation = new Reservation(id, utilisateur, salle, creneau, EtatReservation.CONFIRMEE);
        reservationRepository.ajouter(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> listerToutesLesReservations() {
        return reservationRepository.toutes();
    }

    @Override
    public List<Reservation> listerReservationsPourSalle(String salleId) {
        return reservationRepository.trouverParSalle(salleId);
    }

    @Override
    public void annulerReservation(String reservationId) {
        Reservation reservation = reservationRepository.trouverParId(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Aucune réservation avec l'id " + reservationId));
        reservationRepository.supprimer(reservation.id());
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
            throw new SalleIndisponibleException("La salle " + salle.id() + " n'est pas disponible sur ce créneau");
        }
    }
}
