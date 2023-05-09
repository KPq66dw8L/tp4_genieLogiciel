package gestionVol;

import reservation.Reservation;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Vol {

    private String numero;
    private Aeroport depart;
    private Aeroport arrivee;
    private LinkedHashSet<Escale> escales; // ordered & unique
    private Compagnie compagnie;
    private ZonedDateTime dateDepart;
    private ZonedDateTime dateArrivee;
    private boolean ouvert;
    private Duration duree;
    private HashSet<Reservation> reservations;

    public Vol() {
        this.escales = new LinkedHashSet<>();
        this.reservations = new HashSet<>();
    }

    /**
     * Init:
     * - ID du vol
     * - liste des escales (vide)
     * - check validité des dates
     * - calcul la durée du vol
     * @param numero ID du vol
     * @param date1 date de depart du vol
     * @param date2 date d'arrivée du vol
     */
    public Vol(String numero, ZonedDateTime date1, ZonedDateTime date2) throws Exception {
        this.numero = numero;
        this.escales = new LinkedHashSet<>();
        if (date1.compareTo(date2) < 0){
            this.dateDepart = date1;
            this.dateArrivee = date2;
        } else{
            throw new Exception("Dates invalides.");
        }
        this.duree = obtenirDuree();
        this.reservations = new HashSet<>();
    }

    /**
     * Calcul l'attribut dérivé 'duree' = durée du vol
     * @return duree de type Duration
     */
    private Duration obtenirDuree() {
        if(this.dateDepart != null && this.dateArrivee != null) {
            return Duration.between(dateDepart, dateArrivee);
        }
        return null;
    }

    /**
     * Ouvre le vol
     */
    public void ouvrir(){
        this.ouvert = true;
    }

    /**
     * Ferme le vol
     */
    public void fermer() {
        this.ouvert = false;
    }

    /**
     * Setter reservations sans bidirection (le vol ne se retire pas des reservations précédentes)
     */
    public void setReservationsWithoutBidirectional(HashSet<Reservation> rs){
        this.reservations = rs;
    }

    /**
     * Annule les reservations courantes et les remplace par une nouvelle liste de reservations.
     * @param rs set de reservations
     */
    public void setReservations(HashSet<Reservation> rs){
        for (Reservation r : this.reservations){
            r.annuler();
        }
        this.reservations = rs;
        for (Reservation r : rs){
            if (r.getPassager() == null){
                this.reservations = new HashSet<>();
            }
        }
    }

    /**
     * Ajoute une réservation dans le set. Un passager est associé a une reservation lorsque celle-ci est confirmée, donc on verifie que les reservations
     * sont bien confirmées avant d'etre comptabilisees dans le vol.
     * @param r 1 reservation
     */
    public void addReservation(Reservation r){
        if (r.getPassager() != null && r.getVol().equals(this)){
            this.reservations.add(r);
        }
    }

    /**
     * Setter date départ du vol
     * @param dateDepart Date
     */
    public void setDateDepart(ZonedDateTime dateDepart){
        this.dateDepart = dateDepart;
        this.duree = obtenirDuree();
    }

    /**
     * Setter date d'arrivée du vol
     * @param dateArrivee Date
     */
    public void setDateArrivee(ZonedDateTime dateArrivee){
        this.dateArrivee = dateArrivee;
        this.duree = obtenirDuree();
    }

    /**
     * S'ajoute comme vol dans la company en parametre.
     * Si le vol est actuellement à une company alors on se retire de celle-ci et on change sa company actuelle.
     * @param compagnie nouvelle
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
     * @param compagnie nouvelle
     */
    protected void setCompagnieWithoutBidirectional(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    /**
     * Setter de l'attribut numero
     * @param numero ID du vol
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Setter de date de depart
     * @param depart Date
     */
    public void setDepart(Aeroport depart) {
        this.depart = depart;
    }

    /**
     * Setter date d'arrivee
     * @param arrivee Date
     */
    public void setArrivee(Aeroport arrivee) {
        this.arrivee = arrivee;
    }

    /**
     * Remplace la liste d'escales actuelle par une nouvelle liste.
     * @param escales LinkedHashSet
     */
    public void setEscales(LinkedHashSet<Escale> escales){
        this.escales = escales;
    }

    /**
     * Ajoute une nouvelle escale dans la liste actuelle.
     * @param escale Escale
     */
    public void addEscale(Escale escale) throws Exception{
        try {
            if (this.escales.contains(escale)){
                throw new Exception("Cette escale fait deja partie du vol.");
            } else {
                this.escales.add(escale);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Getter liste des reservations
     * @return set contenant les reservations du vol
     */
    public HashSet<Reservation> getReservations(){
        return this.reservations;
    }

    /**
     * Getter de durée
     * @return duree
     */
    public Duration getDuree(){
        return this.duree;
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
     * Getter de la compagnie du vol
     * @return compagnie
     */
    public Compagnie getCompagnie() {
        return compagnie;
    }

    /**
     * Getter numero du vol
     * @return numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Getter aeroport de depart
     * @return depart aeroport
     */
    public Aeroport getDepart() {
        return depart;
    }

    /**
     * Getter aeroport d'arrivee
     * @return arrivee aeroport
     */
    public Aeroport getArrivee() {
        return arrivee;
    }

    /**
     * Getter liste d'escales du vol
     * @return la liste des escales (ordonee et unique)
     */
    public LinkedHashSet<Escale> getEscales(){
        return this.escales;
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
