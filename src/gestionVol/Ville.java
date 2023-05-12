package gestionVol;

import java.util.HashSet;
import java.util.Set;

public class Ville {
    private String nom;
    private Set<Aeroport> aeroports; // la collection des aéroports qui desservent la ville

    /**
     * Init le nom de la ville et le set des aeroports qu'elle contient
     */
    public Ville (String nom) {
        this.nom = nom;
        this.aeroports = new HashSet<>();
    }
    public Ville () {
        this.aeroports = new HashSet<>();
    }

    /**
     * Ajouter un aéroport qui dessert la ville
     * @param aeroport
     */
    public void ajouterAeroport(Aeroport aeroport) {
        aeroport.setVilleWithoutBidirectional(this);
        this.aeroports.add(aeroport);
    }

    /**
     * Ajouter un aéroport qui dessert la ville
     * @param aeroport
     */
    public void ajouterAeroportWithoutBidirectional(Aeroport aeroport) {
        this.aeroports.add(aeroport);
    }

    /**
     * Change la liste des aeroports de la ville
     * @param aeroport
     */
    public void setAeroports(Aeroport aeroport) {
        this.aeroports.add(aeroport);
    }

    /**
     * Setter du nom de la ville
     * @param nom
     */
    public void setNom (String nom) {
        this.nom = nom;
    }

    /**
     * Getter du nom de la ville
     * @return nom de la ville
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Obtenir les aéroports qui desservent la ville
     * @return
     */
    public Set<Aeroport> getAeroports() {
        return this.aeroports;
    }
}
