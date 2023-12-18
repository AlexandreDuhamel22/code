package AutomateCellulaire;

import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class représentant une grille
 */
public abstract class Grid {
    // Une map pour représenter la grille avec les états des cellules
    public Map<Point, Integer> grid;

    // Nombre de lignes et de colonnes dans la grille
    private int ligne;
    private int colonne;

    // Map représentant l'état initial de la grille
    private Map<Point, Integer> ptInitMap;

    /**
     *  Constructeur prenant le nombre de lignes, de colonnes et une liste de points initiaux
     */
    public Grid(int lins, int cols, ArrayList<Point> ptInit) {
        // Initialisation de la map représentant l'état initial avec une copie des points initiaux
        this.ptInitMap = new HashMap<>();
        for (Point cell : ptInit) {
            ptInitMap.put(new Point(cell), 1); // Chaque point initial a une valeur de 1 (cellule colorée)
        }

        // Initialisation de la map représentant la grille avec des cellules non colorées
        this.grid = new HashMap<>();
        for (int i = 0; i < lins; i++) {
            for (int j = 0; j < cols; j++) {
                grid.put(new Point(i, j), 0); // Toutes les cellules sont initialisées à 0 (non colorées)
            }
        }

        this.colonne = cols;
        this.ligne = lins;

        // Colorier la grille initiale en utilisant la map d'état initial
        this.grid.putAll(this.ptInitMap);
    }

    /**
     * Constructeur prenant le nombre de lignes, de colonnes et une map d'état initial
     */
    public Grid(int lins, int cols, Map<Point, Integer> ptInit) {
        // Attribution de la map d'état initial
        this.ptInitMap = new HashMap<>(ptInit);

        // Initialisation de la map représentant la grille avec des cellules non colorées
        this.grid = new HashMap<>();
        for (int i = 0; i < lins; i++) {
            for (int j = 0; j < cols; j++) {
                grid.put(new Point(i, j), 0); // Toutes les cellules sont initialisées à 0 (non colorées)
            }
        }

        this.colonne = cols;
        this.ligne = lins;

        // Colorier la grille initiale en utilisant la map d'état initial
        this.grid.putAll(this.ptInitMap);
    }

    /**
     * Constructeur prenant une instance de GUISimulator et une liste de points initiaux
     */
    public Grid(GUISimulator gui, ArrayList<Point> ptInit) {
        // Appelle le constructeur principal en utilisant les dimensions du panneau GUI
        this(gui.getPanelHeight() / 10, gui.getPanelWidth() / 10, ptInit);
    }

    /**
     *  Constructeur prenant une instance de GUISimulator et une map d'état initial
     */
    public Grid(GUISimulator gui, Map<Point, Integer> ptInit) {
        // Appelle le constructeur principal en utilisant les dimensions du panneau GUI
        this(gui.getPanelHeight() / 10, gui.getPanelWidth() / 10, ptInit);
    }

    /**
     * Méthode pour obtenir la map représentant l'état initial de la grille
     */
    public Map<Point, Integer> getPtInitMap() {
        return ptInitMap;
    }

    /**
     * Méthodes pour obtenir le nombre de colonnes
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Méthodes pour obtenir le nombre de lignes
     */
    public int getLigne() {
        return ligne;
    }
}
