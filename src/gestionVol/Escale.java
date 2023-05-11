package gestionVol;

import java.time.Duration;
import java.time.ZonedDateTime;

public class Escale {
    private Aeroport aeroport;
    private ZonedDateTime dateDepart;
    private ZonedDateTime dateArrivee;
    private Duration duree;

    public Escale(Aeroport a){
        this.aeroport = a;
    }

    /**
     * Setter date départ du vol
     * @param dateDepart Date
     * @return
     */
    public Escale setDateDepart(ZonedDateTime dateDepart) throws Exception {
        if (dateArrivee != null ){
            if (dateDepart.compareTo(dateArrivee) < 0){
                this.dateDepart = dateDepart;
                this.duree = obtenirDuree();
                return this;
            } else {
                throw new Exception("Date de départ invalide.");
            }
        } else {
            this.dateDepart = dateDepart;
            return this;
        }
    }

    /**
     * Setter date d'arrivée du vol
     * @param dateArrivee Date
     * @return
     */
    public Escale setDateArrivee(ZonedDateTime dateArrivee) throws Exception {
        if (dateDepart != null ){
            if (dateDepart.compareTo(dateArrivee) < 0){
                this.dateArrivee = dateArrivee;
                this.duree = obtenirDuree();
                return this;
            } else {
                throw new Exception("Date d'arrivée' invalide.");
            }
        } else {
            this.dateArrivee = dateArrivee;
            return this;
        }
    }

    /**
     * Getter date de depart
     * @return dateDepart
     */
    public ZonedDateTime getDateDepart() {
        return dateDepart;
    }

    /**
     * Getter date d'arrivee
     * @return dateArrivee
     */
    public ZonedDateTime getDateArrivee() {
        return dateArrivee;
    }

    /**
     * Durée de l'escale.
     * @return duree
     */
    public Duration obtenirDuree() {
        if(this.dateDepart != null && this.dateArrivee != null) {
            return Duration.between(dateDepart, dateArrivee);
        }
        return null;
    }
}
