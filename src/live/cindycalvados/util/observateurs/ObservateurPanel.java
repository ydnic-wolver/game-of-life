/**
 * @author CALVADOS Cindy
 */

package live.cindycalvados.util.observateurs;

import live.cindycalvados.JeuDeLaVie;

import javax.swing.*;

/**
 * Classe ObservateurPanel implementant l'interface Observateur
 * Elle est responsable de l'observation du plateau
 * et de l'affichage dans l'ihm du nombre de generations dejà realisees;
 * ainsi que celle des cellules vivantes restantes.
 */
public class ObservateurPanel extends JPanel implements Observateur {
    /**
     * Reference vers la classe JeuDeLaVie
     */
    private JeuDeLaVie jeu;

    /**
     * Label permettant l'affichage des informations dans la fenetre
     */
    private JLabel infos;

    /**
     * Creation d'un objet de type ObservateurPanel
     * @param jeu  Reference vers le jeu
     */
    public ObservateurPanel(JeuDeLaVie jeu) {
        super();
        this.jeu = jeu;
        this.jeu.attacheObservateur(this);

        this.infos = new JLabel();
        this.infos.setText("Generation = 0 ,Cellules vivantes = 0");
        add(this.infos);
    }

    @Override
    public void actualise() {
        // Recupere le numero de la generation et le nombre de cellules vivantes
        this.infos.setText("Generation = " + jeu.getNumGeneration() + " ,Cellules vivantes = " + jeu.getCellulesVivantes());
    }
}
