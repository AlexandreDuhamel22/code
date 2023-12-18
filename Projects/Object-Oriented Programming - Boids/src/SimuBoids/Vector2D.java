package SimuBoids;

import java.lang.Math;

/**
 * Vector2D --- Classe représentant un vecteur 2D.
 */
public class Vector2D {
    // Coordonnée x du vecteur.
    public double x;

    // Coordonnée y du vecteur.
    public double y;

    @Override
    public boolean equals(Object o) {
        // Vérifie si deux vecteurs sont égaux.
        if (this == o) return true;
        if (!(o instanceof Vector2D vector2D)) return false;
        return Double.compare(x, vector2D.x) == 0 && Double.compare(y, vector2D.y) == 0;
    }

    /**
     * Constructeur par défaut initialisant le vecteur à l'origine (0,0).
     */
    public Vector2D() {
        set(0,0);
    }

    /**
     * Constructeur initialisant le vecteur avec les coordonnées spécifiées.
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructeur initialisant le vecteur avec les coordonnées d'un autre vecteur.
     */
    public Vector2D(Vector2D v) {
        set(v);
    }

    /**
     * Méthode qui définit les coordonnées du vecteur.
     */
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Méthode qui définit les coordonnées du vecteur en utilisant un autre vecteur.
     */
    public void set(Vector2D vecteur) {
        this.x = vecteur.x;
        this.y = vecteur.y;
    }

    /**
     * Méthode qui calcule la distance entre deux vecteurs.
     */
    public double distance(Vector2D vecteur){
        double X = vecteur.x - this.x;
        double Y = vecteur.y - this.y;
        return Math.sqrt(X * X - Y * Y);
    }

    /**
     * Méthode qui additionne un autre vecteur à ce vecteur.
     */
    public void add(Vector2D vecteur){
        this.x += vecteur.x;
        this.y += vecteur.y;
    }

    /**
     * Méthode qui Soustrait un autre vecteur à ce vecteur.
     */
    public void sub(Vector2D vecteur){
        this.x -= vecteur.x;
        this.y -= vecteur.y;
    }

    /**
     * Méthode qui permet d'obtenir un nouveau vecteur résultant de la soustraction d'un autre vecteur à ce vecteur.
     */
    public Vector2D getSub(Vector2D vecteur){
        Vector2D result = new Vector2D();
        result.set(this);
        result.sub(vecteur);
        return result;
    }

    /**
     * Méthode qui permet d'obtenir un nouveau vecteur résultant de l'addition d'un autre vecteur à ce vecteur.
     */
    public Vector2D getAdd(Vector2D vecteur){
        Vector2D result = new Vector2D();
        result.set(this);
        result.add(vecteur);
        return result;
    }

    /**
     * Méthode qui multiplie le vecteur par un scalaire.
     */
    public void mult(double scalaire){
        this.x *= scalaire;
        this.y *= scalaire;
    }

    /**
     * Méthode qui permet d'obtenir un nouveau vecteur résultant de la multiplication du vecteur par un scalaire.
     */
    public Vector2D getMult(double scalaire){
        Vector2D result = new Vector2D();
        result.set(this);
        result.mult(scalaire);
        return result;
    }

    /**
     * Méthode qui divise le vecteur par un scalaire.
     */
    public void div(double scalaire){
        // Vérifie si le scalaire est proche de zéro pour éviter la division par zéro.
        if (scalaire == 0){
            return;
        }
        this.x /= scalaire;
        this.y /= scalaire;
    }

    /**
     * Méthode qui permet d'obtenir un nouveau vecteur résultant de la division du vecteur par un scalaire.
     */
    public Vector2D getDiv(double scalaire){
        Vector2D result = new Vector2D();
        result.set(this);
        result.div(scalaire);
        return result;
    }

    /**
     * Méthode qui calcule le produit scalaire avec un autre vecteur.
     */
    public double dot(Vector2D vecteur){
        return this.x * vecteur.x + this.y * vecteur.y;
    }

    /**
     * Méthode qui permet d'obtenir la norme du vecteur.
     */
    public double getNorme(){
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Méthode qui vérifie si un nombre est proche de zéro, utilisé pour éviter la division par zéro.
     */
    private void checkZero(double n) {
        if (n >= -0.00001 && n <= 0.00001) {
            throw new ArithmeticException("Division par zéro");
        }
    }

    /**
     * Méthode qui permet d'obtenir un nouveau vecteur normalisé à partir de ce vecteur. En cas de division par zéro, renvoie un vecteur nul.
     */
    public Vector2D getNormaliser(){
        double norm = this.getNorme();
        try {
            this.checkZero(norm);
            return new Vector2D(this.x / norm, this.y / norm);
        } catch (ArithmeticException e) {
            return new Vector2D();
        }
    }
}