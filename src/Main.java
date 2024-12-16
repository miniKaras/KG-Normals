import vsu.kg12.karasev_a_e.model.Model;
import vsu.kg12.karasev_a_e.FindNormals;
import vsu.kg12.karasev_a_e.objreader.ObjReader;
import vsu.kg12.karasev_a_e.Vector3f;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            String fileContent = Files.readString(Path.of("C:\\prog\\ComputerGraphicsNormals\\src\\ImageToStl.com_bad_piggies_2_ross.obj"));

            Model model = ObjReader.read(fileContent);

            ArrayList<Vector3f> normals = FindNormals.findNormals(model);

            System.out.println("Calculated Normals:");
            for (Vector3f normal : normals) {
                System.out.println(normal);
            }
        } catch (IOException e) {
            System.err.println("Failed to read the file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
