import java.util.Objects;

public class Point extends Geometry {
    private int x;
    private int y;

    public Point(int x, int y, String name) {
        super(name);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point " + getName() + " : (" + x + ", " + y + ")";
    }

    @Override
    public Point getBarycenter() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public void draw(SimpleDrawer simpleDrawer) {
        // Implémentez le code de dessin pour un point ici
        int x = getX();
        int y = getY();
        simpleDrawer.drawPoint(x, y);
    }
}
