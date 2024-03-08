package main.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(("src/main/config/myProject.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
