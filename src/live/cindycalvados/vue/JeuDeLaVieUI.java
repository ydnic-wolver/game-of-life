/**
 * @author CALVADOS Cindy
 */
package live.cindycalvados.vue;

import live.cindycalvados.controller.GameController;
import live.cindycalvados.JeuDeLaVie;
import live.cindycalvados.util.observateurs.ObservateurPanel;
import live.cindycalvados.util.observateurs.Observateur;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Classe responsable de l'affichage du jeu
 */
public class JeuDeLaVieUI extends JFrame implements Observateur{

    /**
     * Reference vers le panel de la grille
     */
    private GridPanel grillePanel;

    /**
     * Reference vers le controller
     */
    private GameController controller;

    /**
     * Reference vers le JeuDeLaVie
     */
    private JeuDeLaVie jeu;

    /**
     * Methode responsable de l'appel des methodes permettant la creation des
     * differents composants graphiques
     */
    public void start() {
        layoutComponents();
        setTitle("Jeu de la vie - CALVADOS Cindy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(calculateWidth(),800);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * @return Calcule la largeur de la fenetre
     */
    private int calculateWidth() {
        return grillePanel.getLargeurComposant() + 30;
    }

    /**
     * Actualise l'affichage de la fenetre
     */
    public void actualise() {
        repaint();
        revalidate();
    }

    /**
     * Initialisation des caracteristiques du layout principal
     */
    private void initLayoutPrincipal() {
        Container contentPane = getContentPane();
        BorderLayout layout = new BorderLayout();
        contentPane.setLayout(layout);
    }

    /**
     * Ajoute le panneau correspondant à la grille dans la fenetre
     */
    private void ajoutGrillePanel() {
        grillePanel = new GridPanel(controller);
        grillePanel.setModel(jeu);
        add(grillePanel, BorderLayout.CENTER);
    }

    /**
     * Methode gerant la creation des composants graphiques
     */
    private void layoutComponents() {
        // Initialisation du layout principal
        initLayoutPrincipal();
        // Ajout de la grille dans la fenetre
        ajoutGrillePanel();

        // Ajout des panneaux contenat les boutons,
        // le slider et la ComboBox
        JPanel graphic = new JPanel();
        graphic.setLayout(new BoxLayout(graphic, BoxLayout.Y_AXIS));
        graphic.add(new ObservateurPanel(jeu));
        graphic.add(ajoutPanneauBouton());
        graphic.add(ajoutCompl());
        add(graphic, BorderLayout.PAGE_START);
    }

    /**
     * @return Retourne le panneau contenant l'ensemble des boutons
     */
    private JPanel ajoutPanneauBouton() {
        JPanel bar = newButtonBar();
        bar.add(newRandomButton());
        bar.add(newStartButton());
        bar.add(newNextButton());
        bar.add(newStopButton());
        bar.add(newResetButton());
        return bar;
    }

    /**
     *  Methode responsable de la creation et la gestion de
     *  l'evenement lie a la checkbox Grille Aleatoire.
     * @return La reference vers l'objet checkbox Random
     */
    private JCheckBox newRandomButton() {
        JCheckBox cbRandom = new JCheckBox("Grille aleatoire");
        cbRandom.addActionListener(e -> {
            // Si la checkbox grille aleatoire est cocher ,
            // le mode aleatoire est active , sinon il est desactive
            this.jeu.setAleatoire(cbRandom.isSelected());
            this.jeu.InitialiseGrille();
            this.actualise();
        });
        return cbRandom;
    }

    /**
     * @return Retourne le panneau contenant la ComboBox
     * et le Sliders
     */
    private JPanel ajoutCompl() {
        JPanel bar = new JPanel();
        bar.add(newModeComboBox());
        bar.add(newSliders());
        return bar;
    }

    /**
     * Creation et affectation des evenements du Sliders
     * @return Slider
     */
    private JSlider newSliders() {
        JSlider slider  = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener( e -> {
            JSlider source = (JSlider)e.getSource();
            controller.setVitesse(source.getValue());
        });
        return slider;
    }

    /**
     * Creation et affectation des evenements du ComboBox
     * @return Slider
     */
    private JComboBox<String>newModeComboBox() {
        JComboBox<String> modeCombo = new JComboBox<>();
        modeCombo.setModel(new DefaultComboBoxModel<>( new String[] { "Classique", "Day & Night", "HighLife"}) );
        modeCombo.addActionListener( e -> {
            // Recupere le texte selectionne
            String choix = Objects.requireNonNull(modeCombo.getSelectedItem()).toString();
            // Modifie la regle de generation
            controller.setMode(choix);
        });
        modeCombo.setSelectedIndex(0);
        return modeCombo;
    }

    /**
     * Creation du layout pour les boutons
     * @return Layout
     */
    private JPanel newButtonBar() {
        JPanel bar = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(2);
        bar.setLayout(layout);
        return bar;
    }

    /**
     * Creation et affectation des evenements du Bouton Lancer
     * @return Bouton
     */
    private JButton newStartButton() {
        JButton button = new JButton("Lancer");
        button.addActionListener(e -> controller.lancerJeu());
        return button;
    }

    /**
     * Creation et affectation des evenements du Bouton Stopper
     * @return Bouton
     */
    private JButton newStopButton() {
        JButton button = new JButton("Stopper");
        button.addActionListener(e -> controller.stop());
        return button;
    }

    /**
     * Creation et affectation des evenements du Bouton Avancer
     * @return Bouton
     */
    private JButton newNextButton() {
        JButton button = new JButton("Avancer");
        button.addActionListener(e -> controller.avancer());
        return button;
    }

    /**
     * Creation et affectation des evenements du Bouton Reset
     * @return Bouton
     */
    private JButton newResetButton() {
        JButton button = new JButton("Reset");
        button.addActionListener(e -> controller.reset());
        return button;
    }

    /**
     * Associe le controller a l'ihm
     * @param controller Controlleur
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Associe le jeu a l'ihm
     * @param plateau Jeu
     */
    public void setModel(JeuDeLaVie plateau) {
        this.jeu = plateau;
    }
}

