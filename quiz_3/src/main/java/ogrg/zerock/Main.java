package ogrg.zerock;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

public class Main {
    public static void main(String[] args) {
        String fileName = "c:\\java404\\quiz3.xlsx";

        try (InputStream fis = new FileInputStream(fileName);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            // 기존 데이터 출력
            int rowLength = sheet.getPhysicalNumberOfRows();
            for (int rowNo = 0; rowNo < rowLength; rowNo++) {
                Row row = sheet.getRow(rowNo);
                if (row != null) {
                    int cellLength = row.getPhysicalNumberOfCells();
                    for (int cellNo = 0; cellNo < cellLength; cellNo++) {
                        Cell cell = row.getCell(cellNo);
                        String value = "";
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case NUMERIC:
                                    value = String.valueOf((int) cell.getNumericCellValue());
                                    break;
                                case STRING:
                                    value = cell.getStringCellValue();
                                    break;
                            }
                        }
                        System.out.print(value + "\t");
                    }
                    System.out.println();
                }
            }

            // 새 데이터 추가
            List<List<String>> newData = Arrays.asList(
                    Arrays.asList("5", "금강선", "게임디렉터", "남성"),
                    Arrays.asList("6", "전재학", "게임디렉터", "남성"),
                    Arrays.asList("7", "한동숙", "인터넷방송인", "남성")
            );

            int lastRowNum = sheet.getLastRowNum();
            for (List<String> rowValues : newData) {
                Row newRow = sheet.createRow(++lastRowNum);
                int cellNum = 0;
                for (String value : rowValues) {
                    Cell cell = newRow.createCell(cellNum++);
                    cell.setCellValue(value);
                }
            }

            // 파일에 변경사항 저장
            try (OutputStream fos = new FileOutputStream(fileName)) {
                workbook.write(fos);
            }

            System.out.println("새 데이터가 성공적으로 추가되었습니다.");

        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
        }
    }
}
