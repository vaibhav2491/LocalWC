package soprasteria.india.jira.projectstatus;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ToolConfigurationProperties {

	/**
	 * ! Constant declaration for fileSeperator test
	 */
	public static String fileSeperator = System.getProperty("file.separator");
	
	public static String  inputExcelFilePath ;
	
	public static String  excelColumns ;
	
	public static String  requiredColumns ;
	
	public static boolean isCriticalityRequired;
	
	public static String  defaultWorkLogColor ;
	
	public static String  criticalityRating ;
	
	public static String  criticalityColour ;

	static {

		BufferedReader reader = null;
		Properties toolProperties = new Properties();

		try {

			String propertyFilePath = "C:\\Program Files (x86)\\Jenkins\\workspace\\JiraToolRunner\\configuration\\properties\\ToolConfiguration.properties";

			reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(propertyFilePath), StandardCharsets.UTF_8));

			toolProperties.load(reader);
			
			inputExcelFilePath = toolProperties.getProperty("inputExcelFilePath");
			excelColumns = toolProperties.getProperty("excelColumns");
			requiredColumns = toolProperties.getProperty("requiredColumns");
			isCriticalityRequired = Boolean.parseBoolean(toolProperties.getProperty("isCriticalityRequired"));
			defaultWorkLogColor = toolProperties.getProperty("defaultWorkLogColor");
			criticalityRating = toolProperties.getProperty("criticalityRating");
			criticalityColour = toolProperties.getProperty("criticalityColour");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
