import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        Point point = new Point(12, 14, "c'est tout");
        System.out.println(point.toString());
        System.out.println("Barycentre : " + point.getBarycenter().toString());

        Point point1 = new Point(10, 20, "");
        Point point2 = new Point(30, 40, null);

        System.out.println(point1.getName());
        System.out.println(point2.getName());


        Point point3 = new Point(0, 0, "");
        Point point4 = new Point(0, 100, "");
        Point point5 = new Point(100, 100, "");
        Point point6 = new Point(100, 0, "");
        Point point7 = new Point(0, 0, "");

        // Création de la ligne brisée passant par ces points
        BrokenLine line = new BrokenLine("MaLigne", point3, point4);

        // Ajout des autres points à la ligne brisée
        line.add(point5);
        line.add(point6);
        line.add(point7);

        // Affichage de la ligne brisée
        System.out.println(line.toString());

        // Calcul et affichage du barycentre de la ligne brisée
        Point barycenter = line.getBarycenter();
        System.out.println("Barycentre : " + barycenter.toString());

        SIG sig = new SIG();

        // Ajout des entités géométriques au SIG
        sig.add(point1);
        sig.add(point2);
        sig.add(line);

        // Créez un GeometricPainter pour afficher les entités géométriques
        GeometricPainter painter = new GeometricPainter(new Drawable() {
            @Override
            public void draw(SimpleDrawer simpleDrawer) {
                Iterator<Geometry> geometryIterator = sig.geometryIterator();
                while (geometryIterator.hasNext()) {
                    Geometry geom = geometryIterator.next();
                    geom.draw(simpleDrawer);
                }
            }
        });

        // Appelez repaint() pour déclencher l'affichage
        painter.repaint();
    }
}
