package reservation;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Client {
    private String nom;
    private final String ref;
    private String creditCard; //paiement
    private String mailAddress; //contact
    private ArrayList<Reservation> reservationsEffectuees;
    private static final Pattern VALIDATION_VISA = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
    private static final Pattern VALIDATION_MASTERCARD = Pattern.compile("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$");
    private static final Pattern VALIDATION_AMEX = Pattern.compile("^3[47][0-9]{13}$");
    private static final Pattern VALIDATION_MAIL = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    /**
     * Init reference client, init nom du client, init liste de reservations
     */
    public Client(String n){
        this.ref = UUID.randomUUID().toString();
        this.nom = n;
        this.reservationsEffectuees = new ArrayList<>();
    }

    /**
     * Configure le moyen de paiement
     * @param cc numero de la carte de credit du client
     * @throws IllegalArgumentException
     */
    public void setPaiement(String cc) throws IllegalArgumentException{
        Matcher m1 = VALIDATION_VISA.matcher(cc);
        Matcher m2 = VALIDATION_MASTERCARD.matcher(cc);
        Matcher m3 = VALIDATION_AMEX.matcher(cc);
        if (!m1.matches() && !m2.matches() && !m3.matches()){
            throw new IllegalArgumentException("Credit Card not valid.");
        } else {
            this.creditCard = cc;
        }
    }
    /**
     * Configure le contact du client, uniquement une adresse mail actuellement
     * @param mail du client
     * @throws IllegalArgumentException
     */
    public void setContact(String mail) throws IllegalArgumentException{
        Matcher m = VALIDATION_MAIL.matcher(mail);
        if (!m.matches()){
            throw new IllegalArgumentException("Mail address not valid.");
        } else {
            this.mailAddress = mail;
        }
    }

    /**
     * Setter nom du client
     * @param n nom
     */
    public void setNom(String n){
        this.nom = n;
    }

    /**
     * Ajoute une réservation à la liste existante.
     * @param r 1 reservation
     */
    public void addReservation(Reservation r) throws Exception {
        if (r.getClient().equals(this.ref)){
            this.reservationsEffectuees.add(r);
        } else {
            throw new Exception("Reservation appartient a un autre client.");
        }
    }

//    /**
//     * Ajoute une reservation à la liste existante, apres avoir clean la reservation et s'etre mis client dans celle-ci
//     * @param r 1 reservation
//     */
//    public void addReservation(Reservation r){
//        r.annuler();
//        r.setClient(this);
//        r.paiement(this);
//        this.reservationsEffectuees.add(r);
//    }

    /**
     * Remplace la liste existante de réservation par une nouvelle liste, monodirectionnel.
     * @param r liste de reservations
     */
    public void setReservationsWithoutBidirectional(ArrayList<Reservation> r){
        this.reservationsEffectuees = r;
    }

    /**
     * Annule les reservations courantes du client, remplace sa liste de reservations courante par une nouvelle, et se met client dans ces reservations.
     * @param r liste de reservations
     */
    public void setReservations(ArrayList<Reservation> r){
        for (Reservation re : this.reservationsEffectuees){
            re.annuler();
        }
//        for (Reservation re : r){
//            re.annuler();
//            re.setClient(this);
//            re.paiement(this);
//        }
        this.reservationsEffectuees = r;
    }

    /**
     * Getter reference client
     * @return ref client
     */
    public String getReference(){
        return this.ref;
    }

    /**
     * Getter du moyen de paiement
     * @return numero carte bancaire en String
     */
    public String getPaiement(){
        return this.creditCard;
    }

    /**
     * Getter adresse mail client
     * @return adresse mail en String
     */
    public String getContact(){
        return this.mailAddress;
    }

    /**
     * Getter nom du client
     * @return nom en String
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Getter liste de reservations
     * @return liste de reservations
     */
    public ArrayList<Reservation> getReservations(){
        return this.reservationsEffectuees;
    }
}
