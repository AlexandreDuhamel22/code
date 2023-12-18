import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SIG implements Drawable {
    private Map<String, Geometry> geometries;
    private List<String> insertionOrder;

    public SIG() {
        geometries = new HashMap<>();
        insertionOrder = new ArrayList<>();
    }

    public void add(Geometry geom) {
        String name = geom.getName();
        if (geometries.containsKey(name)) {
            throw new IllegalArgumentException("Une entité de même nom existe déjà dans le SIG.");
        }
        geometries.put(name, geom);
        insertionOrder.add(name);
    }

    public Geometry getGeometry(String name) {
        return geometries.get(name);
    }

    public Iterator<String> nameIterator() {
        return insertionOrder.iterator();
    }

    public Iterator<Geometry> geometryIterator() {
        List<Geometry> orderedGeometries = new ArrayList<>();
        for (String name : insertionOrder) {
            orderedGeometries.add(geometries.get(name));
        }
        return orderedGeometries.iterator();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Contenu du SIG :\n");
        for (String name : insertionOrder) {
            builder.append(geometries.get(name).toString()).append("\n");
        }
        return builder.toString();
    }

    public ComplexGeometry getComplexGeometry(String name) {
        Geometry geometry = geometries.get(name);
        if (geometry instanceof ComplexGeometry) {
            return (ComplexGeometry) geometry;
        }
        return null; // Retourne null si l'entité n'est pas complexe
    }
    
    public void addComplexGeometry(ComplexGeometry complexGeometry) {
        String name = complexGeometry.getName();
        if (geometries.containsKey(name)) {
            throw new IllegalArgumentException("Une entité de même nom existe déjà dans le SIG.");
        }
        geometries.put(name, complexGeometry);
        insertionOrder.add(name);
    }

    @Override
    public void draw(SimpleDrawer simpleDrawer) {
        // Dessine toutes les entités géométriques du SIG en utilisant le SimpleDrawer
        Iterator<Geometry> geometryIterator = geometryIterator();
        while (geometryIterator.hasNext()) {
            Geometry geom = geometryIterator.next();
            geom.draw(simpleDrawer);
    }
}

}
