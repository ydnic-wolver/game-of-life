/**
 * @author CALVADOS Cindy
 */

package live.cindycalvados.cellule;

import live.cindycalvados.JeuDeLaVie;
import live.cindycalvados.util.visiteur.Visiteur;

/**
 * Classe correspondant à une Cellule
 */
public class Cellule  {

    /**
     * Position en x dans la grille
     */
    private final int x;

    /**
     * Position en y dans la grille
     */
    private final int y;

    /**
     * Etat de la cellule ( Mort ou Vivant )
     */
    private CelluleEtat etat;

    /**
     * Constructeur d'un objet de type Cellule
     * @param x Position en x
     * @param y Position en y
     * @param etat Etat de la cellule au moment de sa creation
     */
    public Cellule(int x, int y,CelluleEtat etat) {
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    /**
     * Alteration d'une cellule
     * dans l'etat vit()
     * Delegue la tâche à l'objet CelluleEtat
     */
    public void vit() {
        this.etat = etat.vit();
    }

    /**
     * Alteration d'une cellule
     * dans l'etat meurt()
     * Delegue la tâche à l'objet CelluleEtat
     */
    public void meurt() {
        this.etat = etat.meurt();
    }

    /**
     * Verifie si une cellule est vivante
     * @return Renvoi true si la cellule est vivante sinon false
     */
    public boolean estVivante() {
        return etat.estVivante();
    }


    /**
     * Calcule le nombre de voisine vivantes
     * @param g Reference vers le JeuDeLaVie
     * @return Renvoi le nombre de voisines vivantes
     */
    public int nombreVoisinesVivantes(JeuDeLaVie g) {
        int result = 0;
        // Calcule de la borne inferieure
        int borneInferieure = Math.min(x + 1, g.getLignes() - 1);
        // Calcule de la borne de droite
        int borneDroite = Math.min(y + 1, g.getColonnes() - 1);

        for (int i = Math.max(0, x - 1); i <= borneInferieure; i++) {
            for (int j = Math.max(0, y - 1); j <= borneDroite; j++) {
                Cellule voisin = g.getGrilleXY(i, j);
                if(voisin != null) {
                    if (!(i == x && j == y ) && (voisin.estVivante())) {
                        result++;
                    }
                }

            }
        }
        return result;
    }

    /**
     * Methode acceptant un visiteur
     * @param visiteur Visiteur à accepter
     */
    public void accepte(Visiteur visiteur) {
        this.etat.accepte(visiteur, this);
    }

}
