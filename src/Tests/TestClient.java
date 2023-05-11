package Tests;

import com.beust.ah.A;
import gestionVol.Aeroport;
import gestionVol.Compagnie;
import gestionVol.Vol;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reservation.Client;
import reservation.Reservation;

import java.time.ZonedDateTime;

import static org.testng.Assert.*;

public class TestClient {

    Client c1;
    Client c2;
    Client c3;
    Reservation r1;

    @BeforeMethod
    public void initsClients(){
         c1 = new Client("Pablo");
         c2 = new Client("Pablo");
    }

    @BeforeGroups("Contient vol")
    public void initsVol() throws Exception {
        c3 = new Client("Pablo");

        Compagnie cm1 = new Compagnie().setName("Compagnie 1");
        Vol v1 = new Vol("AF11", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
        cm1.addVol(v1);
        Aeroport a11 = new Aeroport("Aéroport départ");
        Aeroport a12 = new Aeroport("Aéroport arrivé");
        v1.setDepart(a11);
        v1.setArrivee(a12);
        v1.ouvrir();
        r1 = new Reservation(c3, v1);
    }

    @Test
    public void testCreationClient(){
        assertNotNull(c1.getReference()); // on a assigné une reference au client
        assertNotNull(c1.getReservations()); // la liste de reservations a etait cree
        assertEquals(c1.getReservations().size(), 0); // cette liste est bien vide

        assertNotEquals(c1.getReference(), c2.getReference()); // malgré que les deux clients ont le meme nom, leur references est bien unique
    }

    @Test
    public void testPaiement(){
        // exemple paiement VISA:
        c1.setPaiement("4272505668446363");
        // exemple paiement MASTERCARD:
        c1.setPaiement("2483571672956135");
        // exemple paiement AMEX:
        c1.setPaiement("377732303291515");

        assertThrows(IllegalArgumentException.class, () -> c1.setPaiement("37773230a3291515"));
        assertEquals(c1.getPaiement(), "377732303291515");
    }

    @Test
    public void testContact(){
        c1.setContact("pablo@gmail.com");
        assertThrows(IllegalArgumentException.class, () -> c1.setContact("askldfjh"));
        assertEquals(c1.getContact(), "pablo@gmail.com");
    }

    @Test
    public void testSetNom(){
        c1.setNom("NotPablo");
        assertEquals(c1.getNom(), "NotPablo");
    }

    @Test(groups= {"Contient vol"})
    public void testAddReservation() throws Exception {
        assertEquals(c3.getReservations().size(), 0);
        c3.addReservation(r1);
        assertTrue(c3.getReservations().size() > 0);

        assertThrows(Exception.class, () -> c1.addReservation(r1));
    }


}
