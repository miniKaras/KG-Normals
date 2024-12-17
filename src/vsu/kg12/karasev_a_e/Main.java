package vsu.kg12.karasev_a_e;

import vsu.kg12.karasev_a_e.model.Model;
import vsu.kg12.karasev_a_e.objreader.ObjReader;
import vsu.kg12.karasev_a_e.ObjWriter.ObjWriterClass;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileContent = Files.readString(Path.of("C:\\prog\\ComputerGraphicsNormals\\src\\vsu\\kg12\\karasev_a_e\\models\\WrapBody.obj"));

        Model model = ObjReader.read(fileContent);
        ObjWriterClass writerClass = new ObjWriterClass();
        FindNormals.findNormals(model);

        writerClass.write(model,"C:\\prog\\ComputerGraphicsNormals\\src\\vsu\\kg12\\karasev_a_e\\models\\Test.obj");
    }
}
