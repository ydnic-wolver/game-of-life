/**
 * @author CALVADOS Cindy
 */
package live.cindycalvados.util.visiteur;

import live.cindycalvados.cellule.Cellule;
import live.cindycalvados.JeuDeLaVie;
import live.cindycalvados.util.commande.CommandeMeurt;
import live.cindycalvados.util.commande.CommandeVit;


/**
 * Classe concrete implementant le pattern Visiteur
 * Le visiteur classique corresponds aux regles de base du jeu de la vie
 */
public class VisiteurClassique extends Visiteur{

    /**
     * Constructeur
     * @param jeu Jeu
     */
    public VisiteurClassique(JeuDeLaVie jeu) {
        super(jeu);
    }

    @Override
    public void visiteCelluleVivante(Cellule cellule) {
        int nbVoisins = cellule.nombreVoisinesVivantes(jeu);

        if ( nbVoisins < 2 || nbVoisins  > 3) {
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {

        if(cellule.nombreVoisinesVivantes(jeu) == 3){
            jeu.ajouteCommande(new CommandeVit(cellule));
        }

    }

}
