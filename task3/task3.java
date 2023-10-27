import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main3 {
    final static String VALUE = "value";
    final static String VALUES = "values";
    final static String ID = "id";
    final static String TESTS = "tests";
    final static String REPORT_FILE_NAME = "report.json";
    final static String FAILED = "failed";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Передайте 2 файла tests.json и values.json в виде аргументов");
            return;
        }

        String testsFilePath = args[0];
        String valuesFilePath = args[1];
        try (FileReader fileReaderTest = new FileReader(testsFilePath);
             FileReader fileReaderValue = new FileReader(valuesFilePath)) {
            try {

                JsonArray testsArray = JsonParser.parseReader(fileReaderTest).getAsJsonObject().get(TESTS).getAsJsonArray();
                JsonObject valuesObject = JsonParser.parseReader(fileReaderValue).getAsJsonObject();
                JsonArray valuesArray = valuesObject.get(VALUES).getAsJsonArray();

                Map<Integer, String> hashMap = new HashMap<>();
                for (JsonElement valueElement : valuesArray) {
                    JsonObject value = (JsonObject) valueElement;
                    int id = value.get(ID).getAsInt();
                    String result = value.get(VALUE).getAsString();
                    hashMap.put(id, result);
                }
                putTestInStructure(testsArray, hashMap, null);

                JsonObject resJson = new JsonObject();
                resJson.add(TESTS, testsArray);
                FileWriter fileWriter = new FileWriter(REPORT_FILE_NAME);
                fileWriter.write(resJson.toString());
                fileWriter.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void putTestInStructure(JsonArray arrayTests, Map<Integer, String> hashMap, Integer mainId) {
        for (JsonElement jsonElementTest : arrayTests) {
            JsonObject jsonObjectTest = (JsonObject) jsonElementTest;

            if (jsonObjectTest.get(VALUES) != null) {
                JsonArray subArrayTests = jsonObjectTest.getAsJsonArray(VALUES);
                putTestInStructure(subArrayTests, hashMap, jsonObjectTest.get(ID).getAsInt());
            }

            String result = hashMap.get(jsonObjectTest.get(ID).getAsInt());
            if (jsonObjectTest.get(VALUE) != null) {
                jsonObjectTest.add(VALUE, JsonParser.parseString((result == null) ? "" : result));
            }

            if (mainId != null)
                if (!Objects.equals(hashMap.get(mainId), FAILED))
                    hashMap.put(mainId, result);
        }


    }


}





