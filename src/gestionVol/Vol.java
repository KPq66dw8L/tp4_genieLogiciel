package gestionVol;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Vol {

    private String numero;
    private Aeroport depart;
    private Aeroport arrivee;
    private Compagnie compagnie;
    private Date dateDepart;
    private Date dateArrivee;
    private boolean ouvert;

    /**
     * Constructeur
     * @param numero
     */
    protected Vol(String numero){
        this.numero = numero;
    }

    public Vol() {
    }

    /**
     * Permet de calculer l'attribut dérivé 'duree'
     * @return duree
     */
    public Duration obtenirDuree() {
        if(this.dateDepart != null && this.dateArrivee != null) {
            return Duration.of(dateArrivee.getTime() - dateDepart.getTime(), ChronoUnit.MILLIS);
        }
        return null;
    }

    /**
     * Simple fonction.
     * @return
     */
    public Date getDateDepart() {
        return dateDepart;
    }

    /**
     * Simple fonction.
     * @param dateDepart
     */
    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    /**
     * Simple fonction.
     * @return
     */
    public Date getDateArrivee() {
        return dateArrivee;
    }

    /**
     * Simple fonction.
     * @param dateArrivee
     */
    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    /**
     * Simple fonction
     * @return
     */
    public Compagnie getCompagnie() {
        return compagnie;
    }

    /**
     * S'ajoute comme vol dans la company en parametre.
     * Si le vol est actuellement à une company alors on se retire de celle-ci et on change la company actuelle en attribut.
     * @param compagnie
     */
    public void setCompagnie(Compagnie compagnie) {
        if(compagnie!=null) {
            compagnie.addVolWithoutBidirectional(this);
        }
        if(this.compagnie!=null){
            this.compagnie.removeVolWithoutBidirectional(this);
        }
        this.compagnie = compagnie;
    }

    /**
     * Change la company du vol, sans retirer le vol de la liste de vols de la company précédente et sans s'ajouter dans la nouvelle company.
     * @param compagnie
     */
    protected void setCompagnieWithoutBidirectional(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    /**
     * Simple fonction.
     * @return
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Simple fonction
     * @param numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Simple fonction.
     * @return
     */
    public Aeroport getDepart() {
        return depart;
    }

    /**
     * Simple fonction.
     * @param depart
     */
    public void setDepart(Aeroport depart) {
        this.depart = depart;
    }

    /**
     * Simple fonction.
     * @return
     */
    public Aeroport getArrivee() {
        return arrivee;
    }

    /**
     * Simple fonction.
     * @param arrivee
     */
    public void setArrivee(Aeroport arrivee) {
        this.arrivee = arrivee;
    }

    /**
     * Vérifie si 2 vols possèdent le meme numéro.
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        try {
            return ((Vol) obj).getNumero().equals(this.numero);
        } catch (Exception e){
            return false;
        }
    }
}