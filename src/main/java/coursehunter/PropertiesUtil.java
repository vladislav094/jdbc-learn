package coursehunter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (var fileInputStream = new FileInputStream("resources/application.properties")) {
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertiesUtil() {}
}
