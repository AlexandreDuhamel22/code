package SimuBoids;

import java.util.ArrayList;

/**
 * Essaim --- Classe représentant un ensemble de Boids (oiseaux)
 */
public class Essaim {
    // Liste des membres de l'essaim (Boids)
    public ArrayList<Boid> membre;

    // Distance de vision des Boids dans l'essaim
    private int distanceVision;

    // Angle de vision des Boids dans l'essaim
    private int angleVision;

    /**
     * Constructeur de l'essaim.
     * @param distanceVision La distance de vision des Boids.
     * @param angleVision L'angle de vision des Boids.
     */
    public Essaim(int distanceVision, int angleVision) {
        // Initialise la liste des membres de l'essaim
        this.membre = new ArrayList<Boid>();
        // Initialise la distance de vision
        this.distanceVision = distanceVision;
        // Initialise l'angle de vision
        this.angleVision = angleVision;
    }

    /**
     * Obtient la distance de vision des Boids dans l'essaim.
     * @return La distance de vision.
     */
    public int getDistanceVision() {
        return distanceVision;
    }

    /**
     * Obtient l'angle de vision des Boids dans l'essaim.
     * @return L'angle de vision.
     */
    public int getAngleVision() {
        return angleVision;
    }

    /**
     * Ajoute un Boid à l'essaim.
     * @param boid Le Boid à ajouter à l'essaim.
     */
    public void addBoid(Boid boid) {
        this.membre.add(boid);
    }

    /**
     * Déplace tous les Boids dans l'essaim en fonction des limites spécifiées (Xmax, Ymax).
     * @param Xmax La limite horizontale maximale.
     * @param Ymax La limite verticale maximale.
     */
    public void moveAll(int Xmax, int Ymax) {
        for (Boid boid : membre) {
            boid.move(Xmax, Ymax);
        }
    }

    /**
     * Réinitialise tous les Boids dans l'essaim.
     */
    public void reInit() {
        for (Boid boid: this.membre) {
            boid.reInit();
        }
    }
}