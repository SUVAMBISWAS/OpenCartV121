package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	String path="./testData/OpnCartData.xlsx";
	
	@DataProvider(name="LoginLogoutDdt")
	public String [] [] provideData() throws IOException {
		
		ExcelUtility exc= new ExcelUtility(path);
		int totalRowCount = exc.getRowCount("Sheet1");
		int totalCellCount = exc.getCellCount("Sheet1", totalRowCount);
		String data [] []= new String [totalRowCount] [totalCellCount];
		for (int i=1;i<=totalRowCount;i++) {
			for (int j=0;j<totalCellCount;j++) {
				data[i-1][j]=exc.getData("Sheet1", i, j);
				
			}
			
		}
		return data;
		
		
		
		
	}

}
