package Tests;

import gestionVol.Compagnie;
import gestionVol.Vol;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import reservation.Client;
import reservation.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

public class MyTests {

    /**
     * Pour voir les OUTPUT des @Test:
     * ./gradlew.bat test --info --rerun-tasks
     */

    String dd = "21/10/2020 13:00";
    String da = "23/10/2020 02:15";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    ZoneId zoneId = ZoneId.of("Europe/Paris");

//    @Test
//    public void testClient() throws Exception {
//
//        Client c1 = new Client("Pablo Escobar");
//        c1.setContact("pablo@gmail.com");
//        // Testing purpose only: https://fossbytes.com/tools/credit-card-generator
//        // exemple VISA:
//        c1.setPaiement("4272505668446363");
//
//        assertThat(c1.getPaiement(), is("4272505668446363"));
//        assertThat(c1.getContact(), is("pablo@gmail.com"));
//
//        Vol v1 = new Vol("AF123", ZonedDateTime.of(LocalDateTime.parse(dd, formatter), zoneId), ZonedDateTime.of(LocalDateTime.parse(da, formatter), zoneId));
//        Reservation r1 = new Reservation(c1, v1);
//        // Utilisation de assertThat avec sameInstance pour vérifier l'identité
//        assertThat(r1.getClient(), sameInstance(c1.getReference()));
//        // Utilisation de assertThat avec hasProperty pour vérifier une propriété d'un objet
//        assertThat(c1, hasProperty("nom", is("Pablo Escobar")));
//
//        //exemple MASTERCARD:
//        Client c2 = new Client("Julien Herbaux");
//        c2.setContact("julien@gmail.com");
//        c2.setPaiement("2483571672956135");
//        Reservation r2 = new Reservation(c2, v1);
//
//        //exemple AMEX:
//        Client c3 = new Client("Axel Exposito");
//        c3.setContact("axel@gmail.com");
//        c3.setPaiement("377732303291515");
//        Reservation r3 = new Reservation(c3, v1);
//    }
//
//    @Test
//    public void testReservation() throws Exception {
//
//        String dd = "21/10/2020 13:00";
//        String da = "23/10/2020 02:15";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//
//        Client c1 = new Client("Pablo Escobar");
//        c1.setContact("pablo@gmail.com");
//        // Testing purpose: https://fossbytes.com/tools/credit-card-generator
//        c1.setPaiement("4272505668446363");
//
//        Vol v1 = new Vol("AF123", ZonedDateTime.of(LocalDateTime.parse(dd, formatter), zoneId), ZonedDateTime.of(LocalDateTime.parse(da, formatter), zoneId));
//
//        Reservation r1 = new Reservation(c1, v1);
//        // Utilisation de assertThat avec sameInstance pour vérifier l'identité
//        assertThat(r1.getClient(), sameInstance(c1.getReference()));
//    }
//
//    @Test
//    public void testPassager(){
//
//    }
//
//    @Test
//    public void testVol() throws ParseException {
//        Vol volFinal = new Vol();
//
//        String dd = "21/10/2020 13:00";
//        String da = "23/10/2020 02:15";
//
//        try {
//            volFinal.setDateDepart(ZonedDateTime.of(LocalDateTime.parse(dd, formatter), zoneId));
//            volFinal.setDateArrivee(ZonedDateTime.of(LocalDateTime.parse(da, formatter), zoneId));
//        } catch (Exception e){
//            throw new RuntimeException("Unable to format to date");
//        }
//
//        // Utilisation de assertThat avec is pour vérifier l'égalité
//        assertThat(volFinal.getDateArrivee(), is(ZonedDateTime.of(LocalDateTime.parse(da, formatter), zoneId)));
//        // Utilisation de assertThat avec startsWith pour vérifier le début d'une chaîne
//        assertThat(volFinal.getDuree().toString(), startsWith("PT"));
//    }
//
//    @Test
//    public void testCompagnie(){
//        //Bidirectional
//        Vol vol = new Vol();
//        vol.setNumero("abc1");
//
//        Vol vol2 = new Vol();
//        vol2.setNumero("abc2");
//
//        Compagnie compagnie = new Compagnie();
//        compagnie.setName("Air France");
//        compagnie.addVol(vol);
//        compagnie.addVol(vol2);
//
//        // Utilisation de assertThat avec hasItems pour vérifier que la compagnie contient les vols
//        assertThat(compagnie.getVols(), hasItems(vol, vol2));
//        // Utilisation de assertThat avec equalTo pour vérifier que les vols ont le bon numéro
//        assertThat(vol.getNumero(), equalTo("abc1"));
//        assertThat(vol2.getNumero(), equalTo("abc2"));
//
//        // Utilisation de assertThat avec sameInstance pour vérifier que les vols ont la même compagnie
//        assertThat(vol.getCompagnie(), sameInstance(compagnie));
//        assertThat(vol2.getCompagnie(), sameInstance(compagnie));
//        // Utilisation de assertThat avec hasProperty pour vérifier que la compagnie a le bon nom
//        assertThat(compagnie, hasProperty("name", is("Air France")));
//
//        vol2.setCompagnie(null);
//        // Utilisation de assertThat avec nullValue pour vérifier que le vol n'a plus de compagnie
//        assertThat(vol2.getCompagnie(), nullValue());
//
//        // Utilisation de assertThat avec not pour vérifier que la compagnie ne contient plus le vol
//        assertThat(compagnie.getVols(), not(hasItem(vol2)));
//    }

    //Help you to handle exception. :-)
    public static Throwable exceptionOf(Callable<?> callable) {
        try {
            callable.call();
            return null;
        } catch (Throwable t) {
            System.out.println(t);
            return t;
        }
    }
}
