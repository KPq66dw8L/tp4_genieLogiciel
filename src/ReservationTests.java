import gestionVol.Compagnie;
import gestionVol.Vol;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import reservation.Client;
import reservation.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.hamcrest.core.IsIterableContaining.hasItems;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.hamcrest.core.StringStartsWith.startsWith;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTests {

    // Les objets communs à tous les tests
    private Client client;
    private Vol vol;
    private Reservation reservation;

    // La méthode qui initialise les objets avant chaque test
    @BeforeEach
    void setUp() throws Exception {
        client = new Client("Pablo Escobar");
        client.setContact("pablo@gmail.com");
        client.setPaiement("4272505668446363");
        vol = new Vol("AF123", ZonedDateTime.parse("2020-10-21T13:00:00+02:00"), ZonedDateTime.parse("2020-10-23T02:15:00+02:00"));
        reservation = new Reservation(client, vol);
    }

    // Le test qui vérifie que la réservation est payée quand elle est créée
    @Test
    void testReservationIsPayedWhenCreated() {
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
        assertNull(reservation.getClientR());
        assertNull(reservation.getUniqueID());
        assertNull(reservation.getDate());
        assertNull(reservation.getVol());
        assertNull(reservation.getPassager());
    }

    // Le test qui vérifie que la réservation ne peut pas être annulée par un autre client
    @Test
    void testReservationCannotBeCanceledByWrongClient() {
        Client otherClient = new Client("Axel Exposito");
        otherClient.setContact("axel@gmail.com");
        otherClient.setPaiement("377732303291515");
        assertThrows(IllegalArgumentException.class, () -> reservation.annuler());
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
        assertNull(reservation.getClientR());
        assertNull(reservation.getUniqueID());
        assertNull(reservation.getDate());
        assertNull(reservation.getVol());
        assertNull(reservation.getPassager());
    }

    // Le test qui vérifie que la réservation n'est pas remboursée quand elle est annulée après avoir été confirmée
    @Test
    void testReservationIsNotRefundedWhenCanceledAfterConfirmed() {
        reservation.confirmer(client);
        reservation.annuler();
        assertTrue(reservation.isPayed());
        assertNull(reservation.getClientR());
        assertNull(reservation.getUniqueID());
        assertNull(reservation.getDate());
        assertNull(reservation.getVol());
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