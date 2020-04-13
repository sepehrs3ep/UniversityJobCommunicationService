package ir.khu.jaobshaar.service.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadExcelData {

    public static ArrayList<ArrayList<String>> importExcelData(String url) {
        ArrayList<ArrayList<String>> studentData = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(url));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                ArrayList<String> studentDetail = new ArrayList<>();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            studentDetail.add(new Double(cell.getNumericCellValue()).intValue() + "");
                            break;
                        case STRING:
                            studentDetail.add(cell.getStringCellValue());
                            break;
                    }
                }
                studentData.add(studentDetail);
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentData;
    }
}
