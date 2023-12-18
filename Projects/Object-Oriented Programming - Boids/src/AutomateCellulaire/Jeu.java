package AutomateCellulaire;
import java.awt.Point;

/**
 *  Interface représentant un jeu
 */
public interface Jeu {
    /**
     * Méthode pour calculer le nombre de voisins d'une cellule donnée
     */
    public int nbVoisin(Point cell);

    /**
     * Méthode pour faire évoluer l'état du jeu d'une génération à l'autre
     */
    public void evolve();

    /**
     * Méthode pour réinitialiser l'état du jeu à un état initial
     */
    public void reInit();
}