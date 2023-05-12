package Tests;

import gestionVol.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reservation.Client;
import reservation.Passager;
import reservation.Reservation;

import java.time.Duration;
import java.time.ZonedDateTime;

import static org.testng.Assert.*;

public class TestVol {

    /**
     * Pour voir les OUTPUT des @Test et obtenir le lien du rapport HTML des tests:
     * ./gradlew.bat test --info --rerun-tasks
     * Si probleme de cache:
     * ./gradlew.bat cleanTest --info --rerun-tasks
     */

    Client c1;
    Reservation r1;
    Reservation r2;
    Vol v1;
    Compagnie cm1;
    Ville vi1;
    Ville vi2;

    @BeforeMethod
    public void inits() throws Exception {
        c1 = new Client("Pablo");
        c1.setPaiement("4272505668446363");

        v1 = new Vol("AF11", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
        cm1 = new Compagnie().setName("Compagnie 1");
        cm1.addVol(v1);
        Aeroport a11 = new Aeroport("Aéroport départ");
        Aeroport a12 = new Aeroport("Aéroport arrivé");
        vi1 = new Ville("Paris");
        vi2 = new Ville("Brest");
        a11.setVille(vi1);
        a12.setVille(vi2);
        v1.setDepart(a11);
        v1.setArrivee(a12);
        v1.ouvrir();
    }

    @Test
    public void testConstructeur() throws Exception {
        v1 = new Vol("AF11", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
        assertThrows(Exception.class, v1::ouvrir); // Informations du vol imcompletes donc impossible de l'ouvrir

        cm1 = new Compagnie().setName("Compagnie 1");
        cm1.addVol(v1);
        Aeroport a11 = new Aeroport("Aéroport départ");
        Aeroport a12 = new Aeroport("Aéroport arrivé");
        vi1 = new Ville("Paris");
        vi2 = new Ville("Brest");
        a11.setVille(vi1);
        a12.setVille(vi2);
        v1.setDepart(a11);
        assertThrows(Exception.class, v1::ouvrir); // Informations du vol imcompletes donc impossible de l'ouvrir
        v1.setArrivee(a12);
        v1.ouvrir();
    }

    @Test
    public void testAddReservation() throws Exception {
        r2 = new Reservation(c1, v1);
        assertEquals(v1.getReservations().size(), 0);
        r2.confirmer(c1);
        assertEquals(v1, r2.getVol());
        assertTrue(v1.getReservations().size() > 0); // Reservation confirmée, donc passager associé, donc reservation ajoutée dans la liste du Vol
    }

    @Test
    public void testDuree() throws Exception {
        v1 = new Vol("AF11", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
        Duration d = Duration.ofHours(2);
        assertEquals(v1.getDuree(), d);
    }

    @Test
    public void testSetCompagnie(){
        Compagnie cm2 = new Compagnie().setName("Compagnie 2");
        v1.setCompagnie(cm2);
        assertEquals(v1.getCompagnie(), cm2);
        assertTrue(cm2.getVols().contains(v1));
    }

    @Test
    public void testSetCompagnieWithoutBidirectional(){
        Compagnie cm3 = new Compagnie().setName("Compagnie 3");
        v1.setCompagnieWithoutBidirectional(cm3);
        assertEquals(v1.getCompagnie(), cm3);
        assertFalse(cm3.getVols().contains(v1));
    }

    @Test
    public void testEscales() throws Exception {
        Aeroport a = new Aeroport("JFKL");
        Escale e1 = new Escale(a).setDateDepart(ZonedDateTime.now().plusHours(1)).setDateArrivee(ZonedDateTime.now().plusHours(1).plusMinutes(30));
        v1.addEscale(e1);
    }


}