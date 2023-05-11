package Tests;

import gestionVol.Aeroport;
import gestionVol.Compagnie;
import gestionVol.Escale;
import gestionVol.Vol;
import org.testng.Assert;
import org.testng.annotations.Test;
import reservation.Client;
import reservation.Passager;
import reservation.Reservation;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class TestVol {
//    @Test
//    public void testObtenirDuree() throws Exception {
//        Vol vol = new Vol("123", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
//        Assert.assertEquals(vol.getDuree(), Duration.ofHours(2));
//    }
//    @Test
//    public void testFermer() throws Exception {
//        Vol vol = new Vol();
//        vol.ouvrir();
//        vol.fermer();
//        Assert.assertFalse(vol.isOuvert());
//    }
//
//    @Test
//    public void testSetReservationsWithoutBidirectional() throws Exception {
//        Vol vol = new Vol();
//        Client cli = new Client("Patrick");
//        Reservation r = new Reservation(cli, vol);
//        HashSet<Reservation> rs = new HashSet<>();
//        vol.setReservationsWithoutBidirectional(rs);
//        Assert.assertEquals(vol.getReservations().size(), 1);
//    }
//
//    @Test
//    public void testSetReservations() throws Exception {
//        Vol vol = new Vol();
//        Client cli = new Client("Patrick");
//        HashSet<Reservation> rs = new HashSet<>();
//        Reservation r = new Reservation(cli, vol);
//        vol.setReservations(rs);
//        Assert.assertEquals(vol.getReservations().size(), 1);
//    }
//
//    @Test
//    public void testAddReservation() throws Exception {
//        Vol vol = new Vol();
//        Client cli = new Client("Patrick");
//        Reservation r = new Reservation(cli, vol);
//        r.confirmer(cli);
//        vol.addReservation(r);
//        Assert.assertEquals(vol.getReservations().size(), 1);
//    }
//
//    @Test
//    public void testSetDateDepart() throws Exception {
//        Vol vol = new Vol("123", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
//        ZonedDateTime dateDepart = ZonedDateTime.now().plusHours(1);
//        vol.setDateDepart(dateDepart);
//        Assert.assertEquals(vol.getDateDepart(), dateDepart);
//    }
//
//    @Test
//    public void testSetDateArrivee() throws Exception {
//        Vol vol = new Vol("123", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
//        ZonedDateTime dateArrivee = ZonedDateTime.now().plusHours(3);
//        vol.setDateArrivee(dateArrivee);
//        Assert.assertEquals(vol.getDateArrivee(), dateArrivee);
//    }
//
//    @Test
//    public void testSetCompagnie() {
//        Vol vol = new Vol();
//        Compagnie compagnie1 = new Compagnie();
//        Compagnie compagnie2 = new Compagnie();
//        vol.setCompagnie(compagnie1);
//        Assert.assertEquals(compagnie1.getVols().size(), 1);
//        Assert.assertEquals(compagnie2.getVols().size(), 0);
//        vol.setCompagnie(compagnie2);
//        Assert.assertEquals(compagnie1.getVols().size(), 0);
//        Assert.assertEquals(compagnie2.getVols().size(), 1);
//    }
//
//    @Test
//    public void testSetCompagnieWithoutBidirectional() {
//        Vol vol = new Vol();
//        Compagnie compagnie = new Compagnie();
//        vol.setCompagnieWithoutBidirectional(compagnie);
//        Assert.assertEquals(vol.getCompagnie(), compagnie);
//    }
//
//    @Test
//    public void testSetNumero() {
//        Vol vol = new Vol();
//        String numero = "123";
//        vol.setNumero(numero);
//        Assert.assertEquals(vol.getNumero(), numero);
//    }
//
//    @Test
//    public void testSetDepart() {
//        Vol vol = new Vol();
//        Aeroport depart = new Aeroport("CDG");
//        vol.setDepart(depart);
//        Assert.assertEquals(vol.getDepart(), depart);
//    }
//    @Test
//    public void testSetArrivee() {
//        Vol vol = new Vol();
//        Aeroport arrivee = new Aeroport("JFK");
//        vol.setArrivee(arrivee);
//        Assert.assertEquals(vol.getArrivee(), arrivee);
//    }
//
//    @Test
//    public void testSetEscales() {
//        Vol vol = new Vol();
//        LinkedHashSet<Escale> escales = new LinkedHashSet<>();
//        escales.add(new Escale());
//        vol.setEscales(escales);
//        Assert.assertEquals(vol.getEscales().size(), 1);
//    }
//
//    @Test
//    public void testAddEscale() throws Exception {
//        Vol vol = new Vol();
//        Escale escale1 = new Escale();
//        Escale escale2 = new Escale();
//        vol.addEscale(escale1);
//        Assert.assertEquals(vol.getEscales().size(), 1);
//        try {
//            vol.addEscale(escale1);
//            Assert.fail("Expected an Exception to be thrown");
//        } catch (Exception e) {
//            Assert.assertEquals(e.getMessage(), "Cette escale fait deja partie du vol.");
//        }
//        vol.addEscale(escale2);
//        Assert.assertEquals(vol.getEscales().size(), 2);
//    }
//
//    @Test
//    public void testGetReservations() throws Exception {
//        Vol vol = new Vol();
//        HashSet<Reservation> rs = new HashSet<>();
//        rs.add(new Reservation(new Client("Michel"), vol));
//        vol.setReservations(rs);
//        Assert.assertEquals(vol.getReservations().size(), 1);
//    }
//
//    @Test
//    public void testGetDuree() throws Exception {
//        Vol vol = new Vol("123", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
//        Assert.assertEquals(vol.getDuree().toHours(), 2);
//    }
//
//    @Test
//    public void testGetDateDepart() throws Exception {
//        Vol vol = new Vol("123", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
//        Assert.assertEquals(vol.getDateDepart(), ZonedDateTime.now());
//    }
//
//    @Test
//    public void testGetDateArrivee() throws Exception {
//        Vol vol = new Vol("123", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
//        Assert.assertEquals(vol.getDateArrivee(), ZonedDateTime.now().plusHours(2));
//    }
//    @Test
//    public void testGetCompagnie() {
//        Vol vol = new Vol();
//        Compagnie compagnie = new Compagnie();
//        vol.setCompagnie(compagnie);
//        Assert.assertEquals(vol.getCompagnie(), compagnie);
//    }
//
//    @Test
//    public void testGetNumero() {
//        Vol vol = new Vol();
//        String numero = "123";
//        vol.setNumero(numero);
//        Assert.assertEquals(vol.getNumero(), numero);
//    }
//
//    @Test
//    public void testGetDepart() {
//        Vol vol = new Vol();
//        Aeroport depart = new Aeroport("CDG");
//        vol.setDepart(depart);
//        Assert.assertEquals(vol.getDepart(), depart);
//    }
//
//    @Test
//    public void testGetArrivee() {
//        Vol vol = new Vol();
//        Aeroport arrivee = new Aeroport("JFK");
//        vol.setArrivee(arrivee);
//        Assert.assertEquals(vol.getArrivee(), arrivee);
//    }
//
//    @Test
//    public void testGetEscales() {
//        Vol vol = new Vol();
//        LinkedHashSet<Escale> escales = new LinkedHashSet<>();
//        escales.add(new Escale());
//        vol.setEscales(escales);
//        Assert.assertEquals(vol.getEscales().size(), 1);
//    }
//
//    @Test
//    public void testEquals() {
//        Vol vol1 = new Vol();
//        vol1.setNumero("123");
//        Vol vol2 = new Vol();
//        vol2.setNumero("123");
//        Assert.assertTrue(vol1.equals(vol2));
//    }
}