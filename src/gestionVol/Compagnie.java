package gestionVol;

import java.util.ArrayList;
import java.util.Collection;

public class Compagnie {

    private String name;
    private Collection<Vol> vols = new ArrayList<>();

    /**
     * Constructeur
     */
    public Compagnie() {
    }

    /**
     * Simple function
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Simple function
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Simple function
     * @return liste de vols de l'entreprise
     */
    public Collection<Vol> getVols() {
        return vols;
    }

    /**
     * Company se retire des vols actuels. Remplace la liste des vols. Se met company des vols de la nouvelle liste.
     * @param vols
     */
    public void setVols(Collection<Vol> vols) {
        for(Vol v : this.vols){
            v.setCompagnieWithoutBidirectional(null);
        }

        this.vols = vols;

        if(this.vols != null) {
            for (Vol v : this.vols) {
                v.setCompagnieWithoutBidirectional(this);
            }
        }
    }

    /**
     * Company s'associe au vol, puis l'ajoute a sa liste de vols.
     * @param vol
     */
    public void addVol(Vol vol){
        vol.setCompagnieWithoutBidirectional(this);
        this.vols.add(vol);
    }

    /**
     * Se désassocie du vol, puis le supprime de sa liste de vols.
     * @param vol
     */
    public void removeVol(Vol vol){
        vol.setCompagnieWithoutBidirectional(null);
        this.vols.remove(vol);
    }

    /**
     * Remplace la liste de vols actuelle par nouvelle liste, sans retirer la company des vols de l'ancienne liste et sans s'associer aux nouveaux vols.
     * @param vols
     */
    protected void setVolsWithoutBidirectional(Collection<Vol> vols) {
        this.vols = vols;
    }

    /**
     * Ajoute un vol à la liste des vols, sans se mettre company de ce vol dans l'objet.
     * @param vol
     */
    protected void addVolWithoutBidirectional(Vol vol){
        this.vols.add(vol);
    }

    /**
     * Supprime un vol de la liste de vols, sans se retirer comme company de l'objet.
     * @param vol
     */
    protected void removeVolWithoutBidirectional(Vol vol){
        this.vols.remove(vol);
    }
}