package com.github.lh;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto: 393803588@qq.com">刘涵(Hanl)</a>
 * By 2018/1/16
 */
public class Main {


    public static void main(String[] args) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook(Main.class.getResourceAsStream("/test.xlsx"));

        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            System.out.println("sheet: " + (i + 1));
            for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                Row row = sheet.getRow(j);
                int c = 1;
                Map<String, String> map = new HashMap<>();
                map.put("k", row.getCell(c++).getStringCellValue());
                map.put("v", row.getCell(c).getStringCellValue());
                System.out.println(String.format("%s,%s", row.getCell(c).getStringCellValue(), row.getCell(c).getStringCellValue()));
                System.out.println(map);
            }
        }

    }
}
