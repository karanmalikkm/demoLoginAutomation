package commonLibs.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;
    private FileInputStream fileInputStream;
    private String configFilePath = "src/main/resources/config.properties";

    public ConfigReader() {
        properties = new Properties();
        try {
            fileInputStream = new FileInputStream(configFilePath);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
