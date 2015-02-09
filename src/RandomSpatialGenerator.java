import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sayurimila on 2/8/15.
 */
public class RandomSpatialGenerator {

    public static List<Sphere> generate(double minx,
                                        double maxx,
                                        double miny,
                                        double maxy,
                                        double minz,
                                        double maxz,
                                        int count) {
        int counter = 0;
        List<Sphere> list = new ArrayList<>(count);
        Random rand = new Random(1);
        while (counter < count) {
            double x = minx + rand.nextDouble()*(maxx - minx);
            double y = miny + rand.nextDouble()*(maxy - miny);
            double z = minz + rand.nextDouble()*(maxz - minz);
            Sphere s = new Sphere(x, y, z);
            if(list.stream().allMatch(other -> !other.overlaps(s))) {
                list.add(s);
                counter++;
            }
        }
        return list;
    }

    public static void main(String[] args) {

        List<Sphere> list = RandomSpatialGenerator.generate(0,10,0,10,0,10,4);
        for(Sphere s : list) {
            //do something with s
            //s.x;
        }
    }
}

