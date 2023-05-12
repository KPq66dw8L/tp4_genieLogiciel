package Tests;

import gestionVol.Aeroport;
import gestionVol.Ville;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestAeroportVille {

    /**
     * Pour voir les OUTPUT des @Test et obtenir le lien du rapport HTML des tests:
     * ./gradlew.bat test --info --rerun-tasks
     * Si probleme de cache:
     * ./gradlew.bat cleanTest --info --rerun-tasks
     */

    @Test
    public void inits() throws Exception {
        Ville vi1 = new Ville("Paris");
        Aeroport a1 = new Aeroport("JFK").setVille(vi1);
        assertTrue(vi1.getAeroports().contains(a1)); // Bidirectionnel

        Ville vi2 = new Ville("Brest");
        Aeroport a2 = new Aeroport("JFK2").setVilleWithoutBidirectional(vi2);
        assertFalse(vi2.getAeroports().contains(a2)); // Bidirectionnel

        vi1.ajouterAeroport(a2);
        assertEquals(vi1.getAeroports().size(), 2);
        assertTrue(vi1.getAeroports().contains(a2));
        assertEquals(a2.getVille(), vi1);

        Aeroport a3 = new Aeroport("JFK3");
        a3.ajouterVilleDesservie(vi1);
        a3.ajouterVilleDesservie(vi2);
        assertEquals(a3.getVilles().size(), 2);
    }
}
