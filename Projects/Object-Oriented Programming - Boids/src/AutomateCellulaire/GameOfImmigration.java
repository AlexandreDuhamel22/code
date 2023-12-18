package AutomateCellulaire;

import gui.GUISimulator;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant le jeu d'immigration qui étend la classe Grid et implémente l'interface Jeu
 */
public class GameOfImmigration extends Grid implements Jeu {

    // Nombre total d'états possibles dans le jeu d'immigration
    public int nbState;

    /**
     * Méthode pour calculer le nombre de voisins d'une cellule spécifiée par ses coordonnées et son état
     */
    public int nbVoisin(int lin, int col, int state) {
        int nbVoisins = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        // Boucle sur les voisins potentiels
        for (int i = 0; i < 8; i++) {
            int newLin = (lin + dx[i] + getLigne()) % getLigne();
            int newCol = (col + dy[i] + getColonne()) % getColonne();

            // Vérifie si le voisin a l'état spécifié
            if (this.grid.get(new Point(newLin, newCol)) == (state + 1) % this.nbState) {
                nbVoisins++;
            }
        }
        return nbVoisins;
    }

    /**
     * Constructeur prenant le nombre de lignes, de colonnes, une liste de points initiaux et le nombre d'états
     */
    public GameOfImmigration(int lins, int cols, Map<Point, Integer> ptInit, int nbState) {
        super(lins, cols, ptInit);
        this.nbState = nbState;
    }

    /**
     * Constructeur prenant une instance de GUISimulator, une liste de points initiaux et le nombre d'états
     */
    public GameOfImmigration(GUISimulator gui, Map<Point, Integer> ptInit, int nbState) {
        super(gui, ptInit);
        this.nbState = nbState;
    }

    /**
     * Méthode de l'interface Jeu pour calculer le nombre de voisins d'une cellule spécifiée par un objet Point
     */
    @Override
    public int nbVoisin(Point cell) {
        return nbVoisin(cell.x, cell.y, this.grid.get(cell));
    }

    /**
     * Méthode de l'interface Jeu pour faire évoluer l'état du jeu d'une génération à l'autre
     */
    @Override
    public void evolve() {
        Map<Point, Integer> newGrid = new HashMap<>();
        // Parcours de chaque cellule de la grille actuelle
        for (Map.Entry<Point, Integer> entry : this.grid.entrySet()) {
            Point cell = entry.getKey();
            int state = entry.getValue();
            int nbVoisinCell = nbVoisin(cell);
            // Logique d'évolution basée sur le nombre de voisins
            if (nbVoisinCell >= 3) {
                newGrid.put(cell, (state + 1) % this.nbState);
            }
        }
        // Met à jour la grille avec les changements
        grid.putAll(newGrid);
    }

    /**
     * Méthode de l'interface Jeu pour réinitialiser l'état du jeu à un état initial
     */
    @Override
    public void reInit() {
        // Remplace toutes les valeurs de la grille par les valeurs initiales
        this.grid.replaceAll((c, v) -> this.getPtInitMap().getOrDefault(c, 0));
    }
}
