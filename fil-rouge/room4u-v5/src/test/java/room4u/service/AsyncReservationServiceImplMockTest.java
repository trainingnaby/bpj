package room4u.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import room4u.domain.*;
import room4u.patterns.factory.ReservationFactory;
import room4u.repository.InMemoryReservationRepository;
import room4u.repository.InMemorySalleRepository;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Exemple d'utilisation de Mockito pour isoler AsyncReservationServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
class AsyncReservationServiceImplMockTest {

    @Mock
    InMemorySalleRepository salleRepository;

    @Mock
    InMemoryReservationRepository reservationRepository;

    @Mock
    ReservationFactory reservationFactory;

    @Test
    void creerReservationStrongAppelleFactoryEtRepository() throws Exception {
        AsyncReservationServiceImpl service = new AsyncReservationServiceImpl(
                salleRepository,
                reservationRepository,
                reservationFactory,
                Executors.newSingleThreadExecutor()
        );

        Salle salle = new Salle("A", "Salle A", 4, Set.of(), EtatSalle.DISPONIBLE);
        Creneau creneau = new Creneau(LocalDateTime.now().plusHours(1), Duration.ofHours(1));
        Reservation reservation = new Reservation("R1", "alice", salle, creneau, EtatReservation.CONFIRMEE, BigDecimal.TEN);

        when(salleRepository.trouverParId("A")).thenReturn(Optional.of(salle));
        when(reservationRepository.trouverParSalle("A")).thenReturn(List.of());
        when(reservationFactory.creerReservation(salle, "alice", creneau)).thenReturn(reservation);

        CompletableFuture<Reservation> future = service.creerReservationStrong("A", "alice", creneau);
        Reservation result = future.get(); // attend la fin du traitement asynchrone

        assertSame(reservation, result);

        verify(reservationFactory, times(1)).creerReservation(salle, "alice", creneau);

        ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
        verify(reservationRepository, times(1)).ajouter(captor.capture());
        assertEquals("R1", captor.getValue().id());
    }
}
