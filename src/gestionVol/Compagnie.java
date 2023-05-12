package gestionVol;

import java.util.ArrayList;
import java.util.Collection;

public class Compagnie {

    private String name;
    private Collection<Vol> vols;

    /**
     * Init liste des vols
     */
    public Compagnie() {
        vols = new ArrayList<>();
    }

    /**
     * Getter nom de la compagnie
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter nom de la compagnie
     * @param name String
     * @return this, pour pouvoir faire : Compagnie cm1 = new Compagnie().setName("Compagnie 1")
     */
    public Compagnie setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Getter liste de vols
     * @return liste de vols de l'entreprise
     */
    public Collection<Vol> getVols() {
        return vols;
    }

    /**
     * Compagnie se retire des vols actuels. Remplace la liste des vols. Se met company des vols de la nouvelle liste.
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
     * @param vol a ajouter
     */
    protected void addVolWithoutBidirectional(Vol vol){
        this.vols.add(vol);
    }

    /**
     * Supprime un vol de la liste de vols, sans se retirer comme company de l'objet.
     * @param vol a supprimer
     */
    protected void removeVolWithoutBidirectional(Vol vol){
        this.vols.remove(vol);
    }
}
