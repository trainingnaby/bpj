package room4u.service;

import room4u.annotations.UseCase;
import room4u.domain.Creneau;
import room4u.domain.Reservation;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Service asynchrone de gestion des réservations.
 * Retourne systématiquement des CompletableFuture pour permettre des accès non bloquants.
 */
public interface AsyncReservationService {

    @UseCase("Créer une réservation avec cohérence forte (async)")
    CompletableFuture<Reservation> creerReservationStrong(String salleId, String utilisateur, Creneau creneau);

    @UseCase("Créer une réservation avec cohérence à terme (async)")
    CompletableFuture<Reservation> creerReservationEventual(String salleId, String utilisateur, Creneau creneau);

    @UseCase("Lister toutes les réservations (async)")
    CompletableFuture<List<Reservation>> listerToutesLesReservations();

    @UseCase("Lister les réservations pour une salle (async)")
    CompletableFuture<List<Reservation>> listerReservationsPourSalle(String salleId);
}
