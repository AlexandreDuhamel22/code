package AutomateCellulaire;

import gui.GUISimulator;

import java.awt.*;
import java.util.*;



/**
 * Classe représentant le modèle Schelling qui étend la classe Grid et implémente l'interface Jeu
 */
public class SchellingModel extends Grid implements Jeu {
    private int K;
    private Queue<Point> habitationDispo; // File des habitations disponibles

    /**
     * Constructeur prenant les dimensions de la grille, la carte initiale, le paramètre K et initialisant les habitations disponibles
     */
    public SchellingModel(int lins, int cols, Map<Point, Integer> ptInit, int K) {
        super(lins, cols, ptInit);
        this.K = K;
        this.habitationDispo = new LinkedList<Point>();

        // Ajoute les habitations disponibles à la file
        for (Point cell : ptInit.keySet()) {
            if (ptInit.get(cell) == 0) {
                this.habitationDispo.add(cell);
            }
        }
    }

    /**
     * Constructeur prenant l'interface graphique, la carte initiale, le paramètre K et initialisant les habitations disponibles
     */
    public SchellingModel(GUISimulator gui, Map<Point, Integer> ptInit, int K) {
        super(gui, ptInit);
        this.K = K;
        this.habitationDispo = new LinkedList<Point>();

        // Ajoute les habitations disponibles à la file
        for (Point cell : ptInit.keySet()) {
            if (ptInit.get(cell) == 0) {
                this.habitationDispo.add(cell);
            }
        }
    }

    /**
     * Méthode pour calculer le nombre de voisins différents d'une cellule avec une couleur spécifiée
     */
    public int nbVoisin(int lin, int col, int couleurCell) {
        int nbVoisins = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newLin = (lin + dx[i] + getLigne()) % getLigne();
            int newCol = (col + dy[i] + getColonne()) % getColonne();
            int couleurVoisin = this.grid.get(new Point(newLin, newCol));

            // Les habitations vacantes ne sont pas considérées comme des voisins
            if (couleurVoisin != couleurCell && couleurVoisin != 0) {
                nbVoisins++;
            }
        }
        return nbVoisins;
    }

    /**
     * Méthode pour calculer le nombre de voisins différents d'une cellule spécifiée
     */
    public int nbVoisin(Point cell) {
        return nbVoisin(cell.x, cell.y, this.grid.get(cell));
    }

    /**
     * Méthode pour faire évoluer le modèle Schelling d'une génération à l'autre
     */
    public void evolve() {
        Map<Point, Integer> newGrid = new HashMap<Point, Integer>();
        Queue<Point> newHabitationDispo = new LinkedList<Point>(habitationDispo);

        // Parcours de chaque cellule de la grille
        for (Map.Entry<Point, Integer> entry : this.grid.entrySet()) {
            Point cell = entry.getKey();
            int couleurCell = entry.getValue();

            // Si la cellule est vacante, pas besoin de calculer ses voisins
            if (couleurCell == 0) {
                continue;
            }

            int nbVoisinCell = nbVoisin(cell);

            // Si le nombre de voisins différents est supérieur ou égal à K, déménagement
            if (nbVoisinCell >= this.K) {
                Point newHab = this.habitationDispo.poll();
                if (newHab != null) {
                    newHabitationDispo.remove();

                    // On occupe une habitation disponible
                    newGrid.put(newHab, couleurCell);

                    // On rend la cellule (Habitation) disponible
                    newGrid.put(cell, 0);
                    newHabitationDispo.add(cell);
                }
            }
        }

        // Met à jour la grille et la file des habitations disponibles
        this.grid.putAll(newGrid);
        this.habitationDispo = newHabitationDispo;
    }

    /**
     * Méthode pour réinitialiser le modèle Schelling à un état initial
     */
    public void reInit() {
        // Réinitialisation de la grille avec la carte initiale
        this.grid.putAll(this.getPtInitMap());

        // Réinitialisation de la file des habitations disponibles
        this.habitationDispo.clear(); // On vide la file

        // On la réinitialise en fonction de la grille déjà réinitialisée
        for (Point cell : this.grid.keySet()) {
            if (this.grid.get(cell) == 0) {
                this.habitationDispo.add(cell);
            }
        }
    }
}