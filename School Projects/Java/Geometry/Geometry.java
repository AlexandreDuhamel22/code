public abstract class Geometry implements Drawable {
    private String name;
    private static int autoGeometryCount = 0;

    public Geometry(String name) {
        if (name == null || name.isEmpty()) {
            this.name = "NameAuto" + autoGeometryCount;
            autoGeometryCount++;
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public abstract void draw(SimpleDrawer simpleDrawer);

    public abstract String toString();

    public abstract Point getBarycenter();
}
