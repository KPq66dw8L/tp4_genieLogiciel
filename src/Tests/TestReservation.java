package Tests;

import gestionVol.Aeroport;
import gestionVol.Compagnie;
import gestionVol.Ville;
import gestionVol.Vol;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reservation.Client;
import reservation.Reservation;
import java.time.ZonedDateTime;

import static org.testng.Assert.*;


public class TestReservation {

    /**
     * Pour voir les OUTPUT des @Test et obtenir le lien du rapport HTML des tests:
     * ./gradlew.bat test --info --rerun-tasks
     * Si probleme de cache:
     * ./gradlew.bat cleanTest --info --rerun-tasks
     */

    Client c1;
    Client c2;
    Client c3;
    Reservation r1;
    Reservation r2;
    Reservation r3;
    Vol v1;
    Vol v2;

    @BeforeMethod
    public void inits() throws Exception {
        c1 = new Client("Pablo");
        c2 = new Client("Pablo - pas de moyen de paiement");
        c3 = new Client("Pablo");
        Compagnie cm1 = new Compagnie().setName("Compagnie 1");
        v1 = new Vol("AF11 - vol ouvert", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
        v2 = new Vol("AF12 - vol fermé", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
        cm1.addVol(v1);
        cm1.addVol(v2);
        Aeroport a11 = new Aeroport("Aéroport départ");
        Aeroport a12 = new Aeroport("Aéroport arrivé");
        Ville vi1 = new Ville("Paris");
        Ville vi2 = new Ville("Brest");
        a11.setVille(vi1);
        a12.setVille(vi2);
        v1.setDepart(a11);
        v1.setArrivee(a12);
        v2.setDepart(a11);
        v2.setArrivee(a12);
        v1.ouvrir();
        c1.setPaiement("4272505668446363");
        c3.setPaiement("4272505668446363");
    }

    @Test
    public void testReservations() throws Exception {
        r1 = new Reservation(c1, v1);
        assertEquals(r1.getClient(), c1.getReference());

        assertThrows(Exception.class, () -> r2 = new Reservation(c1, v2)); // Vol non ouvert, donc impossible de réserver
        assertThrows(Exception.class, () -> r2 = new Reservation(c2, v1)); // Impossible de débiter le client, donc impossible de réserver
    }

    @Test
    public void testConfirmer() throws Exception {
        r1 = new Reservation(c1, v1);
        assertThrows(IllegalArgumentException.class, () -> r1.confirmer(c2)); // Mauvais client, empêche de vérifier que les informations client sont toujours completes
        v2.ouvrir();
        r1 = new Reservation(c1, v2); // On peut créer la reservation car le vol est mtn ouvert
        v2.fermer();
        assertThrows(Exception.class, () -> r1.confirmer(c2)); // Impossible de confirmer, le vol est fermé

        r1 = new Reservation(c1, v1);
        assertNull(r1.getPassager()); // Pas encore assigné comme passager car non confirmé
        r1.confirmer(c1);
        assertNotNull(r1.getPassager()); // Passager après confirmation
    }

    @Test
    public void testAnnuler() throws Exception {
        r1 = new Reservation(c1, v1);
        assertTrue(r1.isPayed());
        r1.annuler();
        assertFalse(r1.isValide());
        assertFalse(r1.isPayed()); // réservation remboursée car annulée avant confirmation

        r3 = new Reservation(c3, v1);
        r3.confirmer(c3);
        assertTrue(r3.isConfirmed());
        assertTrue(r3.isPayed());
        r3.annuler();
        assertTrue(r3.isPayed());
        assertFalse(r3.isValide());
    }

}