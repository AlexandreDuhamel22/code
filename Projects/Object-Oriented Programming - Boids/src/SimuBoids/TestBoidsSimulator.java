package SimuBoids;

import gui.GUISimulator;

import java.awt.*;
import java.util.Random;

/**
 * TestBoidsSimulator --- Classe de test pour le simulateur Boids
 */
public class TestBoidsSimulator {
    public static void main(String[] args) {
        // Création de l'interface graphique
        GUISimulator gui = new GUISimulator(1000, 1000, Color.CYAN);

        // Initialisation d'un essaim de Boids
        Essaim essaim1 = new Essaim(100, 110);

        // Génération de 50 Boids avec des positions et des vitesses aléatoires
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            // Génération de positions aléatoires
            Vector2D pos = new Vector2D(rand.nextDouble(gui.getPanelWidth()), rand.nextDouble(gui.getPanelHeight()));

            // Génération de vitesses aléatoires comprises entre -10 et 10 sur x et y
            Vector2D vit = new Vector2D(rand.nextDouble(20) - 10, rand.nextDouble(20) - 10);

            // Création d'un Boid et ajout à l'essaim
            Boid boid = new Boid(pos, vit, essaim1);
        }

        // Configuration de l'interface graphique avec le simulateur Boids
        gui.setSimulable(new BoidsSimulator(gui, essaim1));
    }
}