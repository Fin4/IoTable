package org.iotable.core;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class Config {

    private static final Logger logger = Logger.getLogger(Config.class);

    private static Properties props = new Properties();

    public static void loadProperties() {
        try {
            props.load(Config.class.getClassLoader().getResourceAsStream("iotable.properties"));
            logger.info("Properties file successfully loaded");
        } catch (FileNotFoundException e) {
            logger.warn("Properties file not found... Using default properties", e);
            defaultProperties();
        } catch (IOException e) {
            logger.warn("Can't read properties file... Using default properties", e);
            defaultProperties();
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    private static void defaultProperties() {

        // workbook properties
        props.setProperty("sheet.di" , "DI");
        props.setProperty("sheet.ai" , "AI");
        props.setProperty("sheet.do" , "DO");
        props.setProperty("sheet.ao" , "AO");

        //unit properties
        props.setProperty("unit.map.number", "%num%");
        props.setProperty("unit.map.address", "%addr%");
        props.setProperty("unit.map.symbol", "%symbol%");
        props.setProperty("unit.map.desc", "%desc%");
        props.setProperty("unit.map.engUnits", "%eu%");

        // mechanisms properties
        props.setProperty("DG", "Z,KZ,C");
        props.setProperty("M", "M");
        props.setProperty("Ef", "Ef");
        props.setProperty("K", "K");


        //dg properties
        props.setProperty("dg.map.symbol", "%symbol%");
        props.setProperty("dg.map.desc", "%desc%");
        props.setProperty("dg.map.opened", "%opened%");
        props.setProperty("dg.map.closed", "%closed%");
        props.setProperty("dg.map.open", "%open%");
        props.setProperty("dg.map.close", "%close%");

        props.setProperty("dg.openedSymbol", ".+\\.1");
        props.setProperty("dg.closedSymbol", ".+\\.2");
        props.setProperty("dg.openSymbol", ".+\\.Y1");
        props.setProperty("dg.closeSymbol", ".+\\.Y2");
        props.setProperty("dg.nullUnit", "NULL");

    }
}
