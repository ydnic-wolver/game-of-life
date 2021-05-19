/**
 * @author CALVADOS Cindy
 */

package live.cindycalvados.cellule;

import live.cindycalvados.util.visiteur.Visiteur;


/**
 * Classe concrete de type Singleton implementant l'interface CelluleEtat
 * CelluleEtatVivant modelise l'etat de vie d'une cellule
 */
public class CelluleEtatVivant implements CelluleEtat{

    /**
     * Instance unique de la classe CelluleEtatVivant
     */
    private static CelluleEtatVivant _instance;

    /**
     * Constructeur en privee afin d'empecher toutes  instanciations
     */
    private CelluleEtatVivant() {}

    /**
     * Renvoi une reference sur l'instance unique de la classe CelluleEtatVivant
     * @return  L'instance
     */
    public static CelluleEtat getInstance() {

        if( _instance == null ) {
            _instance = new CelluleEtatVivant();
        }

        return _instance;
    }


    @Override
    public CelluleEtat vit() {
        return this;
    }

    @Override
    public CelluleEtat meurt() {
        return CelluleEtatMort.getInstance();
    }

    @Override
    public boolean estVivante() {
        return true;
    }

    @Override
    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visiteCelluleVivante(cellule);
    }

}

