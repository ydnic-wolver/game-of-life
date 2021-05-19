/**
 * @author CALVADOS Cindy
 */


package live.cindycalvados.cellule;

import live.cindycalvados.util.visiteur.Visiteur;


/**
 * Classe concrete de type Singleton implementant l'interface CelluleEtat
 * CelluleEtatMort modelise l'etat de mort d'une cellule
 */
public class CelluleEtatMort implements CelluleEtat {

    /**
     * Instance unique de la classe CelluleEtatMort
     */
    private static CelluleEtatMort _instance;

    /**
     * Constructeur en privee afin d'empecher toutes  instanciations
     */
    private CelluleEtatMort() {}

    /**
     * Renvoi une reference sur l'instance unique de la classe CelluleEtatMort
     * @return Retourne l'instance de CelluleEtatMort
     */
    public static CelluleEtat getInstance() {
        // Si l'instance n'est pas encore creee on l'instancie une seule fois
        if( _instance == null ) {
            _instance = new CelluleEtatMort();
        }

        return _instance;
    }

    @Override
    public CelluleEtat vit() {
        return CelluleEtatVivant.getInstance();
    }

    @Override
    public CelluleEtat meurt() {
        return this;
    }

    @Override
    public boolean estVivante() {
        return false;
    }

    @Override
    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visiteCelluleMorte(cellule);
    }


}
