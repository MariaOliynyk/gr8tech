package com.gr8tech.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {
        private static final Properties properties = new Properties();

        static {
            try (InputStream input = ConfigProperties.class.getClassLoader().getResourceAsStream("properties/env/qa.properties")) {
                if (input == null) {
                    System.out.println("Unable to find config.properties");
                    throw new RuntimeException("Unable to find config.properties");
                }
                properties.load(input);
            } catch (IOException ex) {
                throw new RuntimeException("Error loading properties file", ex);
            }
        }

        public static String getProperty(String key) {
            return properties.getProperty(key);
        }
    }

