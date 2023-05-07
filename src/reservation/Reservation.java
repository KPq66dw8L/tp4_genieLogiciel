package reservation;

import java.util.UUID;
import java.time.ZonedDateTime ;

public class Reservation {

    private String client;
    private String uniqueID;
    private ZonedDateTime date;
    private boolean confirmed = false;

    /**
     * Constructeur
     * @param clientRef
     */
    public Reservation(String clientRef) {
        this.uniqueID = UUID.randomUUID().toString();
        this.date = ZonedDateTime.now();
        this.client = clientRef;
    }

    /**
     * Confirmation de la reservation, apres avoir paye et avant le vol
     * @param c
     * @throws IllegalArgumentException
     */
    public void confirmer(Client c) throws IllegalArgumentException {
        if (!(c.getReference() == this.client)){
            throw new IllegalArgumentException("Wrong client.");
        }
        if (c.getPaiement() != null || c.getNom() != null || c.getReference() != null){
            this.confirmed = true;
        }
    }

    /**
     * Fonction simple
     */
    public void annuler(){
        this.confirmed = false;
    }

    /**
     * Fonction simple.
     * @return
     */
    public String getClient() {
        return client;
    }

    /**
     * Fonction simple.
     * @param client
     */
    public void setClient(String client) {
        this.client = client;
    }
}
