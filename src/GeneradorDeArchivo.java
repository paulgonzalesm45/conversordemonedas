import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivo {
    public <Texto> void guardarJson(Texto texto) throws IOException {
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura=new FileWriter("archivo"+".json");
       escritura.write(gson.toJson(texto));
       escritura.close();
    }
}
