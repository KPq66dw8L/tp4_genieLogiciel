package gestionVol;

import java.util.HashSet;
import java.util.Set;

public class Ville {
    private String nom;
    private Set<Aeroport> aeroports; // la collection des aéroports qui desservent la ville

    /**
     * Constructeur
     */
    public Ville (String nom) {
        this.nom = nom;
        this.aeroports = new HashSet<>();
    }
    public Ville () {
        this.aeroports = new HashSet<>();
    }

    /**
     * Simple fonction.
     * @param nom
     */
    public void setNom (String nom) {
        this.nom = nom;
    }

    /**
     * Simple fonction.
     * @return nom de la ville
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Ajouter un aéroport qui dessert la ville
     * @param aeroport
     */
    public void ajouterAeroport(Aeroport aeroport) {
        this.aeroports.add(aeroport);
    }

    /**
     * Obtenir les aéroports qui desservent la ville
     * @return
     */
    public Set<Aeroport> getAeroports() {
        return this.aeroports;
    }
}
