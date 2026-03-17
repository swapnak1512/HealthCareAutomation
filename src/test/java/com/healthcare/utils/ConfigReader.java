package com.healthcare.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTestDataPath() {
        return prop.getProperty("testdata.path");
    	
    }
    
    
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

     
    public static String getEnvironmentUrl() {

        String env = prop.getProperty("env");

        switch(env.toLowerCase()) {

            case "qa":
                return prop.getProperty("qa.url");

            case "uat":
                return prop.getProperty("uat.url");

            case "prod":
                return prop.getProperty("prod.url");

            default:
                throw new RuntimeException("Invalid environment: " + env);
        }
        
        
    }
    
    
}