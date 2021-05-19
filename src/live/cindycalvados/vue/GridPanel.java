/**
 * @author CALVADOS Cindy
 */
package live.cindycalvados.vue;

import live.cindycalvados.controller.GameController;
import live.cindycalvados.JeuDeLaVie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe IHM responsable de la creation de l'affichage de la grille
 */
public class GridPanel extends JPanel {

    /**
     * Hauteur par defaut d'une cellule static
     */
    private static final int HAUTEUR_CELL = 12;

    /**
     * Largeur par defaut d'une cellule static
     */
    private static final int LARGEUR_CELL = 12;

    /**
     * Taille par defaut d'une bordure
     */
    private static final int TAILLE_BORDURE =10;

    /**
     * Hauteur par defaut d'une cellule
     */
    private int hauteurCell = HAUTEUR_CELL;

    /**
     * Largeur par defaut d'une cellule
     */
    private int largeurCell = LARGEUR_CELL;

    /**
     * Lien vers le GameController
     */
    private GameController controller;

    /**
     * Association avec le JeuDeLaVie
     */
    private JeuDeLaVie plateau;

    /**
     * Constructeur
     * @param controller Reference vers le controlleur
     */
    public GridPanel(GameController controller) {
        this.controller = controller;
        creationsEvenements();
    }

    /**
     * Methode responsable de la creations des evenements à
     * traiter avec la souris
     */
    private void creationsEvenements() {
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint();
                if (estDansGrille(p)) {
                    toggle(p);
                }
            }
        };
        // Ajout de l'ecouteur sur le bouton de souris presse/clique
        addMouseListener(ml);
    }

    /**
     * Dessine le composant
     * @param g Objet graphique
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = TAILLE_BORDURE;
        for (int currentRow = 0; currentRow < plateau.getLignes(); currentRow++) {
            int x = TAILLE_BORDURE;
            for (int currentCol = 0; currentCol < plateau.getColonnes(); currentCol++) {
                dessineCell(g, y, currentRow, x, currentCol);
                x += largeurCell;
            }
            y += hauteurCell;
        }
    }

    /**
     * Dessine une cellule
     * @param g Affichage de la cellule
     * @param y Position en x
     * @param currentRow Position courant sur la ligne
     * @param x Position en y
     * @param currentCol Position courant sur la colonne
     */
    private void dessineCell(Graphics g, int y, int currentRow, int x, int currentCol) {
        g.drawRect(x, y, largeurCell, hauteurCell);
        // Si la cellule est vivante on la remplie dans la grille
        if ( plateau.getGrilleXY(currentRow, currentCol).estVivante() ) {
            g.fillRect(x, y, largeurCell, hauteurCell);
        }
    }

    /**
     * Associe le jeu avec l'ihml
     * @param plateau Jeu
     */
    public void setModel(JeuDeLaVie plateau) {
        this.plateau = plateau;
    }

    /**
     * Recupere la position de la cellule clique, et
     * delegue la modification de son etat au controller
     * @param p Point aka cellule
     */
    private void toggle(Point p) {
        int ligne = getLigneClique(p);
        int col = getColonneClique(p);
        controller.toggle(ligne, col);
    }

    /**
     * Renvoi la ligne clique
     * @param p Point
     * @return Retourne le nombre de la ligne
     */
    private int getLigneClique(Point p) {
        return (int) ((p.y - TAILLE_BORDURE) / hauteurCell);
    }

    /**
     * Renvoi la colonne clique
     * @param p
     * @return Retourne le numero de colonne
     */
    private int getColonneClique(Point p) {
        return (int) ((p.x - TAILLE_BORDURE) / largeurCell);

    }

    /**
     * Verifie si la cellule clique se situe bien dans la grille
     * @param p
     * @return Vrai si la cellule se trouve dans les limites de la grille, false sinon
     */
    private boolean estDansGrille(Point p) {
        Rectangle r = new Rectangle(TAILLE_BORDURE, TAILLE_BORDURE, getHauteurGrille(), getLargeurGrille());
        return r.contains(p);
    }

    /**
     * @return Renvoi la hauteur du composant
     */
    public int getHauteurComposant() {
        return getHauteurGrille() + 2 * TAILLE_BORDURE;
    }

    /**
     * @return Renvoi la largeur du composant
     */
    public int getLargeurComposant() {
        return getLargeurGrille() + 2 * TAILLE_BORDURE;
    }

    /**
     * @return Renvoi la hauteur de la grille
     */
    private int getHauteurGrille() {
        return plateau.getColonnes() * HAUTEUR_CELL;
    }

    /**
     * @return Renvoi la largeur de la grille
     */
    private int getLargeurGrille() {
        return plateau.getLignes() * LARGEUR_CELL;
    }
}
