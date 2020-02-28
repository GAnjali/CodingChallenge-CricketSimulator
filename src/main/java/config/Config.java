package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static helper.CricketSimulatorConstants.CONFIG_PATH;

public class Config {
    Properties properties;

    public Config() {
        properties = new Properties();
    }

    public void loadProperties() throws IOException {
        InputStream input = new FileInputStream(CONFIG_PATH);
        properties.load(input);
    }

    public String getValue(String field) {
        return properties.getProperty(field);
    }
}
