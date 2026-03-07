package org.apache.camel.learn.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {
    private String environment;

    public PropertiesConfig() {
        Properties props = new Properties();
        String profile = System.getProperty("profile", "");

        String fileName = "application.properties";
        if (!profile.isEmpty()) {
            fileName = "application-" + profile + ".properties";
        }

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            props.load(input);

            // read properties
            environment = props.getProperty("environment");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getEnvironment() {
        return environment;
    }
}
