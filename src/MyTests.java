

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

    @Test
    public void testClient() throws Exception {
        System.out.println("Test launched.");

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String dd = "21/10/2020 13:00";
        String da = "23/10/2020 02:15";
        Client c1 = new Client("Pablo Escobar");
        c1.setContact("pablo@gmail.com");
        // Testing purpose: https://fossbytes.com/tools/credit-card-generator
        c1.setPaiement("4272505668446363");
        Vol v1 = new Vol("AF123", format.parse(dd), format.parse(da));
        Reservation r1 = new Reservation(c1.getReference(), v1);
        if (r1.getClient() == c1.getReference()) {
            System.out.println(c1.getNom());
        }
        assertThat(c1.getPaiement(), is("4272505668446363aa"));
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
