package org.iotable.core;


import java.io.FileInputStream;

import java.util.Properties;
import org.apache.log4j.Logger;

public class Config {

    private static final Logger logger = Logger.getLogger(Config.class);

    private static Properties props;

    static {
        try {
            props.load(new FileInputStream("resources/IoTable.properties"));
        } catch (Exception e) {
            logger.warn("Properties file not found... Using default properties");
            defaultProperties();
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    private static void defaultProperties() {

        props = new Properties();

        // workbook properties
        props.setProperty("sheet.di" , "DI");
        props.setProperty("sheet.ai" , "AI");
        props.setProperty("sheet.do" , "DO");
        props.setProperty("sheet.ao" , "AO");

        //code properties
        props.setProperty("number", "%number%");
        props.setProperty("address", "%address%");
        props.setProperty("symbol", "%symbol%");
        props.setProperty("description", "%description%");
        props.setProperty("engUnits", "%engUnits%");

        // mechanisms properties

        //dg properties
        props.setProperty("dg.openedSymbol", ".+\\.1");
        props.setProperty("dg.closedSymbol", ".+\\.2");
        props.setProperty("dg.openSymbol", ".+\\.Y1");
        props.setProperty("dg.closeSymbol", ".+\\.Y2");

    }
}
