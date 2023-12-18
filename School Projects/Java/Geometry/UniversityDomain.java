import java.util.List;

public class UniversityDomain extends ComplexGeometry {
    public UniversityDomain(String name) {
        super(name);
    }
    
    @Override
    public void draw(SimpleDrawer simpleDrawer) {
        // Draw the UniversityDomain (if needed)
        // Example: simpleDrawer.drawRectangle(x, y, width, height);

        // Iterate over sub-geometries and draw them
        List<Geometry> subGeometries = getSubGeometries();
        for (Geometry subGeometry : subGeometries) {
            subGeometry.draw(simpleDrawer);
        }
    }
}
