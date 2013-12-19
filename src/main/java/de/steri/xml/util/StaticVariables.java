package de.steri.xml.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaticVariables {
	
	 private static final Logger log = Logger.getLogger( StaticVariables.class.getName() );
	
	public static String DIRECTORY_PATH; 
	public static String SCHEMA_PATH;
	public static String FILE;
	public static String FILE_NAME;
	public static String FILE_SUFFIX;
	
	static {
		Properties prop = new Properties();
		 
    	try {
               //load a properties file
    		prop.load(StaticVariables.class.getClassLoader().getResourceAsStream("file.properties"));
    		DIRECTORY_PATH=prop.getProperty("directory");
    		FILE_NAME=prop.getProperty("fileName");
    		FILE_SUFFIX=prop.getProperty("fileSuffix");
    		FILE= DIRECTORY_PATH  + "/"  + FILE_NAME + FILE_SUFFIX;
    		SCHEMA_PATH=prop.getProperty("schemaPath");
    		log.log(Level.INFO, FILE);
    		 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }		
	}

}
