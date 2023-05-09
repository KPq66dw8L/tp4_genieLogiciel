package reservation;

public class Passager {
    private String nom;

    /**
     * Init le nom du passager
     */
    public Passager (String n) {
        this.nom = n;
    }

    /**
     * Setter nom du passager
     * @param nom String
     */
    public void setNom (String nom) {
        this.nom = nom;
    }

    /**
     * Getter nom du passager
     * @return nom en String
     */
    public String getNom() {
        return this.nom;
    }

}
