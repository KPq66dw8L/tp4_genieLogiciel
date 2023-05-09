package Tests;

import gestionVol.Vol;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reservation.Client;
import reservation.Reservation;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestReservation {

    // Les objets communs à tous les tests
    private Client client;
    private Vol vol;
    private Reservation reservation;

    // La méthode qui initialise les objets avant chaque test
    @BeforeMethod
    void setUp() throws Exception {
        client = new Client("Pablo Escobar");
        client.setContact("pablo@gmail.com");
        client.setPaiement("4272505668446363");
        vol = new Vol("AF123", ZonedDateTime.parse("2020-10-21T13:00:00+02:00"), ZonedDateTime.parse("2020-10-23T02:15:00+02:00"));
        reservation = new Reservation(client, vol);
    }

    // Le test qui vérifie que la réservation est payée quand elle est créée
    @Test
    void testReservationIsPayedWhenCreated() throws Exception {
        assertTrue(reservation.isPayed());
    }

    // Le test qui vérifie que la réservation n'est pas confirmée quand elle est créée
    @Test
    void testReservationIsNotConfirmedWhenCreated() {
        assertFalse(reservation.isConfirmed());
    }

    // Le test qui vérifie que la réservation peut être confirmée par le bon client
    @Test
    void testReservationCanBeConfirmedByRightClient() {
        reservation.confirmer(client);
        assertTrue(reservation.isConfirmed());
        assertNotNull(reservation.getPassager());
        assertEquals(client.getNom(), reservation.getPassager().getNom());
    }

    // Le test qui vérifie que la réservation ne peut pas être confirmée par un autre client
    @Test
    void testReservationCannotBeConfirmedByWrongClient() {
        Client otherClient = new Client("Julien Herbaux");
        otherClient.setContact("julien@gmail.com");
        otherClient.setPaiement("2483571672956135");
        assertThrows(IllegalArgumentException.class, () -> reservation.confirmer(otherClient));
        assertFalse(reservation.isConfirmed());
        assertNull(reservation.getPassager());
    }
    // Le test qui vérifie que la réservation peut être annulée par le bon client
    @Test
    void testReservationCanBeCanceledByRightClient() {
        reservation.confirmer(client);
        reservation.annuler();
        assertFalse(reservation.isConfirmed());
        assertNull(reservation.getPassager());
    }

    // Le test qui vérifie que la réservation ne peut pas être annulée par un autre client
    @Test
    void testReservationCannotBeCanceledByWrongClient() throws Exception {
        Client otherClient = new Client("Axel Exposito");
        otherClient.setContact("axel@gmail.com");
        otherClient.setPaiement("377732303291515");
        assertThrows(Exception.class, () -> otherClient.addReservation(reservation));
        assertTrue(reservation.isPayed());
        assertNotNull(reservation.getClientR());
        assertNotNull(reservation.getUniqueID());
        assertNotNull(reservation.getDate());
        assertNotNull(reservation.getVol());
        assertNull(reservation.getPassager());
    }

    // Le test qui vérifie que la réservation est remboursée quand elle est annulée avant d'être confirmée
    @Test
    void testReservationIsRefundedWhenCanceledBeforeConfirmed() {
        reservation.annuler();
        assertFalse(reservation.isPayed());
        assertNull(reservation.getPassager());
    }

    // Le test qui vérifie que la réservation n'est pas remboursée quand elle est annulée après avoir été confirmée
    @Test
    void testReservationIsNotRefundedWhenCanceledAfterConfirmed() {
        reservation.confirmer(client);
        reservation.annuler();
        assertTrue(reservation.isPayed());
        assertNull(reservation.getPassager());
    }

    // Le test qui vérifie que la réservation renvoie bien la référence du client
    @Test
    void testReservationReturnsClientReference() {
        assertEquals(client.getReference(), reservation.getClient());
    }

    // Le test qui vérifie que la réservation renvoie bien le vol associé
    @Test
    void testReservationReturnsVol() {
        assertEquals(vol, reservation.getVol());
    }
}