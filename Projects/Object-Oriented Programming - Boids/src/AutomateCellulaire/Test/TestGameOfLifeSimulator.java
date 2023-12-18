package AutomateCellulaire.Test;

import AutomateCellulaire.GameOfLife;
import AutomateCellulaire.Simulator.GameOfLifeSimulator;
import gui.GUISimulator;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * Classe de test pour le simulateur du jeu de la vie
 */
public class TestGameOfLifeSimulator {
    public static void main(String[] args) {
        ArrayList<Point> listeCellAlive = new ArrayList<>(); // Liste des cellules vivantes
        Random random = new Random(); // Générateur de nombres aléatoires

        int initialCellCount = 1000; // Nombre initial de cellules vivantes
        int gridSize = 50; // Taille de la grille du jeu

//        // Génération de positions aléatoires pour les cellules vivantes
//        for (int i = 0; i < initialCellCount; i++) {
//            int x = random.nextInt(gridSize);
//            int y = random.nextInt(gridSize);
//            listeCellAlive.add(new Point(x, y));
//        }

        //Generation d'un 'Gosper Glider Gun'
        listeCellAlive.add(new Point(5, 5));
        listeCellAlive.add(new Point(6, 5));
        listeCellAlive.add(new Point(5, 6));
        listeCellAlive.add(new Point(6, 6));
        listeCellAlive.add(new Point(15, 5));
        listeCellAlive.add(new Point(15, 6));
        listeCellAlive.add(new Point(15, 7));
        listeCellAlive.add(new Point(16, 4));
        listeCellAlive.add(new Point(16, 8));
        listeCellAlive.add(new Point(17, 3));
        listeCellAlive.add(new Point(18, 3));
        listeCellAlive.add(new Point(17, 9));
        listeCellAlive.add(new Point(18, 9));
        listeCellAlive.add(new Point(19, 6));
        listeCellAlive.add(new Point(21, 5));
        listeCellAlive.add(new Point(21, 6));
        listeCellAlive.add(new Point(21, 7));
        listeCellAlive.add(new Point(22, 6));
        listeCellAlive.add(new Point(20, 4));
        listeCellAlive.add(new Point(20, 8));
        listeCellAlive.add(new Point(25, 5));
        listeCellAlive.add(new Point(25, 4));
        listeCellAlive.add(new Point(25, 3));
        listeCellAlive.add(new Point(26, 5));
        listeCellAlive.add(new Point(26, 4));
        listeCellAlive.add(new Point(26, 3));
        listeCellAlive.add(new Point(27, 2));
        listeCellAlive.add(new Point(27, 6));
        listeCellAlive.add(new Point(29, 2));
        listeCellAlive.add(new Point(29, 1));
        listeCellAlive.add(new Point(29, 6));
        listeCellAlive.add(new Point(29, 7));
        listeCellAlive.add(new Point(39, 4));
        listeCellAlive.add(new Point(40, 4));
        listeCellAlive.add(new Point(39, 3));
        listeCellAlive.add(new Point(40, 3));

        // Création de l'interface graphique
        GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);

        // Création du jeu de la vie avec la liste de cellules vivantes
        GameOfLife jeu = new GameOfLife(gui, listeCellAlive);

        // Configuration de l'interface graphique avec le simulateur du jeu de la vie
        gui.setSimulable(new GameOfLifeSimulator(jeu, gui));
    }
}