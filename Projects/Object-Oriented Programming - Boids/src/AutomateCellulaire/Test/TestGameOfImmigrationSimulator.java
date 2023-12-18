package AutomateCellulaire.Test;

import AutomateCellulaire.GameOfImmigration;
import AutomateCellulaire.Simulator.GameOfImmigrationSimulator;
import gui.GUISimulator;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Classe de test pour le simulateur du jeu d'immigration
 */
public class TestGameOfImmigrationSimulator {
    public static void main(String[] args) {
        int Xmax = 500;
        int Ymax = 500;

        // Création de l'interface graphique avec une taille spécifiée et un fond blanc
        GUISimulator gui = new GUISimulator(Ymax, Xmax, Color.WHITE);

        // Initialisation d'une carte de cellules avec des états aléatoires
        Map<Point, Integer> mapCellInit = new HashMap<>();
        int nbCases = (Xmax * Ymax) / 100; // Calcul du nombre de cases pour 1% de la taille de la grille

        // Génération d'états aléatoires pour chaque case de la carte
        int[] randomState = new Random().ints(nbCases, 0, 4).toArray();
        for (int i = 0; i < nbCases; i++) {
            // Conversion d'un index en coordonnées de cellule
            mapCellInit.put(new Point(i / (Ymax / 10), i % (Ymax / 10)), randomState[i]);
        }

        // Création du jeu d'immigration avec la carte initiale et le nombre d'états possibles
        GameOfImmigration jeu = new GameOfImmigration(gui, mapCellInit, 4);

        // Configuration de l'interface graphique avec le simulateur du jeu d'immigration
        gui.setSimulable(new GameOfImmigrationSimulator(jeu, gui));
    }
}