package room4u.service;

import room4u.annotations.UseCase;
import room4u.domain.EtatSalle;
import room4u.domain.Salle;

import java.util.List;
import java.util.Map;

/**
 * Service métier pour la gestion des salles.
 */
public interface SalleService {

    @UseCase("Lister toutes les salles")
    List<Salle> listerToutesLesSalles();

    @UseCase("Lister les salles disponibles")
    List<Salle> listerSallesDisponibles();

    @UseCase("Rechercher des salles par capacité et équipement")
    List<Salle> rechercherParCapaciteEtEquipement(int capaciteMin, String equipement);

    @UseCase("Regrouper les salles par état")
    Map<EtatSalle, List<Salle>> regrouperParEtat();
}
