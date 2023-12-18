package SimuBalls;

import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;

import java.awt.Color;
import java.awt.Point;

import Events.EventManager;

/**
 * Classe représentant une simulation graphique de balles se déplaçant dans un espace
 */
public class BallsSimulator implements Simulable {
    // Instance de la classe Balls pour gérer les balles
    Balls balls;

    // Instance de GUISimulator pour gérer l'interface graphique
    GUISimulator gui;

    //Instance de la classe EventManager qui traite chaque iteration d'une translation des balles comme un Event
    EventManager manager;

    /**
     * Constructeur prenant une instance de GUISimulator et le nombre de balles en paramètres
     */
    public BallsSimulator(GUISimulator gui, int nb_balls) {
        this.gui = gui;
        this.manager = new EventManager();
        // Initialise l'instance de Balls avec le nombre de balles et les dimensions du panneau GUI
        this.balls = new Balls(nb_balls, gui.getPanelHeight(), gui.getPanelWidth());
        this.manager.addEvent(new BallsEvent(0,balls,manager));
    }

    /**
     * Méthode pour dessiner les balles sur le GUI
     */
    public void draw() {
        gui.reset(); // Réinitialise le GUI avant de dessiner les nouvelles positions des balles

        // Dessine chaque balle à sa position actuelle avec sa couleur
        for (int i = 0; i < balls.tabBalls.length; i++) {
            Point ball = balls.tabBalls[i];
            Color color = balls.colors[i];
            gui.addGraphicalElement(new Oval(ball.x, ball.y, color, color, 10));
        }
    }
    /**
     * Méthode appelée à chaque itération de la simulation afin d'actualisé la position des balles
     */
    @Override
    public void next() {
        this.draw();
        manager.next();
    }

    /**
     *  Méthode pour réinitialiser la simulation
     */
    @Override
    public void restart() {
        gui.reset();
        balls.reInit();
        this.draw();
    }
}
