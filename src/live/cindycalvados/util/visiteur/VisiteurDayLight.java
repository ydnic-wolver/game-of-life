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
 * Le VisiteurDayLight applique les regles de generation DayLight
 */
public class VisiteurDayLight extends Visiteur{

    /**
     * Constructeur
     * @param jeu Jeu
     */
    public VisiteurDayLight(JeuDeLaVie jeu) {
        super(jeu);
    }

    @Override
    public void visiteCelluleVivante(Cellule cellule) {
        int nbVoisins = cellule.nombreVoisinesVivantes(jeu);

        if ( !(nbVoisins == 3 || nbVoisins == 4 ||  nbVoisins == 6 || nbVoisins == 7 ||  nbVoisins == 8)  ) {
            jeu.ajouteCommande( new CommandeMeurt(cellule) );
        }
    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {
        int nbVoisins = cellule.nombreVoisinesVivantes(jeu);

        if( nbVoisins == 3 || nbVoisins == 6 ||  nbVoisins == 7 ||  nbVoisins == 8  ){
            jeu.ajouteCommande( new CommandeVit(cellule) );
        }

    }

}
