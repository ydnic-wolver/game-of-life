/**
 * @author CALVADOS Cindy
 */


package live.cindycalvados.util.commande;

import live.cindycalvados.cellule.Cellule;

/**
 * Classe implementant le design pattern Commande
 */
public abstract class Commande {

    /**
     * Reference vers une Cellule devant subir la modification
     */
    protected Cellule cellule;

    /**
     * Constructeur
     * @param cell Cellule a modifier
     */
    public Commande(Cellule cell) {
        this.cellule = cell;
    }

    /**
     * Commande à executer
     */
    public abstract void executer();
}
