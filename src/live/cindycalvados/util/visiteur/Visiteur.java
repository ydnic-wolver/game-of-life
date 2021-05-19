/**
 * @author CALVADOS Cindy
 */

package live.cindycalvados.util.visiteur;

import live.cindycalvados.cellule.Cellule;
import live.cindycalvados.JeuDeLaVie;


/**
 * Classe abstraite implementant le pattern Visiteur
 */
public abstract class Visiteur {
    /**
     * Reference vers le jeu
     */
    public JeuDeLaVie jeu;

    /**
     * Constructeur jeu
     * @param jeu Jeu
     */
    public Visiteur(JeuDeLaVie jeu) {
        this.jeu = jeu;
    }

    /**
     * Methode de visite pour les cellules vivantes
     * @param cellule Cellule a visiter
     */
    public abstract void visiteCelluleVivante(Cellule cellule);

    /**
     * Methode de visite pour les cellules mortes
     * @param cellule Cellule a visiter
     */
    public abstract void visiteCelluleMorte(Cellule cellule);
}
