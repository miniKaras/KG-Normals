package vsu.kg12.karasev_a_e.ObjWriter;


import vsu.kg12.karasev_a_e.model.Model;

import java.io.IOException;

public interface ObjWriter {
    void write(Model model, String fileName) throws IOException;
}
