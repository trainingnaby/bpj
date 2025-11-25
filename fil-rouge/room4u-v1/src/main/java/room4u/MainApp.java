package room4u;

import room4u.domain.*;
import room4u.repository.InMemorySalleRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Room4U - Jour 1 (Matin) - Démonstration de la correction enrichie");

        InMemorySalleRepository repo = new InMemorySalleRepository();

        Salle salleA = new Salle("A", "Salle A", 4, Set.of("VISIO", "TABLEAU"), EtatSalle.DISPONIBLE);
        Salle salleB = new Salle("B", "Salle B", 10, Set.of("TABLEAU"), EtatSalle.DISPONIBLE);
        Salle salleC = new Salle("C", "Salle C", 8, Set.of("VISIO"), EtatSalle.MAINTENANCE);

        repo.ajouter(salleA);
        repo.ajouter(salleB);
        repo.ajouter(salleC);

        List<Salle> toutes = repo.toutes();
        System.out.println("Toutes les salles : " + toutes);

        List<Salle> grandesSalles = SalleUtils.filtrerParCapacite(toutes, 8);
        System.out.println("Salles avec capacité >= 8 : " + grandesSalles);

        List<Salle> visio = SalleUtils.filtrerParEquipement(toutes, "VISIO");
        System.out.println("Salles avec VISIO : " + visio);

        List<Salle> filtrageLambda = SalleUtils.filtrer(toutes,
                s -> s.capacite() > 5 && s.equipements().contains("VISIO"));
        System.out.println("Salles (lambda) capacite > 5 et VISIO : " + filtrageLambda);

        LocalDateTime now = LocalDateTime.now();
        Creneau c1 = new Creneau(now.minusHours(2), Duration.ofHours(1));      // passé
        Creneau c2 = new Creneau(now, Duration.ofHours(2));                    // en cours
        Creneau c3 = new Creneau(now.plusHours(3), Duration.ofHours(1));       // futur

        System.out.println("c1 overlap c2 ? " + c1.overlap(c2));
        System.out.println("c2 overlap c3 ? " + c2.overlap(c3));
        System.out.println("c1 estDansLePasse ? " + c1.estDansLePasse());
        System.out.println("Durée de c2 en minutes : " + c2.dureeEnMinutes());

        Reservation r1 = new Reservation("R1", "user1", salleA, c2, EtatReservation.CONFIRMEE);

        boolean dispoChevauche = SalleUtils.estDisponible(salleA,
                new Creneau(now.plusHours(1), Duration.ofHours(1)), List.of(r1));
        boolean dispoLoin = SalleUtils.estDisponible(salleA, c3, List.of(r1));

        System.out.println("Salle A disponible sur créneau qui chevauche ? " + dispoChevauche);
        System.out.println("Salle A disponible sur créneau lointain ? " + dispoLoin);

        Map<EtatSalle, List<Salle>> parEtat = SalleUtils.regrouperParEtat(toutes);
        System.out.println("Salles regroupées par état : " + parEtat);

        Map<String, List<Salle>> parEquipement = SalleUtils.regrouperParEquipement(toutes);
        System.out.println("Salles regroupées par équipement : " + parEquipement);

        Salle salleTrouvee = SalleUtils.trouverOuException(toutes, "A");
        System.out.println("Salle trouvée par id=A : " + salleTrouvee.description());
    }
}
