package AutomateCellulaire.Simulator;

import AutomateCellulaire.GameOfImmigration;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

/**
 * Classe représentant le simulateur du jeu d'immigration qui implémente l'interface Simulable
 */
public class GameOfImmigrationSimulator implements Simulable {
    GUISimulator gui;
    GameOfImmigration jeu;

    /**
     * Constructeur prenant une instance du jeu d'immigration et une interface graphique GUISimulator
     */
    public GameOfImmigrationSimulator(GameOfImmigration jeu, GUISimulator gui) {
        this.jeu = jeu;
        this.gui = gui;
    }

    /**
     * Méthode pour dessiner l'état actuel du jeu d'immigration dans l'interface graphique
     */
    public void draw() {
        gui.reset();

        // Calcul du pas pour la variation de luminosité en fonction du nombre d'états possibles
        float pas = (float) 1 / (jeu.nbState - 1);

        // Parcours de chaque cellule de la grille du jeu d'immigration
        for (Point cell : this.jeu.grid.keySet()) {
            int i = cell.x;
            int j = cell.y;

            // Calcul de la couleur en fonction de l'état de la cellule
            float lum = pas * this.jeu.grid.get(cell);
            Color couleurCell = new Color((float) 1 - lum, (float) 1 - lum, (float) 1 - lum);

            // Dessine un rectangle coloré en fonction de l'état de la cellule
            this.gui.addGraphicalElement(new Rectangle((i + 1) * 10, (j + 1) * 10, Color.WHITE, couleurCell, 10));
        }
    }

    /**
     * Méthode de l'interface Simulable pour effectuer une étape de simulation
     */
    @Override
    public void next() {
        this.draw(); // Dessine l'état actuel du jeu d'immigration
        jeu.evolve(); // Fait évoluer le jeu d'immigration d'une génération à l'autre
    }

    /**
     * Méthode de l'interface Simulable pour réinitialiser l'état du jeu d'immigration à un état initial
     */
    @Override
    public void restart() {
        gui.reset(); // Réinitialise l'interface graphique
        jeu.reInit(); // Réinitialise l'état du jeu d'immigration
        this.draw(); // Dessine l'état initial du jeu d'immigration
    }
}