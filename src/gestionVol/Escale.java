package gestionVol;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Escale {
    private Aeroport aeroport;
    private Date dateDepart;
    private Date dateArrivee;

    /**
     * Durée de l'escale.
     * @return duree
     */
    public Duration obtenirDuree() {
        if(this.dateDepart != null && this.dateArrivee != null) {
            return Duration.of(dateArrivee.getTime() - dateDepart.getTime(), ChronoUnit.MILLIS);
        }
        return null;
    }
}
