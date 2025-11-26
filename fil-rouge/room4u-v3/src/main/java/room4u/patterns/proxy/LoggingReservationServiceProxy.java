package room4u.patterns.proxy;

import room4u.domain.Creneau;
import room4u.domain.Reservation;
import room4u.service.ReservationService;

import java.util.List;
import java.util.Objects;

/**
 * Proxy de ReservationService ajoutant du logging.
 */
public class LoggingReservationServiceProxy implements ReservationService {

    private final ReservationService delegate;

    public LoggingReservationServiceProxy(ReservationService delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }

    @Override
    public Reservation creerReservation(String salleId, String utilisateur, Creneau creneau) {
        System.out.println("[LOG] creerReservation - salleId=" + salleId + ", utilisateur=" + utilisateur + ", creneau=" + creneau);
        Reservation res = delegate.creerReservation(salleId, utilisateur, creneau);
        System.out.println("[LOG] creerReservation - résultat=" + res);
        return res;
    }

    @Override
    public List<Reservation> listerToutesLesReservations() {
        System.out.println("[LOG] listerToutesLesReservations");
        List<Reservation> list = delegate.listerToutesLesReservations();
        System.out.println("[LOG] -> " + list.size() + " réservation(s)");
        return list;
    }

    @Override
    public List<Reservation> listerReservationsPourSalle(String salleId) {
        System.out.println("[LOG] listerReservationsPourSalle - salleId=" + salleId);
        List<Reservation> list = delegate.listerReservationsPourSalle(salleId);
        System.out.println("[LOG] -> " + list.size() + " réservation(s) pour la salle " + salleId);
        return list;
    }

    @Override
    public void annulerReservation(String reservationId) {
        System.out.println("[LOG] annulerReservation - reservationId=" + reservationId);
        delegate.annulerReservation(reservationId);
        System.out.println("[LOG] annulerReservation - fait");
    }
}
