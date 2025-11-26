package room4u.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SalleUtilsTest {

    private Salle petiteSalle;
    private Salle grandeSalle;
    private Salle salleMaintenance;

    private List<Salle> salles;

    @BeforeEach
    void setUp() {
        petiteSalle = new Salle("S1", "Petite salle", 4, Set.of("TABLEAU"), EtatSalle.DISPONIBLE);
        grandeSalle = new Salle("S2", "Grande salle", 10, Set.of("VISIO", "TABLEAU"), EtatSalle.DISPONIBLE);
        salleMaintenance = new Salle("S3", "Salle HS", 8, Set.of("VISIO"), EtatSalle.MAINTENANCE);

        salles = List.of(petiteSalle, grandeSalle, salleMaintenance);
    }

    @Test
    void filtrerParCapaciteRetourneLesSallesAvecCapaciteMinimum() {
        List<Salle> result = SalleUtils.filtrerParCapacite(salles, 8);

        assertEquals(2, result.size());
        assertTrue(result.contains(grandeSalle));
        assertTrue(result.contains(salleMaintenance));
    }

    @Test
    void filtrerParEquipementRetourneLesSallesAyantLEquipement() {
        List<Salle> result = SalleUtils.filtrerParEquipement(salles, "VISIO");

        assertEquals(2, result.size());
        assertTrue(result.contains(grandeSalle));
        assertTrue(result.contains(salleMaintenance));
    }

    @Test
    void estDisponibleRetourneFalseSiUneReservationChevauche() {
        Creneau creneau = new Creneau(LocalDateTime.now().plusHours(1), Duration.ofHours(1));
        Reservation existante = new Reservation("R1", "alice", petiteSalle, creneau, EtatReservation.CONFIRMEE, 
                java.math.BigDecimal.TEN);

        boolean disponible = SalleUtils.estDisponible(petiteSalle, creneau, List.of(existante));

        assertFalse(disponible);
    }

    @Test
    void estDisponibleRetourneTrueSiAucuneReservationChevauche() {
        Creneau creneau = new Creneau(LocalDateTime.now().plusHours(1), Duration.ofHours(1));
        Creneau autreCreneau = new Creneau(LocalDateTime.now().plusHours(3), Duration.ofHours(1));
        Reservation existante = new Reservation("R1", "alice", petiteSalle, autreCreneau, EtatReservation.CONFIRMEE,
                java.math.BigDecimal.TEN);

        boolean disponible = SalleUtils.estDisponible(petiteSalle, creneau, List.of(existante));

        assertTrue(disponible);
    }

    @Test
    void regrouperParEtatRegroupeCorrectementLesSalles() {
        Map<EtatSalle, List<Salle>> map = SalleUtils.regrouperParEtat(salles);

        assertEquals(2, map.get(EtatSalle.DISPONIBLE).size());
        assertEquals(1, map.get(EtatSalle.MAINTENANCE).size());
    }
}
