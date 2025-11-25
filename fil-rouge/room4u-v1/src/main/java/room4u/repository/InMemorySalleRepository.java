package room4u.repository;

import room4u.domain.Salle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repository en mémoire pour les salles.
 */
public class InMemorySalleRepository implements Repository<String, Salle> {

    private final Map<String, Salle> salles = new HashMap<>();

    @Override
    public void ajouter(Salle salle) {
        salles.put(salle.id(), salle);
    }

    @Override
    public Optional<Salle> trouverParId(String id) {
        return Optional.ofNullable(salles.get(id));
    }

    @Override
    public List<Salle> toutes() {
        return Collections.unmodifiableList(new ArrayList<>(salles.values()));
    }
}
