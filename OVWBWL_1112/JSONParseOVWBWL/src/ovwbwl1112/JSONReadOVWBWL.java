package ovwbwl1112;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;


public class JSONReadOVWBWL {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("orarendOVWBWL.json")){

            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(reader);

            JSONObject root = (JSONObject) object.get("orarend");
            JSONArray arr = (JSONArray) root.get("ora");

            System.out.println("Mérnökinformatikus órarend 2024/25 I. félév:\n");

            for (int i = 0; i < arr.size(); i++) {
                JSONObject lesson = (JSONObject) arr.get(i);
                JSONObject time = (JSONObject) lesson.get("idopont");
                System.out.println("\nTárgy: " + lesson.get("targy"));
                System.out.println("Időpont: " + time.get("nap") + " " + time.get("tol") + "-" + time.get("ig"));
                System.out.println("Helyszín: " + lesson.get("helyszin"));
                System.out.println("Oktató: " + lesson.get("oktato"));
                System.out.println("Szak: " + lesson.get("szak"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
