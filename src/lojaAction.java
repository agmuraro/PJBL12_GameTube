package src;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class lojaAction {
    public static void comprarGame(JSONObject session, Game game){
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get("src/usuarios.json")));
            JSONArray jsonArray;
            jsonArray = new JSONArray(fileContent);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (session.getString("name").equals(jsonObject.getString("name"))) {

                    JSONArray currentBiblioteca = jsonObject.getJSONArray("biblioteca");
                    JSONObject addingGame = new JSONObject();
                    addingGame.put("nome", game.getName());
                    addingGame.put("descricao", game.getDescricao());
                    currentBiblioteca.put(addingGame);
                    jsonObject.put("biblioteca", currentBiblioteca);

                    Files.write(Paths.get("src/usuarios.json"), jsonArray.toString().getBytes());

                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    };
}
