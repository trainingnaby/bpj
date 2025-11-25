package room4u.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Représente une salle dans le système Room4U.
 * Classe immuable.
 */
public final class Salle implements Identifiable<String> {

    private final String id;
    private final String nom;
    private final int capacite;
    private final Set<String> equipements;
    private final EtatSalle etat;

    public Salle(String id, String nom, int capacite, Set<String> equipements, EtatSalle etat) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id ne doit pas être nul ou vide");
        }
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("nom ne doit pas être nul ou vide");
        }
        if (capacite <= 0) {
            throw new IllegalArgumentException("capacite doit être > 0");
        }
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.equipements = equipements == null
                ? Collections.emptySet()
                : Collections.unmodifiableSet(new HashSet<>(equipements));
        this.etat = etat == null ? EtatSalle.DISPONIBLE : etat;
    }

    @Override
    public String id() {
        return id;
    }

    public String nom() {
        return nom;
    }

    public int capacite() {
        return capacite;
    }

    public Set<String> equipements() {
        return equipements;
    }

    public EtatSalle etat() {
        return etat;
    }

    /**
     * Description textuelle riche de la salle.
     */
    public String description() {
        return String.format(
                "%s (id=%s, capacite=%d, equipements=%s, etat=%s)",
                nom, id, capacite, equipements, etat
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salle salle = (Salle) o;
        return Objects.equals(id, salle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Salle{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", capacite=" + capacite +
                ", equipements=" + equipements +
                ", etat=" + etat +
                '}';
    }
}
