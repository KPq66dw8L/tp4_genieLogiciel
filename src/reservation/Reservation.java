package reservation;

import gestionVol.Vol;

import java.util.Objects;
import java.util.UUID;
import java.time.ZonedDateTime ;

public final class Reservation {

    private final String clientR;
    private final String uniqueID;
    private final ZonedDateTime date;
    private boolean confirmed;
    private final Vol vol;
    // Association avec Passager
    private Passager passager;
    private boolean payed;

    /**
     * Init la confirmation et le paiement de la reservation a false, init ID, init date de reservation, recupere reference client et le vol,
     * tente d'effectuer le paiement
     * @param client qui effectue la reservation
     * @param v = vol associé à la réservation
     */
    public Reservation(Client client, Vol v) throws Exception {
        this.confirmed = false;
        this.payed = false;
        this.uniqueID = UUID.randomUUID().toString();
        this.date = ZonedDateTime.now();
        this.clientR = client.getReference();
        paiement(client);
        if (v.isOuvert()){
            this.vol = v;
        } else {
            throw new Exception("Vol fermé, impossible de réserver pour ce vol.");
        }
    }

    /**
     * Effectue paiement == débite le client
     * @param client qui paye
     */
    private void paiement(Client client){
        if (client.getPaiement() != null && Objects.equals(client.getReference(), this.clientR)){
            this.payed = true;
        }
    }

    /**
     * Confirmation de la reservation, apres avoir paye, on doit fournir le client pour certifier que le client n'a rien changé de son compte, sinon la réservation
     * pourrait changer.
     * @param c qui confirme
     * @throws IllegalArgumentException
     */
    public void confirmer(Client c) throws Exception {
        if (!(c.getReference() == this.clientR)){
            throw new IllegalArgumentException("Wrong client.");
        }
        if (c.getPaiement() != null && c.getNom() != null && c.getReference() != null && this.payed && this.vol.isOuvert()){
            this.confirmed = true;
            this.passager = new Passager(c.getNom()); // Réservation confirmée, donc passager du vol
        } else {
            throw new Exception("Impossible de confimer. Vous n'avez peut-etre pas paye, ou votre compte est incomplet, ou le vol est fermé.");
        }
    }

    /**
     * Si un client a deja confirmé sa reservation et annule() alors juste annulée. Sinon le client est 'remboursé' et sa réservation annulée.
     * L'annulation rend la réservation inutilisable et invalide.
     */
    public void annuler(){
        if (!this.confirmed) {
            this.payed = false; // remboursement
        } else {
            System.out.println("Ayant déjà confirmé votre réservation, votre réservation est annulée sans remboursement.");
        }
        this.passager = null;
        this.confirmed = false;
    }

    /**
     * Repond a la qst: est ce que la reservation est payée?
     * @return payed boolean
     */
    public boolean isPayed() {
        return this.payed;
    }

    /**
     * Repond a la qst: est ce que la reservation est confirmee?
     * @return confirmed boolean
     */
    public boolean isConfirmed() {
        return this.confirmed;
    }

//    /**
//     * Setter passager 1
//     * @param passager objet Passager
//     */
//    public void setPassager(Passager passager) {
//        if (this.confirmed){
//            this.passager = passager;
//        }
//    }
//
//    /**
//     * Setter passager 2
//     * @param c passager objet Client
//     */
//    public void setPassager(Client c){
//        if (this.confirmed){
//            this.passager = new Passager(c.getNom());
//        }
//    }

//    /**
//     * Setter client
//     * @param client effectuant la réservation
//     */
//    public void setClient(Client client) {
//        this.clientR = client.getReference();
//    }

    /**
     * Getter passager
     * @return passager
     */
    public Passager getPassager() {
        return this.passager;
    }

    /**
     * Getter du boolean de confirmation de la reservation
     * @return état de confirmation
     */
    public boolean getConfirmer(){
        return this.confirmed;
    }

    /**
     * Fonction simple.
     * @return reference client
     */
    public String getClient() {
        return clientR;
    }

    /**
     * Getter du vol de la reservation
     * @return obj vol
     */
    public Vol getVol(){
        return this.vol;
    }

    /**
     * Getter client reference
     * @return clientR
     */
    public String getClientR() {
        return this.clientR;
    }

    /**
     * Getter ID reservation
     * @return uniqueID
     */
    public String getUniqueID() {
        return this.uniqueID;
    }

    /**
     * Getter date de la reservation
     * @return date
     */
    public ZonedDateTime getDate() {
        return this.date;
    }
}
