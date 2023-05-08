package reservation;

import gestionVol.Vol;

import java.util.UUID;
import java.time.ZonedDateTime ;

public class Reservation {

    private String client;
    private String uniqueID;
    private ZonedDateTime date;
    private boolean confirmed = false;
    private final Vol vol;

    /**
     * Constructeur
     * @param clientRef
     */
    public Reservation(String clientRef, Vol v) {
        this.uniqueID = UUID.randomUUID().toString();
        this.date = ZonedDateTime.now();
        this.client = clientRef;
        paiement();
        this.vol = v; // TODO: gérer Passager et ajouter celui-ci dans Vol de manière bidirectionnel
    }

    /**
     * TODO: debiter le client, se fait lors de la creation de la reservation
     */
    public void paiement(){

    }

    /**
     * TODO: confirmation automatique X temps avant le vol?? ou plus simple?
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
     * TODO: remboursement si avant confirmation, sinon juste annulation
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
