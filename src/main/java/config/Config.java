package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static helper.CricketSimulatorConstants.CONFIG_PATH;

public class Config {
    Properties properties;

    public Properties loadProperties() throws IOException {
        InputStream input = new FileInputStream(CONFIG_PATH);
        properties = new Properties();
        properties.load(input);
        return properties;
    }
}
