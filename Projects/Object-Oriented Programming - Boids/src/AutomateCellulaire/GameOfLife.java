package AutomateCellulaire;

import gui.GUISimulator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 * Classe représentant le jeu de la vie (Game of Life) qui étend la classe Grid et implémente l'interface Jeu
 */
public class GameOfLife extends Grid implements Jeu {

    /**
     * Constructeur prenant le nombre de lignes, de colonnes et une liste de points initiaux
     */
    public GameOfLife(int lins, int cols, ArrayList<Point> ptInit) {
        super(lins, cols, ptInit);
    }

    /**
     * Constructeur prenant une instance de GUISimulator et une liste de points initiaux
     */
    public GameOfLife(GUISimulator gui, ArrayList<Point> ptInit) {
        super(gui.getPanelHeight() / 10, gui.getPanelWidth() / 10, ptInit);
    }

    /**
     * Méthode pour calculer le nombre de voisins d'une cellule spécifiée par ses coordonnées
     */
    private int nbVoisin(int lin, int col) {
        int nbVoisins = 0;
        // Décalages relatifs pour les voisins autour d'une cellule
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        // Boucle sur les voisins potentiels
        for (int i = 0; i < 8; i++) {
            // Calcul des nouvelles coordonnées pour le voisin
            int newLin = (lin + dx[i] + getLigne()) % getLigne();
            int newCol = (col + dy[i] + getColonne()) % getColonne();
            Point voisin = new Point(newLin, newCol);
            // Vérification si le voisin existe et est vivant (valeur 1)
            if (this.grid.get(voisin) != null && this.grid.get(voisin) == 1) {
                nbVoisins++;
            }
        }
        return nbVoisins;
    }

    /**
     * Méthode de l'interface Jeu pour calculer le nombre de voisins d'une cellule
     */
    @Override
    public int nbVoisin(Point cell) {
        return nbVoisin(cell.x, cell.y);
    }

    /**
     *  Méthode de l'interface Jeu pour faire évoluer l'état du jeu d'une génération à l'autre
     */
    @Override
    public void evolve() {
        // Nouvelle grille pour stocker les changements
        Map<Point, Integer> newGrid = new HashMap<>();
        // Parcours de chaque cellule de la grille actuelle
        for (Point cell : grid.keySet()) {
            // Calcul du nombre de voisins pour la cellule actuelle
            int nbVoisinCell = nbVoisin(cell);
            // Logique du jeu de la vie pour déterminer l'état de la cellule dans la génération suivante
            if (this.grid.get(cell) == 0 && nbVoisinCell == 3) {
                newGrid.put(cell, 1); // Naissance d'une nouvelle cellule
            } else if (this.grid.get(cell) == 1 && (nbVoisinCell == 3 || nbVoisinCell == 2)) {
                newGrid.put(cell, 1); // La cellule survit
            } else if (this.grid.get(cell) == 1) {
                newGrid.put(cell, 0); // La cellule meurt
            } else {
                newGrid.put(cell, this.grid.get(cell)); // La cellule reste morte
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
        // Parcours de chaque cellule de la grille actuelle
        for (Point cell : grid.keySet()) {
            // Vérifie si la cellule fait partie des points initiaux
            if (this.getPtInitMap().containsKey(cell)) {
                this.grid.put(cell, 1); // La cellule est vivante
            } else {
                this.grid.put(cell, 0); // La cellule est morte
            }
        }
    }
}