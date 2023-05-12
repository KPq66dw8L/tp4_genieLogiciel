package gestionVol;

import java.util.HashSet;
import java.util.Set;

public class Aeroport {

    private String nom;
    private Ville ville;
    private Set<Ville> villesDesservies;

    /**
     * Init nom de l'aeroport et set des villes desservies
     */
    public Aeroport(String nom) {
        this.nom = nom;
        this.villesDesservies = new HashSet<>();
    }
    public Aeroport() {
        this.villesDesservies = new HashSet<>();
    }

    /**
     * Ajouter une ville desservie
     * @param ville
     */
    public void ajouterVilleDesservie(Ville ville) {
        this.villesDesservies.add(ville);
    }

    /**
     * Setter nom de l'aeroport
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter de la ville de l'aeroport, ajoute aussi l'aeroport dans la liste de la ville.
     * @param ville Ville
     */
    public void setVille(Ville ville) {
        ville.ajouterAeroportWithoutBidirectional(this);
        this.ville = ville;
    }

    /**
     * Setter de la ville de l'aeroport.
     * @param ville Ville
     */
    public void setVilleWithoutBidirectional(Ville ville) {
        this.ville = ville;
    }

    /**
     * Remplace le set de villes desservies par un nouveau
     * @param nv
     */
    public void setVillesDesservies(HashSet<Ville> nv){
        this.villesDesservies = nv;
    }

    /**
     * Getter ville dans laquelle se trouve l'aeroport
     * @return le nom de la ville
     */
    public String getVille() {
        return ville.getNom();
    }

    /**
     * Getter nom de l'aeroport
     * @return nom de l'aeroport
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter des villes desservies
     * @return set
     */
    public Set<Ville> getVilles() {
        return this.villesDesservies;
    }
}
