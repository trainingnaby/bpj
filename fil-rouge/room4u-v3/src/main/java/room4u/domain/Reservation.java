package room4u.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Représente une réservation de salle.
 */
public class Reservation implements Identifiable<String> {

    private final String id;
    private final String utilisateur;
    private final Salle salle;
    private final Creneau creneau;
    private final EtatReservation etat;
    private final BigDecimal prix;

    public Reservation(String id,
                       String utilisateur,
                       Salle salle,
                       Creneau creneau,
                       EtatReservation etat,
                       BigDecimal prix) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id ne doit pas être nul ou vide");
        }
        if (utilisateur == null || utilisateur.isBlank()) {
            throw new IllegalArgumentException("utilisateur ne doit pas être nul ou vide");
        }
        this.salle = Objects.requireNonNull(salle, "salle ne doit pas être null");
        this.creneau = Objects.requireNonNull(creneau, "creneau ne doit pas être null");
        if (salle.etat() == EtatSalle.MAINTENANCE) {
            throw new SalleIndisponibleException("Impossible de réserver une salle en MAINTENANCE");
        }
        this.id = id;
        this.utilisateur = utilisateur;
        this.etat = etat == null ? EtatReservation.EN_ATTENTE : etat;
        if (prix == null || prix.signum() < 0) {
            throw new IllegalArgumentException("prix ne doit pas être nul ou négatif");
        }
        this.prix = prix;
    }

    @Override
    public String id() {
        return id;
    }

    public String utilisateur() {
        return utilisateur;
    }

    public Salle salle() {
        return salle;
    }

    public Creneau creneau() {
        return creneau;
    }

    public EtatReservation etat() {
        return etat;
    }

    public BigDecimal prix() {
        return prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", utilisateur='" + utilisateur + '\'' +
                ", salle=" + salle.id() +
                ", creneau=" + creneau +
                ", etat=" + etat +
                ", prix=" + prix +
                '}';
    }
}
