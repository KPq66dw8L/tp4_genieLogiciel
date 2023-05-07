package gestionVol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Aeroport {

    private String nom;
    private Ville ville;
    private Set<Ville> dessert;

    /**
     * Constructeur
     */
    public Aeroport(String nom) {
        this.nom = nom;
        this.dessert = new HashSet<>();
    }
    public Aeroport() {
        this.dessert = new HashSet<>();
    }

    /**
     * Simple fonction.
     * @return nom de l'aeroport
     */
    public String getNom() {
        return nom;
    }

    /**
     * Simple fonction.
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Simple fonction.
     * @return le nom de la ville
     */
    public String getVille() {
        return ville.getNom();
    }

    /**
     * Simple fonction.
     * @param ville
     */
    public void setVille(String ville) {
        this.ville.setNom(ville);
    }

    /**
     * la méthode pour ajouter une ville desservie
     * @param ville
     */
    public void ajouterVille(Ville ville) {
        this.dessert.add(ville);
    }

    /**
     * la méthode pour obtenir les villes desservies
     * @return
     */
    public Set<Ville> getVilles() {
        return this.dessert;
    }
}
