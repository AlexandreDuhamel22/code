package SimuBoids;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Boid --- Classe représentant un oiseau dans le simulateur Boids
 */
public class Boid {
    // Essaim auquel le Boid appartient
    public Essaim essaim;

    // Position actuelle du Boid
    public Vector2D position;

    // Vitesse actuelle du Boid
    public Vector2D vitesse;

    // Position initiale du Boid
    public Vector2D positionInit;

    // Vitesse initiale du Boid
    public Vector2D vitesseInit;

    /**
     * Constructeur d'un Boid.
     * @param pos La position initiale du Boid.
     * @param vit La vitesse initiale du Boid.
     * @param essaim L'essaim auquel le Boid appartient.
     */
    public Boid(Vector2D pos, Vector2D vit, Essaim essaim) {
        this.position = new Vector2D(pos);
        this.vitesse = new Vector2D(vit);
        this.positionInit = new Vector2D(pos);
        this.vitesseInit = new Vector2D(vit);
        this.essaim = essaim;
        this.essaim.addBoid(this);
    }

    /**
     * Vérifie si deux Boids sont égaux.
     * @param o L'objet à comparer.
     * @return Vrai si les Boids sont égaux, sinon faux.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Boid boid)) return false;
        return Objects.equals(essaim, boid.essaim) &&
                Objects.equals(position, boid.position) &&
                Objects.equals(vitesse, boid.vitesse) &&
                Objects.equals(positionInit, boid.positionInit) &&
                Objects.equals(vitesseInit, boid.vitesseInit);
    }

    /**
     * Retourne la liste des Boids voisins dans la vision du Boid.
     * @return La liste des Boids voisins.
     */
    private ArrayList<Boid> getVoisin() {
        ArrayList<Boid> result = new ArrayList<Boid>();
        for (Boid boid: this.essaim.membre){
            if (this.equals(boid)){
                continue;
            }
            Vector2D directionToVoisin= boid.position.getSub(this.position);
            Vector2D direction = this.vitesse.getSub(this.position);

            if(boid.position.distance(this.position)<=this.essaim.getDistanceVision()){
                double angle = Math.acos((direction.dot(directionToVoisin))/(direction.getNorme()*directionToVoisin.getNorme()));
                // Convertir l'angle en degrés
                angle = Math.toDegrees(angle);
                // Comparer avec l'angle de vision
                if (angle <= this.essaim.getAngleVision()){
                    result.add(boid);
                }
            }
        }
        return result;
    }

    /**
     * Retourne le vecteur de cohésion parmi les voisins du Boid.
     * @param listVoisin La liste des Boids voisins.
     * @return Le vecteur de cohésion.
     */
    private Vector2D getCohesion(ArrayList<Boid> listVoisin) {
        Vector2D result = new Vector2D();
        result.add(this.position);
        for (Boid boid : listVoisin){
            result.add(boid.position);
        }
        result.div(listVoisin.toArray().length);

        return (result.getSub(this.position)).getDiv(50);
    }

    /**
     * Retourne le vecteur de séparation parmi les voisins du Boid.
     * @param listVoisin La liste des Boids voisins.
     * @param seuil La distance de séparation.
     * @return Le vecteur de séparation.
     */
    private Vector2D getSeparation(ArrayList<Boid> listVoisin, double seuil) {
        Vector2D result=new Vector2D();
        result.add(this.vitesse);
        for (Boid boid : listVoisin){
            if (boid.position.distance(this.position)<=seuil){
                result.sub(boid.position.getSub(this.position));
            }
        }
        return result;
    }

    /**
     * Retourne le vecteur d'alignement parmi les voisins du Boid.
     * @param listVoisin La liste des Boids voisins.
     * @return Le vecteur d'alignement.
     */
    private Vector2D getAlignement(ArrayList<Boid> listVoisin) {
        Vector2D result = new Vector2D();
        for (Boid boid : listVoisin){
            result.add(boid.vitesse);
        }
        result.div(listVoisin.toArray().length);
        result.sub(this.vitesse);
        return result.getDiv(8);
    }

    /**
     * Limite la vitesse du Boid.
     * @param vitesse La vitesse à limiter.
     * @param vitMax La vitesse maximale autorisée.
     * @return La vitesse limitée.
     */
    public Vector2D limitVitess(Vector2D vitesse, int vitMax) {
        if((int)vitesse.getNorme()>vitMax){
            return vitesse.getNormaliser().getMult(vitMax);
        }
        return vitesse;
    }

    /**
     * Limite la position du Boid en fonction des dimensions de l'environnement.
     * @param Xmax La dimension maximale en X.
     * @param Ymax La dimension maximale en Y.
     */
    public void limitPosition(int Xmax, int Ymax) {
        if (this.position.x < 0) {
            this.position.x = Xmax;
        } else if (this.position.x > Xmax) {
            this.position.x = 0;
        }
        if (this.position.y < 0) {
            this.position.y = Ymax;
        } else if (this.position.y > Ymax) {
            this.position.y = 0;
        }
    }

    /**
     * Déplace le Boid en fonction des règles du simulateur Boids.
     * @param Xmax La dimension maximale en X.
     * @param Ymax La dimension maximale en Y.
     */
    public void move(int Xmax, int Ymax){
        Vector2D acceleration = new Vector2D();
        ArrayList<Boid> listVoisin = getVoisin();
        if(listVoisin.isEmpty()){
            this.position.add(this.vitesse);
            this.limitPosition(Xmax,Ymax);
        }
        acceleration.add(getAlignement(listVoisin));
        acceleration.add(getCohesion(listVoisin));
        acceleration.add(getSeparation(listVoisin,5));
        this.vitesse.add(acceleration);
        this.vitesse= limitVitess(this.vitesse,15);
        this.position.add(this.vitesse);
        this.limitPosition(Xmax,Ymax);
    }

    /**
     * Réinitialise la position et la vitesse du Boid à leurs valeurs initiales.
     */
    void reInit() {
        this.position.set(this.positionInit);
        this.vitesse.set(this.vitesseInit);
    }
}