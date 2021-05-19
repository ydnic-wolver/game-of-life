/**
 * @author CALVADOS Cindy
 */


package live.cindycalvados.cellule;

import live.cindycalvados.util.visiteur.Visiteur;

/**
 * Interface implementant le pattern Etat
 * et responsable de la gestion de l'etat d'une Cellule
 */
public interface CelluleEtat {

    /**
     * Methode qui change l'etat d'une cellule a vivante
     * @return Cellule
     */
    CelluleEtat vit();

    /**
     * Methode qui change l'etat d'une cellule a mort
     * @return Cellule
     */
    CelluleEtat meurt();

    /**
     * Verifie si une cellule est vivante
     * @return Renvoi true si la cellule est vivante sinon false
     */
    boolean estVivante();

    /**
     * Methode acceptant un visiteur
     * @param visiteur  Visiteur à accepter
     * @param cellule Cellule a visiter
     */
    void  accepte(Visiteur visiteur, Cellule cellule);
}
