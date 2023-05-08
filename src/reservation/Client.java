package reservation;

import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Client {
    private String nom;
    private String ref;
    private String creditCard;
    private String mailAddress;
    private static final Pattern VALIDATION_VISA = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
    private static final Pattern VALIDATION_MASTERCARD = Pattern.compile("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$");
    private static final Pattern VALIDATION_AMEX = Pattern.compile("^3[47][0-9]{13}$");
    private static final Pattern VALIDATION_MAIL = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    /**
     * Fonction simple
     */
    public Client(String n){
        this.ref = UUID.randomUUID().toString();
        this.nom = n;
    }

    /**
     * Fonction simple
     * @return
     */
    public String getReference(){
        return this.ref;
    }

    /**
     * Configure le moyen de paiement, actuellement accepte uniquement VISA
     * @param cc
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
     * Fonction simple
     * @return
     */
    public String getPaiement(){
        return this.creditCard;
    }

    /**
     * Configure le contact du client, uniquement une adresse mail actuellement
     * @param mail
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
     * Fonction simple
     * @return
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Fonction simple
     * @param n
     */
    public void setNom(String n){
        this.nom = n;
    }
}
