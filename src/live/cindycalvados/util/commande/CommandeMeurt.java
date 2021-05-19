/**
 * @author CALVADOS Cindy
 */

package live.cindycalvados.util.commande;

import live.cindycalvados.cellule.Cellule;

/**
 * Classe concrete heritant de la classe Abstraite Commande
 * CommandeMeurt demande à une cellule de mourir
 */
public class CommandeMeurt extends Commande {

    /**
     * Constructeur
     * @param c Cellule a modifier
     */
    public CommandeMeurt(Cellule c) {
        super(c);
    }

    /**
     * Methode demandant a la cellule de mourir
     */
    @Override
    public void executer() {
        this.cellule.meurt();
    }

}
