package AutomateCellulaire.Simulator;

import AutomateCellulaire.SchellingModel;
import gui.GUISimulator;
import gui.Simulable;

import java.awt.*;


/**
 * Classe représentant le simulateur pour le modèle Schelling, implémentant l'interface Simulable
 */
public class SchellingModelSimulator implements Simulable {
    GUISimulator gui;
    SchellingModel jeu;

    /**
     * Constructeur prenant le modèle Schelling et l'interface graphique
     */
    public SchellingModelSimulator(SchellingModel jeu, GUISimulator gui) {
        this.jeu = jeu;
        this.gui = gui;
    }

    /**
     * Méthode pour dessiner la grille du modèle Schelling
     */
    public void draw() {
        gui.reset();

        // Parcours de chaque cellule de la grille
        for (Point cell : this.jeu.grid.keySet()) {
            int i = cell.x;
            int j = cell.y;
            int lum = this.jeu.grid.get(cell) + 1;

            // Calcul de la couleur en fonction de la luminosité
            Color couleurCell = new Color((int) 255 / lum, (int) 255 / lum, (int) 255 / lum);

            // Ajout de la cellule à l'interface graphique
            this.gui.addGraphicalElement(new gui.Rectangle((i + 1) * 10, (j + 1) * 10, Color.WHITE, couleurCell, 10));
        }
    }

    /**
     * Méthode appelée à chaque étape de simulation pour passer à la génération suivante
     */
    public void next() {
        this.draw();
        jeu.evolve();
    }

    /**
     *  Méthode pour redémarrer la simulation en réinitialisant le modèle Schelling
     */
    public void restart() {
        gui.reset();
        jeu.reInit();
        this.draw();
    }
}