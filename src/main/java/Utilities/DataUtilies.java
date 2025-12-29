package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtilies {
    private static final String TEST_DATA_PATH = "src/test/resources/TestData/";

    public static String getJsonData(String fileName, String field) {//file + field

        FileReader reader = null;
        try {
            //Define obj of file reader
            reader = new FileReader(TEST_DATA_PATH + fileName + ".json");
            //Parse the Json dir into JsonElement
            JsonElement jsonElement = JsonParser.parseReader(reader);
            LogsUtils.info("File: " + fileName + ".json" + " Opened");
            LogsUtils.info("Data Received: " + jsonElement.getAsJsonObject().get(field).getAsString());
            return jsonElement.getAsJsonObject().get(field).getAsString();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    LogsUtils.info("File closed");// close reader
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static String getPropertValue(String fileName, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(TEST_DATA_PATH + fileName + ".properties"));
        return properties.getProperty(key);
    }

    public static String getYamlData(String fileName, String key) {
        try (FileInputStream fis = new FileInputStream(TEST_DATA_PATH + fileName + ".yaml")) {
            Yaml yaml = new Yaml();
            // Load YAML as Map (assuming simple top-level keys)
            java.util.Map<String, Object> data = yaml.load(fis);
            LogsUtils.info("File: " + fileName + ".yaml" + " Opened");

            if (data != null && data.containsKey(key)) {
                LogsUtils.info("Data: " + data.get(key) + " read from Yaml file");
                return String.valueOf(data.get(key)); // Convert to String
            } else {
                System.err.println("⚠️ Key '" + key + "' not found in " + fileName + ".yaml");
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
