/**
 * @author CALVADOS Cindy
 */

package live.cindycalvados.util.observateurs;

import live.cindycalvados.JeuDeLaVie;


/**
 * Classe ObservateurConsole implementant l'interface Observateur
 * Elle est responsable de l'observation des modifications du plateau
 * et de l'affichage dans la console texte du nombre de generations dejà realisees;
 * ainsi que l'affichage des cellules vivantes restantes.
 */
public class ObservateurConsole implements Observateur {

    /**
     * Reference vers la classe JeuDeLaVie
     */
    private JeuDeLaVie jeu;

    /**
     * Creation de l'objet ObservateurConsole
     * @param jeu  Reference vers le jeu
     */
    public ObservateurConsole(JeuDeLaVie jeu) {
        this.jeu = jeu;
        this.jeu.attacheObservateur(this);
    }

    @Override
    public void actualise() {
        System.out.println("Generation = " + jeu.getNumGeneration() + " ,Cellules vivantes = " + jeu.getCellulesVivantes());
    }
}
