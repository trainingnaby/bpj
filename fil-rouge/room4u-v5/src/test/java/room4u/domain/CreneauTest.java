package room4u.domain;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CreneauTest {

    @Test
    void constructeurValideCreeUnCreneau() {
        LocalDateTime debut = LocalDateTime.now().plusHours(1);
        Duration duree = Duration.ofHours(2);

        Creneau c = new Creneau(debut, duree);

        assertEquals(debut, c.debut());
        assertEquals(duree, c.duree());
        assertEquals(debut.plus(duree), c.fin());
    }

    @Test
    void constructeurAvecDureeNegativeDeclencheException() {
        LocalDateTime debut = LocalDateTime.now().plusHours(1);
        Duration duree = Duration.ofHours(-1);

        assertThrows(CreneauInvalideException.class, () -> new Creneau(debut, duree));
    }

    @Test
    void overlapRetourneTrueQuandLesCreneauxSeChevauchent() {
        LocalDateTime now = LocalDateTime.now();
        Creneau c1 = new Creneau(now.plusHours(1), Duration.ofHours(2));
        Creneau c2 = new Creneau(now.plusHours(2), Duration.ofHours(2)); // chevauche sur 1h

        assertTrue(c1.overlap(c2));
        assertTrue(c2.overlap(c1));
    }

    @Test
    void overlapRetourneFalseQuandLesCreneauxNeSeChevauchentPas() {
        LocalDateTime now = LocalDateTime.now();
        Creneau c1 = new Creneau(now.plusHours(1), Duration.ofHours(1));
        Creneau c2 = new Creneau(now.plusHours(3), Duration.ofHours(1));

        assertFalse(c1.overlap(c2));
        assertFalse(c2.overlap(c1));
    }

    @Test
    void estDansLePasseRetourneTruePourUnCreneauTermine() {
        LocalDateTime debut = LocalDateTime.now().minusHours(2);
        Creneau c = new Creneau(debut, Duration.ofHours(1));

        assertTrue(c.estDansLePasse());
    }

    @Test
    void estDansLePasseRetourneFalsePourUnCreneauFutur() {
        LocalDateTime debut = LocalDateTime.now().plusHours(1);
        Creneau c = new Creneau(debut, Duration.ofHours(1));

        assertFalse(c.estDansLePasse());
    }
}
