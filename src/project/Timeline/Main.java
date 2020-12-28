package project.Timeline;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Main {
    private static HSSFWorkbook xlWBook;
    private static HSSFSheet xlSheet;
    private static HSSFRow xlRow;
    private static HSSFCell xlCell;

    public static String[][] getTable(){
        String[][] excelData = new String[51][61];;
        try {
            FileInputStream xlFile = new FileInputStream("C:\\Users\\Eu\\IdeaProjects\\Timeline\\src\\project\\Timeline\\Population.xls");

            xlWBook = new HSSFWorkbook(xlFile);// Access the required test data sheet
            xlSheet = xlWBook.getSheet("Top50");

            int noOfRows = xlSheet.getPhysicalNumberOfRows();// gives row count in sheet

            xlRow = xlSheet.getRow(0);// gives column count in sheet

            int noOfColumns = xlRow.getLastCellNum();
            for(int i=2;i<=60;i++)excelData[0][i]=String.valueOf(1958+i);
            // r - row c- column
            for (int r = 1; r < noOfRows; r++) {
                for (int c = 0; c < noOfColumns; c++) {
                    xlRow = xlSheet.getRow(r);
                    xlCell = xlRow.getCell(c);

                    if (c<=1)
                        excelData[r][c] = xlCell.getStringCellValue();
                    else
                        excelData[r][c]=String.valueOf(Double.valueOf(xlCell.getNumericCellValue()).longValue());

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelData;
    }

    public static void main(String[] args) {
        String[][] excelData = getTable();
        new TimelinePanel(excelData, 6);
    }
}
