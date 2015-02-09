public class Sphere2 {

    String id;
    Position r;
    int radius;
    String options = "";

    public Sphere(String id, Position r, int radius, String... options) {
        this.id = id;
        this.r = r;
        this.radius = radius;
        for (String o : options) {
            this.options = this.options+ " " + o;
        }
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