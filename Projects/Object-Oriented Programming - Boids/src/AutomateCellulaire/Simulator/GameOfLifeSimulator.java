package AutomateCellulaire.Simulator;

import AutomateCellulaire.GameOfLife;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;


/**
 *  Classe représentant un simulateur pour le jeu de la vie implémentant l'interface Simulable
 */
public class GameOfLifeSimulator implements Simulable {
    GUISimulator gui; // Interface graphique
    GameOfLife jeu;

    /**
     * Constructeur prenant une instance du jeu de la vie et une interface graphique GUISimulator
     */
    public GameOfLifeSimulator(GameOfLife jeu, GUISimulator gui) {
        this.jeu = jeu;
        this.gui = gui;
    }

    /**
     * Méthode pour dessiner l'état actuel du jeu dans l'interface graphique
     */
    public void draw() {
        gui.reset();

        // Parcours de chaque cellule de la grille du jeu
        for (Point cell : this.jeu.grid.keySet()) {
            int i = cell.x;
            int j = cell.y;

            // Dessine un rectangle de couleur en fonction de l'état de la cellule (vivante ou morte)
            if (this.jeu.grid.get(cell) == 1) {
                this.gui.addGraphicalElement(new Rectangle((i + 1) * 10, (j + 1) * 10, Color.BLACK, Color.BLUE, 10));
            } else {
                this.gui.addGraphicalElement(new Rectangle((i + 1) * 10, (j + 1) * 10, Color.BLACK, Color.WHITE, 10));
            }
        }
    }

    /**
     *  Méthode de l'interface Simulable pour effectuer une étape de simulation
     */
    @Override
    public void next() {
        this.draw(); // Dessine l'état actuel du jeu
        jeu.evolve(); // Fait évoluer le jeu d'une génération à l'autre
    }

    /**
     * Méthode de l'interface Simulable pour réinitialiser l'état du jeu à un état initial
     */
    @Override
    public void restart() {
        gui.reset(); // Réinitialise l'interface graphique
        jeu.reInit(); // Réinitialise l'état du jeu
        this.draw(); // Dessine l'état initial du jeu
    }
}