import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class BrokenLine extends Geometry {
    private Deque<Point> points;

    public BrokenLine(String name, Point... initialPoints) {
        super(name);
        points = new ArrayDeque<>();
        Set<Point> uniquePoints = new HashSet<>(); // Utilisation d'un HashSet pour maintenir l'unicité des points

        // Vérifiez qu'il y a au moins deux points passés en argument
        if (initialPoints.length < 2) {
            throw new IllegalArgumentException("Une ligne brisée doit avoir au moins deux points.");
        }

        // Ajoutez les points initiaux à la deque et au HashSet (pour maintenir l'unicité)
        for (Point point : initialPoints) {
            points.addLast(point);
            uniquePoints.add(point);
        }
    }

    // Méthode pour ajouter un point en fin de séquence
    public void add(Point p) {
        points.addLast(p);
    }

    // Méthode pour supprimer le premier point de la ligne
    public void removeFirst() {
        if (points.size() > 2) {
            points.remove(0); // Suppression du premier point
        } else {
            // Gérer l'erreur lorsque la ligne n'a que deux points
            throw new IllegalStateException("Une ligne brisée doit avoir au moins deux points.");
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BrokenLine ").append(getName()).append(" : [");
        for (Point point : points) {
            builder.append(point.toString()).append(", ");
        }
        // Supprimer la virgule et l'espace supplémentaires à la fin
        if (points.size() > 0) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("]");
        return builder.toString();
    }

    // @Override
    // public Point getBarycenter() {
    //     // Calculer le barycentre des points qui constituent la ligne brisée
    //     int totalX = 0;
    //     int totalY = 0;
    //     for (Point point : points) {
    //         totalX += point.getX();
    //         totalY += point.getY();
    //     }
    //     int barycenterX = totalX / points.size();
    //     int barycenterY = totalY / points.size();
    //     return new Point(barycenterX, barycenterY, "Barycenter"); // Retourner un point représentant le barycentre
    // }

    @Override
    public Point getBarycenter() {
        // Utilisation d'un ensemble pour stocker les points uniques
        Set<Point> uniquePoints = new HashSet<>(points);

        // Calcul du barycentre à partir des points uniques
        int totalX = 0;
        int totalY = 0;
        for (Point point : uniquePoints) {
            System.out.println(point);
            totalX += point.getX();
            totalY += point.getY();
        }

        int barycenterX = totalX / uniquePoints.size();
        int barycenterY = totalY / uniquePoints.size();

        return new Point(barycenterX, barycenterY, "Barycenter"); // Retourner un point représentant le barycentre
    }

    // Méthode pour obtenir les points de la ligne
    public Deque<Point> getPoints() {
        return points;
    }

    @Override
    public void draw(SimpleDrawer simpleDrawer) {
        // Implémentez le code de dessin pour une ligne brisée ici
        Deque<Point> points = getPoints();
        Point prevPoint = null;

        for (Point point : points) {
            if (prevPoint != null) {
                simpleDrawer.drawLineSegment(prevPoint.getX(), prevPoint.getY(), point.getX(), point.getY());
            }
            prevPoint = point;
        }
    }
}
