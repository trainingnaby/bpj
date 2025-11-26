package room4u.service;

import room4u.domain.EtatSalle;
import room4u.domain.Salle;
import room4u.domain.SalleUtils;
import room4u.repository.InMemorySalleRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implémentation par défaut de SalleService.
 */
public class DefaultSalleService implements SalleService {

    private final InMemorySalleRepository salleRepository;

    public DefaultSalleService(InMemorySalleRepository salleRepository) {
        this.salleRepository = Objects.requireNonNull(salleRepository);
    }

    @Override
    public List<Salle> listerToutesLesSalles() {
        return salleRepository.toutes();
    }

    @Override
    public List<Salle> listerSallesDisponibles() {
        return salleRepository.toutes().stream()
                .filter(s -> s.etat() == EtatSalle.DISPONIBLE)
                .collect(Collectors.toList());
    }

    @Override
    public List<Salle> rechercherParCapaciteEtEquipement(int capaciteMin, String equipement) {
        List<Salle> salles = salleRepository.toutes();
        List<Salle> filtreCapacite = SalleUtils.filtrerParCapacite(salles, capaciteMin);
        return SalleUtils.filtrerParEquipement(filtreCapacite, equipement);
    }

    @Override
    public Map<EtatSalle, List<Salle>> regrouperParEtat() {
        return SalleUtils.regrouperParEtat(salleRepository.toutes());
    }
}
