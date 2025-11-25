package room4u.repository;

import room4u.domain.Identifiable;

import java.util.List;
import java.util.Optional;

/**
 * Repository générique pour des entités identifiables.
 */
public interface Repository<ID, T extends Identifiable<ID>> {

    void ajouter(T entity);

    Optional<T> trouverParId(ID id);

    List<T> toutes();
}
