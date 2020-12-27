package project.Timeline;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class GetExcelData {
    private static HSSFWorkbook xlWBook;
    private static HSSFSheet xlSheet;
    private static HSSFRow xlRow;
    private static HSSFCell xlCell;
    public static void main(String[] args) throws InterruptedException {

        try {

            FileInputStream xlFile = new FileInputStream("/Downloads/Population.xls");

            // Access the required test data sheet

            xlWBook = new HSSFWorkbook(xlFile);

            // Assuming your data is in Sheet1- if not use your own sheet name
            xlSheet = xlWBook.getSheet("Top50");

            // gives row count in sheet
            int noOfRows = xlSheet.getPhysicalNumberOfRows();

            // gives column count in sheet
            xlRow = xlSheet.getRow(0);
            int noOfColumns = xlRow.getLastCellNum();

            // excelData - 2 dimm array - stores all the excel data -Sheet1 only
            String[][] excelData = new String[noOfRows][noOfColumns];

            // r - row c- column
            for (int r = 1; r < noOfRows; r++) {
                for (int c = 0; c < noOfColumns; c++) {
                    xlRow = xlSheet.getRow(r);
                    xlCell = xlRow.getCell(c);

                    // Here we have complete excel data in an array -excelData-

                    excelData[r][c] = xlCell.getStringCellValue();

                    // System.out.println("row: " + r + " column: " + c);
                    // System.out.println(excelData[r][c]);
                }
            }

            // creating an array to store isExected column
            String[][] isExecuted = new String[noOfRows][1];

            for (int row = 1; row < noOfRows; row++) {
                // here column is always only one
                // so c=0

                // extracting a isExecuted column - and considering it as last
                // column in sheet
                // in your case it is not then - count the column position : use
                // position-1
                // ex: if column position is 7 then use 6 as below
                // isExecuted[row][0]= excelData[row][6];

//                isExecuted[row][0] = excelData[row][noOfColumns - 1];
//
//                if (isExecuted[row][0].equalsIgnoreCase("yes")) {
//
//                    // accessing complete row -which isExecuted=Yes
//
//                    // *********IMPORTANT*****
//                    for (int col = 0; col < noOfColumns; col++) {
//                        // prints all the rows where isExecuted column has Yes
//                        System.out.println(excelData[row][col]);
//                    }
//                }

                // System.out.println(isExecuted[row][0]);

            }
            System.out.println(excelData[3][3]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
