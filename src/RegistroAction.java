package src;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import src.Usuario;

public class RegistroAction {
    public static void registrarUsuario(Usuario user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", user.getName());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("senha", user.getPassword());
        jsonObject.put("username", user.getUsername());

        String filePath = "src/usuarios.json";

        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray;
            jsonArray = new JSONArray(fileContent);
            jsonArray.put(jsonObject);

            FileWriter escrever = new FileWriter(filePath);

            escrever.write(jsonArray.toString());

            escrever.close();

            System.out.println("Data written to the JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
