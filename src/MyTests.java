

import gestionVol.Compagnie;
import gestionVol.Vol;
import org.testng.annotations.Test;
import reservation.Client;
import reservation.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class MyTests {

    /**
     * Pour voir les OUTPUT des @Test:
     * ./gradlew.bat test --info --rerun-tasks
     */

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    @Test
    public void testClient() throws Exception {


        String dd = "21/10/2020 13:00";
        String da = "23/10/2020 02:15";

        Client c1 = new Client("Pablo Escobar");
        c1.setContact("pablo@gmail.com");
        // Testing purpose only: https://fossbytes.com/tools/credit-card-generator
        // exemple VISA:
        c1.setPaiement("4272505668446363");

        assertThat(c1.getPaiement(), is("4272505668446363"));
        assertThat(c1.getContact(), is("pablo@gmail.com"));

        Vol v1 = new Vol("AF123", format.parse(dd), format.parse(da));
        Reservation r1 = new Reservation(c1, v1);
        if (r1.getClient() == c1.getReference()) {
            System.out.println(c1.getNom());
        }

        //exemple MASTERCARD:
        Client c2 = new Client("Julien Herbaux");
        c2.setContact("julien@gmail.com");
        c2.setPaiement("2483571672956135");
        Reservation r2 = new Reservation(c2, v1);

        //exemple AMEX:
        Client c3 = new Client("Axel Exposito");
        c3.setContact("axel@gmail.com");
        c3.setPaiement("377732303291515");
        Reservation r3 = new Reservation(c3, v1);
    }

    @Test
    public void testReservation() throws Exception {

        String dd = "21/10/2020 13:00";
        String da = "23/10/2020 02:15";

        Client c1 = new Client("Pablo Escobar");
        c1.setContact("pablo@gmail.com");
        // Testing purpose: https://fossbytes.com/tools/credit-card-generator
        c1.setPaiement("4272505668446363");

        Vol v1 = new Vol("AF123", format.parse(dd), format.parse(da));

        Reservation r1 = new Reservation(c1, v1);
        if (r1.getClient() == c1.getReference()) {
            System.out.println(c1.getNom());
        }
    }

    @Test
    public void testPassager(){

    }

    @Test
    public void testVol(){
        Vol volFinal = new Vol();

        String dd = "21/10/2020 13:00";
        String da = "23/10/2020 02:15";

        try {
            volFinal.setDateDepart(format.parse(dd));
            volFinal.setDateArrivee(format.parse(da));
        } catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }

        System.out.println(volFinal.getDateArrivee());
        System.out.println(volFinal.getDuree().toString().substring(2));
    }

    @Test
    public void testCompagnie(){
        //Bidirectional
        Vol vol = new Vol();
        vol.setNumero("abc1");

        Vol vol2 = new Vol();
        vol2.setNumero("abc2");

        Compagnie compagnie = new Compagnie();
        compagnie.setName("Air France");
        compagnie.addVol(vol);
        compagnie.addVol(vol2);

        for(Vol v : compagnie.getVols()){
            System.out.println(v.getNumero());
        }

        System.out.println(vol.getCompagnie().getName());
        System.out.println(vol2.getCompagnie().getName());

        vol2.setCompagnie(null);
        System.out.println(vol2.getCompagnie());

        for(Vol v : compagnie.getVols()){
            System.out.println(v.getNumero());
        }
    }

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
