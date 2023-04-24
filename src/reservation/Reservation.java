package reservation;

import java.util.UUID;
import java.time.ZonedDateTime ;

public class Reservation {

    private String client;
    private String uniqueID;
    private ZonedDateTime date;
    private boolean confirmed = false;

    public Reservation(String clientRef) {
        this.uniqueID = UUID.randomUUID().toString();
        this.date = ZonedDateTime.now();
        this.client = clientRef;
    }

    public void confirmer(Client c) throws IllegalArgumentException {
        if (!(c.getReference() == this.client)){
            throw new IllegalArgumentException("Wrong client.");
        }
        if (c.getPaiement() != null || c.getNom() != null || c.getReference() != null){
            this.confirmed = true;
        }
    }
    public void annuler(){
        this.confirmed = false;
    }

    /**
     *
     * @return
     */
    public String getClient() {
        return client;
    }

    /**
     *
     * @param client
     */
    public void setClient(String client) {
        this.client = client;
    }
}
