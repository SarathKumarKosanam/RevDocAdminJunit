package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static void loadProperties() throws Exception {
        if (properties == null) {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/testdata/config.properties");
            properties.load(fis);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
