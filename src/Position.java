public class Position {
    
    int x,y,z;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString() {
        return String.format("%3d %3d %3d",x,y,z);
    }
}