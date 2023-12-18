package SimuBoids;

import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;

/**
 * BoidsSimulator --- Classe implémentant l'interface Simulable pour le simulateur Boids
 */
public class BoidsSimulator implements Simulable {
    // Interface graphique
    GUISimulator gui;

    // Essaim de Boids
    Essaim essaim;

    // Liste des Boids initiale
    ArrayList<Boid> BoidInit;

    /**
     * Constructeur de BoidsSimulator.
     * @param gui Interface graphique.
     * @param essaim Essaim de Boids.
     */
    public BoidsSimulator(GUISimulator gui, Essaim essaim) {
        this.gui = gui;
        this.essaim = essaim;
        this.draw();
    }

    /**
     * Dessine les Boids dans l'interface graphique.
     */
    public void draw() {
        gui.reset();
        for (Boid boid : this.essaim.membre) {
            // Gestion du bec ( orientation du boid )
            Vector2D bec = boid.position.getAdd(boid.vitesse);
            Vector2D directionbec = bec.getSub(boid.position);
            directionbec = directionbec.getNormaliser();
            directionbec.mult(5);
            bec = boid.position.getAdd(directionbec);

            gui.addGraphicalElement(new Rectangle((int) bec.x, (int) bec.y, Color.YELLOW, Color.YELLOW, 5));
            gui.addGraphicalElement(new Oval((int) boid.position.x, (int) boid.position.y, Color.WHITE, Color.GRAY, 10));
        }
    }

    /**
     * Appelée à chaque étape de simulation.
     */
    public void next() {
        this.draw();
        essaim.moveAll(gui.getPanelHeight(), gui.getPanelWidth());
    }

    /**
     * Réinitialise la simulation.
     */
    public void restart() {
        gui.reset();
        this.essaim.reInit();
        this.draw();
    }
}