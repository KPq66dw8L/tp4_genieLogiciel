package reservation;

import gestionVol.Vol;

public class Association_Reservation_Vol {
    private Reservation reservation;
    private Vol vol;

    public Association_Reservation_Vol(Reservation reservation, Vol vol) {
        this.reservation = reservation;
        this.vol = vol;
    }
}