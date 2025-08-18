package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public static void loadConfig() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/testdata/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file!", e);
        }
    }

    public static String getProperty(String key) {
    	if (properties == null) {
            loadConfig(); // <-- Ensure it's loaded before usage
        }
        return properties.getProperty(key);
    }

}
