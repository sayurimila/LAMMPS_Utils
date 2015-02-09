/**
 * Created by sayurimila on 2/8/15.
 */
public class Sphere {

    public final double x;
    public final double y;
    public final double z;

    public static final double RADIUS = 2;
    double tolerance = 2 * RADIUS + 0.1;
    public Sphere(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean overlaps(Sphere other) {
        double x = this.x - other.x;
        double y = this.y - other.y;
        double z = this.z - other.z;
        return (Math.sqrt(x*x + y*y + z*z) < tolerance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;

        if (Double.compare(sphere.x, x) != 0) return false;
        if (Double.compare(sphere.y, y) != 0) return false;
        if (Double.compare(sphere.z, z) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
