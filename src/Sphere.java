public class Sphere {

    String id;
    Position r;
    double radius;
    String options = "";

    public static final double RADIUS = 2;
    double tolerance = 2 * RADIUS + 0.1;

    public Sphere(String id, Position r, int radius, String... options) {
        this.id = id;
        this.r = r;
        this.radius = radius;
        for (String o : options) {
            this.options = this.options+ " " + o;
        }
    }

    public Sphere(double x, double y, double z) {
        this.r = new Position(x,y,z);
        radius = RADIUS;
    }

    public boolean overlaps(Sphere other) {
        double x = this.r.x - other.r.x;
        double y = this.r.y - other.r.y;
        double z = this.r.z - other.r.z;
        return (Math.sqrt(x*x + y*y + z*z) < tolerance);
    }



    public String toString() {
        return String.format("%-10s %-10s sphere %-15s %d%s",
            "region",
            id,
            r.toString(),
            radius,
            options);
    }
}