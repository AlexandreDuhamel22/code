import java.util.ArrayList;
import java.util.List;

public abstract class ComplexGeometry extends Geometry {
    protected List<Geometry> subGeometries;

    public ComplexGeometry(String name) {
        super(name);
        subGeometries = new ArrayList<>();
    }

    public void addSubGeometry(Geometry geometry) {
        subGeometries.add(geometry);
    }

    public void removeSubGeometry(Geometry geometry) {
        subGeometries.remove(geometry);
    }

    public List<Geometry> getSubGeometries() {
        return subGeometries;
    }
}
