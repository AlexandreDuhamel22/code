package SimuBalls;

import java.awt.Point;
import java.util.Random;
import java.awt.Color;

/**
 * Class représentant un ensemble de balles
 */
public class Balls {
    // Tableau de positions actuelles des balles
    Point[] tabBalls;

    // Tableau de positions initiales des balles
    Point[] tabInit;

    // Tableau de vitesses des balles (chaque balle a une vitesse x et y)
    double[][] vitesse;

    // Générateur de nombres aléatoires
    Random random = new Random();

    // Dimension maximale de l'espace dans lequel les balles se déplacent
    private int maxX = 500;
    private int maxY = 500;

    // Paramètres de mouvement des balles
    private double vitesseXMinimale = 0.8;
    private double vitesseYMinimale = 0.8;
    private double decelerationX = 0.9;
    private double decelerationY = 0.9;

    // Tableau de couleurs associées à chaque balle
    public Color[] colors;

    /**
     * Constructeur pour créer des balles aléatoires dans un espace donné
     */
    public Balls(int nb_ball, int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.tabBalls = new Point[nb_ball];
        this.tabInit = new Point[nb_ball];
        this.colors = new Color[nb_ball];
        this.vitesse = new double[nb_ball][2];

        // Initialisation des balles avec des positions aléatoires et des vitesses aléatoires
        for (int i = 0; i < nb_ball; i++) {
            int x = random.nextInt(maxX - 5);
            int y = random.nextInt(maxY - 5);
            this.tabBalls[i] = new Point(x, y);
            this.tabInit[i] = new Point(x, y);

            // Assure une vitesse minimale pour chaque composante de la vitesse
            while (Math.abs(this.vitesse[i][0]) < vitesseXMinimale) {
                this.vitesse[i][0] = random.nextInt(20) - 10;
            }
            while (Math.abs(this.vitesse[i][1]) < vitesseYMinimale) {
                this.vitesse[i][1] = random.nextInt(20) - 10;
            }

            // Génère une couleur aléatoire pour chaque balle
            colors[i] = generateRandomColor();
        }
    }

    /**
     * Constructeur pour créer des balles à partir de positions initiales spécifiées
     */
    public Balls(Point[] tabInit) {
        this.tabInit = tabInit;
        this.tabBalls = new Point[tabInit.length];

        // Crée des balles avec des positions initiales spécifiées
        for (int i = 0; i < tabInit.length; i++) {
            this.tabBalls[i] = new Point(tabInit[i]);
        }
    }

    /**
     * Méthode qui génère une couleur aléatoire
     */
    private Color generateRandomColor() {
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }

    /**
     * Méthode qui met à jour les positions des balles en fonction de leurs vitesses
     */
    public void translate() {
        for (int i = 0; i < this.tabBalls.length; i++) {
            Point ball = this.tabBalls[i];
            double dx = this.vitesse[i][0];
            double dy = this.vitesse[i][1];

            double newX = ball.x + dx;
            double newY = ball.y + dy;

            // Gestion des rebonds sur les bords de l'espace
            if (newX <= 0 || newX >= maxX - 5) {
                double newSpeedX = -dx * decelerationX;
                if (Math.abs(newSpeedX) < vitesseXMinimale) {
                    newSpeedX = -Math.abs(dx) / dx * vitesseXMinimale;
                }
                this.vitesse[i][0] = newSpeedX;
                newX = ball.x + this.vitesse[i][0];
            }

            if (newY <= 0 || newY >= maxY - 5) {
                double newSpeedY = -dy * decelerationY;
                if (Math.abs(newSpeedY) < vitesseYMinimale) {
                    newSpeedY = -Math.abs(dy) / dy * vitesseYMinimale;
                }
                this.vitesse[i][1] = newSpeedY;
                newY = ball.y + this.vitesse[i][1];
            }

            ball.setLocation(newX, newY);
        }
    }

    /**
     * Méthode qui réinitialise les positions des balles à leurs positions initiales
     */
    public void reInit() {
        for (int i = 0; i < this.tabBalls.length; i++) {
            this.tabBalls[i].setLocation(this.tabInit[i]);
        }
    }

    /**
     *  Surcharge de la méthode toString pour afficher les positions des balles
     */
    @Override
    public String toString() {
        String str = "Balls :";
        for (Point Ball : this.tabBalls) {
            str += Ball.toString() + "  ";
        }
        return str;
    }
}