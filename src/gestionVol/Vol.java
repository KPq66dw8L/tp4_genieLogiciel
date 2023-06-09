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
        this.ouvert = false;
    }

    public Vol() {
        this.escales = new LinkedHashSet<>();
        this.reservations = new HashSet<>();
        this.ouvert = false;
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
    public void ouvrir() throws Exception {
        if (    this.numero != null &&
                this.depart != null &&
                this.arrivee != null &&
                this.compagnie != null &&
                this.dateDepart != null &&
                this.dateArrivee != null){
            this.ouvert = true;
        } else{
            throw new Exception("Le vol n'est pas valide. Il manque une des composantes du vol parmi les suivantes: " +
                    "numéro de vol, " +
                    "aéroport de départ, " +
                    "aéroport d'arrivée, " +
                    "compagnie du vol, " +
                    "date de départ, " +
                    "date d'arrivée.");
        }
    }

    /**
     * Ferme le vol. Il est recommandé d'annuler les réservations faites après avoir fermé un vol en utilisant setReservations().
     */
    public void fermer() {
        this.ouvert = false;
    }

    /**
     * Repond a la question: est ce que le vol est ouvert?
     */
    public boolean isOuvert(){
        return this.ouvert;
    }

    /**
     * Setter reservations sans bidirection (le vol ne se retire pas des reservations précédentes). Indisponible si le vol est fermé.
     */
    public void setReservationsWithoutBidirectional(HashSet<Reservation> rs) throws Exception {
        if (this.ouvert){
            this.reservations = rs;
        } else {
            throw new Exception("Vol fermé impossible de faire de réservation.");
        }
    }

    /**
     * Annule les reservations courantes et les remplace par une nouvelle liste de reservations. Indisponible si le vol est fermé.
     * @param rs set de reservations
     */
    public void setReservations(HashSet<Reservation> rs) throws Exception {
        if (this.ouvert){
            for (Reservation r : this.reservations){
                r.annuler();
            }
            this.reservations = rs;
            for (Reservation r : rs){
                if (r.getPassager() == null){
                    this.reservations = new HashSet<>();
                }
            }
        } else {
            throw new Exception("Vol fermé impossible de faire de réservation.");
        }
    }

    /**
     * Ajoute une réservation dans le set. Un passager est associé a une reservation lorsque celle-ci est confirmée, donc on verifie que les reservations
     * sont bien confirmées avant d'etre comptabilisees dans le vol.
     * @param r 1 reservation
     */
    public void addReservation(Reservation r) throws Exception {
        if (!this.ouvert){
            throw new Exception("Impossible de réserver, le vol est fermé.");
        }
        if (r.getPassager() != null && r.getVol().equals(this)){
            this.reservations.add(r);
        } else {
            throw new Exception("Pas de passager lié à cette réservation ou vol de la réservation différent de ce vol.");
        }
    }

    /**
     * Setter date départ du vol
     * @param dateDepart Date
     * @return
     */
    public Vol setDateDepart(ZonedDateTime dateDepart) throws Exception {
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
     * @param dateArrivee Date
     * @return
     */
    public Vol setDateArrivee(ZonedDateTime dateArrivee) throws Exception {
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
    public void setCompagnieWithoutBidirectional(Compagnie compagnie) {
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
    public void setDepart(Aeroport depart) throws Exception {
        if (depart.getVille() != null){
            this.depart = depart;
        } else {
            throw new Exception("Impossible d'assigner le départ du vol à cet aeroport, car l'aeroport n'est localisé dans aucune ville.");
        }
    }

    /**
     * Setter date d'arrivee
     * @param arrivee Date
     */
    public void setArrivee(Aeroport arrivee) throws Exception {
        if (depart.getVille() != null){
            this.arrivee = arrivee;
        } else {
            throw new Exception("Impossible d'assigner le départ du vol à cet aeroport, car l'aeroport n'est localisé dans aucune ville.");
        }
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
