import java.io.*;
import java.io.File;
import java.util.List;
import java.util.function.*;
public class Driver {

    static String prefix = "mySPH";
    static int radius = 2;
    static String[] options = {"side", "in", "units", "box"};
    static BiFunction<Integer, Integer, Position> gen = (t, z) -> new Position(t * 9 - 8, z * 9 - 18, 18);

    public static void main(String[] args) throws Exception {
        PrintStream writer = createFile("out.txt");
        writer.println("#===BEGIN REGIONS===");
        int createdSpheres = createNonOverlappingSpheres(writer);
        writer.println("#===BEGIN ATOMS===");
        createAtoms(writer, createdSpheres);
	   writer.println("#===BEGIN MASS===");
	   createMass(writer, createdSpheres);
	   writer.println("#===BEGIN GROUP===");
	   createGroup(writer, createdSpheres);

	   createUnion(writer, createdSpheres);

	   writer.println("#===BEGIN FIX===");
	   createFix(writer, createdSpheres);

        
    }

    static PrintStream createFile(String name) {
        try {
            return new PrintStream(new File(name));
        } catch (Exception e) {
            return null;
        }
    }

    static int createNonOverlappingSpheres(PrintStream writer) {
        int count = 25;
        List<Sphere> list = RandomSpatialGenerator.generate(-20, 40, -50, 50, 15, 18, count);
        for (int i = 0; i < count; i++) {
            list.get(i).setId(prefix + i);
            list.get(i).setOptions(options);
            writer.println(list.get(i).toString());
        }
        return count;
    }

    static int createSpheres(PrintStream writer) {
        int counter = 0;
        for (int t = 0; t < 5; t++) {
            for(int z = 0; z < 5; z++) {
                Sphere e = new Sphere(prefix + counter++, gen.apply(t,z), radius, options);
                writer.println(e.toString());
            }
        }
        return counter;
    }

    static void createAtoms(PrintStream writer, int counter) {
        for(int i = 0; i < counter; i++) {
            writer.println(String.format("%-15s %d region %s","create_atoms", i+2, prefix+i));
        }
    }

static void createMass(PrintStream writer, int counter) {
        for(int i = 0; i < counter; i++) {
            writer.println(String.format("mass %d 1",i+2));
        }
    }

static void createGroup(PrintStream writer, int counter) {
        for(int i = 0; i < counter; i++) {
            writer.println(String.format("group %s type %d","s"+(i+2),(i+2)));
        }
    }


static void createUnion(PrintStream writer, int counter) {
	writer.print(String.format("group mix union "));
        for(int i = 0; i < counter; i++) {
            writer.print(String.format("%s ","s"+(i+2)));
        }
    writer.println();
    }

static void createFix(PrintStream writer, int counter) {
        for(int i = 0; i < counter; i++) {
            writer.println(String.format("fix %d %-10s rigid single",1000+i,"s"+(i+2)));
        }
    }

}