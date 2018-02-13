package soprasteria.india.jira.projectstatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;

public class ToolHelper {
	
	protected static String readExcelColumsFromFile(Iterator<Cell> cellIterator) {
		String excelColumnFromFile = null;
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			String cellValue = null;

			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				System.out.print(cellValue);
				break;

			case Cell.CELL_TYPE_NUMERIC:
				cellValue = Double.toString(cell.getNumericCellValue());
				System.out.print(cellValue);
				break;
			}

			if (null != cellValue) {
				if (null == excelColumnFromFile) {
					excelColumnFromFile = cellValue;
				} else {
					excelColumnFromFile = excelColumnFromFile.concat(",").concat(cellValue);
				}
			}
			System.out.print(" - ");
		}
		return excelColumnFromFile;

	}
	
	protected static Map<String, String> mapCritingRatingAndColor(String  criticalityRating, String  criticalityColour){
		String[] rating = criticalityRating.split(","); 
		String[] color = criticalityColour.split(",");
		Map<String, String> criticalityRatingColorMap = new HashMap<String, String>();
		if(rating.length == color.length){
			for(int i=0; i<rating.length; i++){
				criticalityRatingColorMap.put(rating[i], color[i]);
			}
		}
		return criticalityRatingColorMap;
		
	}

	protected static void writeTaskPerResourceGraph(CreateJS taskPerResourceChartWriter,
			Map<String, Integer> taskPerresource) throws Exception {
		
		taskPerResourceChartWriter = new CreateJS("C:\\Program Files (x86)\\Jenkins\\workspace\\JiraToolRunner\\js\\amChartTaskPerResource.js");

		taskPerResourceChartWriter.write(
				"function amTaskPerResourceChart(themeType){ var chart = AmCharts.makeChart(\"chartdivResources\",{\"type\": \"serial\", \"theme\": themeType, \"dataProvider\": [");
		
		//dataProvider
		int i=0;
		for (Map.Entry<String, Integer> entry : taskPerresource.entrySet()) {
			taskPerResourceChartWriter.write("{   \"name\": \"" +entry.getKey() + "\",      \"points\":" + entry.getValue() + ",     \"color\": \"#7F8DA9\",     \"bullet\": \"https://www.amcharts.com/lib/images/faces/A04.png\"    }");
			if(i != taskPerresource.size()){
				taskPerResourceChartWriter.write(",");
			}
		}	
		//dataProvider
		
		taskPerResourceChartWriter.write(
				"], \"valueAxes\": [{  \"maximum\": 25, \"minimum\": 0, \"axisAlpha\": 0,  \"dashLength\": 4, \"position\": \"left\" }],\"startDuration\": 1, \"graphs\": [{   \"balloonText\": \"<span style='font-size:13px;'>[[category]]: <b>[[value]]</b></span>\",  \"bulletOffset\": 10,   \"bulletSize\": 52,  \"colorField\": \"color\",  \"cornerRadiusTop\": 8, \"customBulletField\": \"bullet\",   \"fillAlphas\": 0.8,   \"lineAlpha\": 0, \"type\": \"column\", \"valueField\": \"points\"   }],\"marginTop\": 0,\"marginRight\": 0,\"marginLeft\": 0,\"marginBottom\": 0,    \"autoMargins\": false,    \"categoryField\": \"name\",    \"categoryAxis\": {        \"axisAlpha\": 0,        \"gridAlpha\": 0,        \"inside\": true,        \"tickLength\": 0    },    \"export\": {    	\"enabled\": true     }});}");
		
	}
	
	protected static HashMap<String, Integer> getColumnOrder(String excelColumns) {
        HashMap<String, Integer> columnOrderMap = new HashMap<>();
        String[] columnValues = excelColumns.split(",");
        for(int i=0;i<columnValues.length;i++){
               columnOrderMap.put(columnValues[i], i+1);
        }
        return columnOrderMap;
 
        
 }

	protected static boolean checkRequiredColumns(HashMap<String, Integer> columnOrderMap, String requiredColumns) {
		if (requiredColumns != null) {
			String[] requiredAttributes = requiredColumns.split(",");
			for (int i = 0; i < requiredAttributes.length; i++) {
				if (("").equals(columnOrderMap.get(requiredAttributes[i]))
						|| columnOrderMap.get(requiredAttributes[i]) == null) {
							return false;
				}
			}
		}
		return true;
	}


}
