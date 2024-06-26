package demo;

import java.io.File;

import org.testng.annotations.DataProvider;

/**
 * ExcelDataProvider
 */
public class ExcelDataProvider {

    @DataProvider(name = "excelData")
    public static Object[][] excelData(){
        String fileLocation = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Radha_qa_codeathon_week3.xlsx";System.out.println("Fetching excel file from "+fileLocation);
        return ExcelReaderUtil.readExcelData(fileLocation);

        //C:\CRIO\CRIO\CRIO-Code-A-Thon\Youtube\selenium-starter-2\src\test\resources\Radha_qa_codeathon_week3.xlsx
    }
    
}