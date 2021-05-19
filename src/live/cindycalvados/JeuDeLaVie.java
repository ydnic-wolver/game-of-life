/**
 * @author CALVADOS Cindy
 */
package live.cindycalvados;


import live.cindycalvados.controller.GameController;
import live.cindycalvados.cellule.Cellule;
import live.cindycalvados.cellule.CelluleEtatMort;
import live.cindycalvados.cellule.CelluleEtatVivant;
import live.cindycalvados.util.observateurs.ObservateurConsole;
import live.cindycalvados.util.observateurs.Observable;
import live.cindycalvados.util.observateurs.Observateur;
import live.cindycalvados.util.commande.Commande;
import live.cindycalvados.util.visiteur.Visiteur;
import live.cindycalvados.vue.JeuDeLaVieUI;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Classe representant le JeuDeLaVie
 */
public class JeuDeLaVie implements Observable {

    /**
     * Tableau de cellule
     */
    private Cellule[][] cellule;

    /**
     * Visiteur
     */
    private Visiteur visiteur;

    /**
     * Liste des observateurs
     */
    private List<Observateur> list;

    /**
     * Liste des commandes à traiter
     */
    private List<Commande> commandes;

    /**
     * Nombre de lignes
     */
    private int lignes;
    /**
     * Nombre de colonnes
     */
    private int colonnes;

    /**
     * Numero de generation
     */
    private int numGeneration;

    /**
     * Booleen permettant d'activer on non la grille aleatoire
     */
    private boolean aleatoire;

    /**
     * Construction du JeuDeLaVie
     * @param lignes Nombre de lignes
     * @param colonnes Nombre de colonnes
     */
    public JeuDeLaVie(int lignes, int colonnes) {
        // Numero de la generation par defaut elle commence à 1
        this.numGeneration = 1;
        // Par defaut la grille n'est pas aleatoire
        this.aleatoire = false;
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.cellule = new Cellule[lignes][colonnes];
        this.list = new CopyOnWriteArrayList<>();
        this.commandes = new CopyOnWriteArrayList<>();

    }

    /**
     * Initialisation de la grille
     * Si le mode aleatoire est active la grille est rempli de façon aleatoire
     * Sinon elle est initialisee avec des cases mortes par defaut
     */
    public void InitialiseGrille() {
        this.numGeneration = 1;
        Random random = new Random();
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
              if(aleatoire){
                  int num = (int)(Math.random()*2) ;
                  this.cellule[i][j] = new Cellule( i,j, num == 1 ? CelluleEtatVivant.getInstance() : CelluleEtatMort.getInstance());
              }
              else {
                  this.cellule[i][j] = new Cellule( i, j , CelluleEtatMort.getInstance());
              }
            }
        }
    }

    /**
     * @return Renvoi le numero de la generation actuelle
     */
    public int getNumGeneration() {
        return numGeneration;
    }

    /**
     * Affecte un visiteur
     * @param visiteur Nouveau visiteur à affecter
     */
    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    /**
     * Methode modifiant l'attribut aleatoire
     * @param aleatoire Nouvelle etat
     */
    public void setAleatoire(boolean aleatoire) {
        this.aleatoire = aleatoire;
    }

    /**
     * Retourne une cellule à une position donnee
     * @param x Ligne de la cellule
     * @param y Colonne de la cellule
     * @return Retourne la cellule si elle existe, sinon null
     */
    public Cellule getGrilleXY(int x, int y){
        if( (x >= 0) && (y >= 0) && (x < lignes) && (y < colonnes) ){
            return this.cellule[x][y];
        }
        else{
            return null;
        }
    }

    @Override
    public void attacheObservateur(Observateur o) {
        this.list.add(o);
    }

    @Override
    public void detacheObservateur(Observateur o) {
        this.list.remove(o);
    }


    /**
     * Notifie les observateurs d'une actualisation
     */
    @Override
    public void notifieObservateur() {
        for(var obs : list) {
            obs.actualise();
        }
    }

    /**
     * Ajout d'une commande à traiter
     * @param c Commande a traiter
     */
    public void ajouteCommande(Commande c) {
        this.commandes.add(c);
    }

    /**
     *    Modifie l'etat d'une cellule
     *    Si elle est vivante elle meurt ,
     *  si elle est morte elle vit
     *  Cette methode est utilise dans le cadre de l'ajout/suppresion de
     *  cellule avec la souris
     * @param ligne Numero de ligne
     * @param colonne Numero de colonne
     */
    public void toggle(int ligne, int colonne) {
        if( this.cellule[ligne][colonne].estVivante() ){
            this.cellule[ligne][colonne].meurt();
        }
        else {
                this.cellule[ligne][colonne].vit();
        }
    }

    /**
        Parcours la liste des commandes
     * et delegue à son objet la responsable d'executer leur traitement
     */
    public void executeCommandes() {
        for(Commande commande : commandes) {
            commande.executer();
        }
        this.commandes.clear();
    }

    /**
     * Distribue le visiteur sur l'ensemble des cellules
     */
    public void distribueVisiteur() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                this.cellule[i][j].accepte(visiteur);
            }
        }
    }

    /**
     * Calcul de la generation suivante
     */
    public void calculerGenerationSuivante() {
        this.numGeneration += 1;
        // Distribue les visiteurs
        this.distribueVisiteur();
        // Execute les visiteurs
        this.executeCommandes();
        // Notifie les observateurs
        this.notifieObservateur();

    }

    /**
     *
     * @return Retourne le nombre de cellule vivantes dans la grille
     */
    public int getCellulesVivantes() {
        int num=0;
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if(cellule[i][j].estVivante()) {
                    num++;
                }
            }
        }
        return num;
    }

    /**
     *
     * @return Le nombre de lignes
     */
    public int getLignes() {  return lignes;    }

    /**
     *
     * @return Le nombre de colonnes
     */
    public int getColonnes() {  return colonnes; }

    /**
     * Main
     * @param args Args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JeuDeLaVie jeu = new JeuDeLaVie(50,50);
                JeuDeLaVieUI jeuDeLaVieUI = new JeuDeLaVieUI();
                GameController controller = new GameController(jeu, jeuDeLaVieUI);
                jeuDeLaVieUI.setController(controller);
                jeuDeLaVieUI.start();
                Observateur s = new ObservateurConsole(jeu);
            }
        });
    }
}
