/**
 * @author CALVADOS Cindy
 */

package live.cindycalvados.util.commande;

import live.cindycalvados.cellule.Cellule;

/**
 * Classe concrete heritant de la classe Abstraite Commande
 * CommandeVit demande à une cellule de vivre
 */
public class CommandeVit extends Commande {

    /**
     * Constructeur
     * @param c Cellule a modifier
     */
    public CommandeVit(Cellule c) {
        super(c);
    }

    /**
     * Methode demandant a la cellule de vivre
     */
    @Override
    public void executer() {
        this.cellule.vit();
    }

}

