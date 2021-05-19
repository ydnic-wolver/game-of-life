/**
 * @author CALVADOS Cindy
 */


package live.cindycalvados.controller;

import live.cindycalvados.JeuDeLaVie;
import live.cindycalvados.util.visiteur.Visiteur;
import live.cindycalvados.util.visiteur.VisiteurClassique;
import live.cindycalvados.util.visiteur.VisiteurDayLight;
import live.cindycalvados.util.visiteur.VisiteurHighLife;
import live.cindycalvados.vue.JeuDeLaVieUI;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Classe Controlleur servant de lien entre le Jeu de la vie
 * et l'IHM
 *
 * - La classe Controlleur est responsable :
 *  - De la gestion de vitesse ( acceleration / deceleration )
 *  - Du changement de mode ( Modification du visiteur )
 *  - Lancement d'une generation
 *  - Arret d'une generation
 */
public class GameController implements Runnable{

    /**
     * Reference sur la classe JeuDeLaVie
     */
    private final JeuDeLaVie jeu;

    /**
     * Reference sur la classe JeuDeLaVieUI correspondant a
     * l'IHM
     */
    private final JeuDeLaVieUI vue;

    /*
        Visiteur Concret implementant la regle classique de generation
     */
    private final Visiteur vClassic;

    /*
        Visiteur Concret implementant la regle HighLife
     */
    private final Visiteur vHighLife;

    /*
        Visiteur Concret implementant la regle DayNight
     */
    private final Visiteur vDayNight;

    /**
     * Attribut correspondant à la vitesse de generation
     */
    private int vitesse;

    /*
        Attribut de type Thread
     */
    Thread worker;

    /**
     * Booleen Atomic correspondant à l'etat de la grille de generation
     */
    private final AtomicBoolean running = new AtomicBoolean(false);

    /**
     * Constructeur de l'objet GameController
     * @param jeu Reference vers le jeu
     * @param vue Reference vers la vue
     */
    public GameController(JeuDeLaVie jeu, JeuDeLaVieUI vue) {
        this.jeu = jeu;
        this.vue = vue;

        // Par defaut la vitesse du jeu est defini à 1
        this.vitesse = 1;

        // Initialisation de la grille
        this.jeu.InitialiseGrille();

        // Creation d'une association entre la vue et le modele ( Le jeu )
        this.vue.setModel(jeu);

        // Association du visiteur classique du jeu
        this.jeu.setVisiteur(new VisiteurClassique(this.jeu)); // par defaut : mode classique

        // Initialisation des differents types de visiteurs
        this.vClassic = new VisiteurClassique(this.jeu);
        this.vHighLife = new VisiteurHighLife(this.jeu);
        this.vDayNight = new VisiteurDayLight(this.jeu);

    }

    /**
     * Methode responsable de la creation d'un nouveau Thread
     * et de son lancement
     */
    public void lancerJeu() {
        worker = new Thread(this);
        worker.start();
    }

    /**
     * Methode encapsulant les traitements
     * liees aux calcul des generations suivantes en boucle,
     * ainsi que de la vitesse d'execution
     */
    @Override
    public void run() {
        running.set(true);
        // Boucle
        while( running.get() ) {
            /*
                Tant qu'il y a des cellules vivantes ont
               continue de calculer les generations suivantes
             */
            if( jeu.getCellulesVivantes() != 0){
                jeu.calculerGenerationSuivante();
                vue.actualise();
            }else {
                this.stop();
            }

            try {
                /*
                    Gestion de la vitesse
                 */
                int tmp = this.vitesse;
                if(tmp == 3) tmp *= 100;
                else if(tmp > 3) tmp =  500 - (tmp*100);
                else tmp = 200 + (tmp*100);

                /**
                 * Le thread est mis en pause
                 */
                Thread.sleep(tmp);
            }catch(InterruptedException ex){
                System.out.println("Erreur : "+ex);
            }

        }
    }

    /**
     * Methode permettant de modifier l'etat d'une cellule
     * @param ligne Numero de ligne de la cellule
     * @param colonne Numero de colonne de la cellule
     */
    public void toggle(int ligne, int colonne) {
        jeu.toggle(ligne, colonne);
        vue.actualise();
    }

    /**
     * Alteration de la vitesse des generations
     * @param vitesse Nouvelle vitesse de generation
     */
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    /**
     * Calcule la generation suivante
     */
    public void avancer() {
      if( jeu.getCellulesVivantes() != 0 ) {
          jeu.calculerGenerationSuivante();
          vue.actualise();
      }
    }

    /**
     * Reinitialise la grille
     */
    public void reset() {
        jeu.InitialiseGrille();
        vue.actualise();
    }

    /**
     * Stop la generation
     */
    public void stop() {
        running.set(false);
    }

    /**
     * Modifie la regle de generation des cellules
     * @param choix Regle de generation choisi
     */
    public void setMode(String choix) {
        if(choix.equals("Classique")){
            jeu.setVisiteur(vClassic);
        }
        else if(choix.equals("HighLife")){
            jeu.setVisiteur(vHighLife);
        }
        else {
            jeu.setVisiteur( vDayNight );
        }
    }
}
