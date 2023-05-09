import gestionVol.Compagnie;
import gestionVol.Vol;
import reservation.Client;
import reservation.Reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Start {

    public static void main(String[] args) throws Exception {
        Vol volFinal = new Vol();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
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

        // exemple VISA:
        Client c1 = new Client("Pablo Escobar");
        c1.setContact("pablo@gmail.com");
        // Testing purpose: https://fossbytes.com/tools/credit-card-generator
        c1.setPaiement("4272505668446363");
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
}
