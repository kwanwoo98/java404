package com.bitc.java404;

import com.bitc.java404.dto.CulturalDTO;
import com.bitc.java404.dto.InteriorDTO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UseExcel {

//  Apache POI : 아파치 소프트웨어 재단에서 만든 라이브러리
//  마이크로 소프트의 오피스 파일을 자바 언어에서 읽고 쓰는 기능을 제공해줌
//  POIFS : MS office의 ole2 포맷 방식을 사용하는 클래스, 모든 오피스 파일 포맷은 ole2 방식을 사용함
//  HSSF : MS office excel 97버전 포맷 방식(xls 확장자)의 엑셀 파일을 컨트롤할 때 사용하는 클래스
//  XSSF : MS office excel 2007버전 포맷 방식(xlsx 확장자)의 엑셀 파일을 컨트롤 할 때 사용하는 클래스
//  SXSSF : XSSF의 스트리밍 버전, 대용량 엑셀 파일을 다룰 경우 사용, 대용량 엑셀 파일 사용 시 임시 파일을 생성하면서 사용할 수 있음
//  HPSF : 오피스 파일의 문서 요약 정보 읽기
//  HWPF : 워드 파일 컨트롤 시 사용
//  HSLF : 파워 포인트 컨트롤 시 사용
//  HPBF : 퍼블리셔 파일 컨트롤 시 사용
//  HSMF : 아웃룩의 msg 파일을 컨트롤 시 사용
//  DDF : 오피스에서 사용하는 이미지 파일을 컨트롤 시 사용

  public void readExcel(String fileName) {
    try {
//      InputStream 방식으로 엑셀 파일을 읽어옴
//      일반 text 파일이 아니기 때문에 InputStream 방식 사용
      FileInputStream fis = new FileInputStream(fileName);

//      XSSF 방식에서 제공하는 워크북 클래스, 간단하게 엑셀파일을 뜻함
      XSSFWorkbook workbook = new XSSFWorkbook(fis);

      int rowNo = 0; // 엑셀의 세로 줄 번호를 입력받을 변수
      int cellNo = 0; // 엑셀의 가로 칸 버호를 입력받을 변수

//      XSSF 방식에서 제공하는 Sheet 클래스 객체 생성
//      getSheetAt(sheet 번호) : 지정한 번호의 sheet를 가져옴
      XSSFSheet sheet = workbook.getSheetAt(0);
//      getPhysicalNumberOfRows() : 사용자가 입력한 내용이 있는 row의 위치를 반환
      int rows = sheet.getPhysicalNumberOfRows();

      for (rowNo = 0; rowNo <= rows; rowNo++) {
//        XSSF 방식에서 제공하는 Row 클래스 객체를 생성
//        getRow(row 번호) : 지정한 index의 ROW 객체를 가져옴
//        사용자가 입력한 데이터가 없을 경우 null 출력
        XSSFRow row = sheet.getRow(rowNo);

//        ROW 데이터가 있을 경우만 실행
        if (row != null) {
//          getPhysicalNumberOfCells() : 사용자가 입력한 cell의 위치를 반환
          int cells = row.getPhysicalNumberOfCells();

          for (cellNo = 0; cellNo <= cells; cellNo++) {
//            XSSF 방식에서 제공하는 Cell 클래스 객체 생성
//            getCell(cell 번호) : 지정한 index에 저장된 cell 객체를 가져옴
//            사용자가 입력한 Cell 데이터가 없을 경우 null을 반환
            XSSFCell cell = row.getCell(cellNo);
            String value = ""; // cell에 입력된 데이터를 저장할 변수

//            cell에 데이터가 없으면 continue를 사용하여 다음번 루프로 넘어감, 다음 cell로 넘어감
            if (cell == null) {
              continue;
            }
            else { // cell에 데이터가 있을 경우 데이터 가져오기
//              cell에 저장된 내용의 데이터 타입을 구분
              switch (cell.getCellType()) {
//                수식이 저장되어 있을 경우
                case FORMULA:
                  value = cell.getCellFormula();
                  break;

//                  숫자 데이터가 저장되어 있을 경우
                case NUMERIC:
                  value = String.valueOf(cell.getNumericCellValue());
                  break;

//                  문자열 데이터가 저장되어 있을 경우
                case STRING:
                  value = cell.getStringCellValue();
                  break;

//                  Boolean 데이터가 저장되어 있을 경우
                case BLANK:
                  value = String.valueOf(cell.getBooleanCellValue());

//                  오류 발생 시
                case ERROR:
                  value = String.valueOf(cell.getErrorCellValue());
              }
            }
            System.out.println(rowNo + "행 " + cellNo + "칸의 값 : " + value);
          }
        }
      }
    }
    catch (Exception e) {

    }
  }

  public void interiorList(String fileName) {
    InputStream fis = null;
    XSSFWorkbook workbook = null;
    XSSFSheet sheet = null;

    try {
      fis = new FileInputStream(fileName);
      workbook = new XSSFWorkbook(fis);
      sheet = workbook.getSheetAt(0);

      int rowLength = sheet.getPhysicalNumberOfRows();

      for (int rowNo = 1; rowNo < rowLength; rowNo++) {
        XSSFRow row = sheet.getRow(rowNo);

        if (row != null) {
          int cellLength = row.getPhysicalNumberOfCells();

          for (int cellNo = 0; cellNo < cellLength; cellNo++) {
            XSSFCell cell = row.getCell(cellNo);
            String value = "";

            if (cell == null) {
              continue;
            }
            else {
              switch (cell.getCellType()) {
                case FORMULA:
                  value = cell.getCellFormula();
                  break;

                case NUMERIC:
                  value = String.valueOf((int)cell.getNumericCellValue());
                  break;

                case STRING:
                  value = cell.getStringCellValue();
                  break;

                case BLANK:
                  value = String.valueOf(cell.getBooleanCellValue());
                  break;

                case ERROR:
                  value = String.valueOf(cell.getErrorCellValue());
                  break;
              }
            }
            System.out.println(value + " ");
          }
        }
        System.out.println("------------------------");
      }
    }
    catch (IOException e) {
      System.out.println("오류 발생 : " + e.getMessage());
    }
    finally {
      try {
        if (workbook != null) { workbook.close(); }
        if (fis != null) { fis.close(); }
      }
      catch (Exception e) {}
    }
  }

//  문제 1) 제공된 엑셀 파일을 poi 라이브러리를 사용하여 엑셀 파일의 내용을 화면에 모두 출력하는 프로그램을 작성하세요
//  ps. 사용자 키보드 입력을 통해서 파일명(파일 전체 경로)을 입력받아서 사용
//  ps. 제공된 엑셀 파일이 xls 인지 xlsx 인지를 구분하는 소스코드를 추가하여 작성 (File 클래스 사용)
//  ps. 확장자가 xls 이면 HSSF 사용, xlsx 이면 XSSF 사용
  public void culturalList(String fileName) {

    InputStream fis = null;
//    xlsx 확장자 사용 시
    XSSFWorkbook xwb = null;
    XSSFSheet xSheet = null;
//    xls 확장자 사용 시
    HSSFWorkbook hwb = null;
    HSSFSheet hSheet = null;

    String[] fileInfo = fileInfo(fileName);

//    파일 확장자 타입에 따라 사용할 클래스 선택
    if (fileInfo[1].equals("xls")) {
      try {
        fis = new FileInputStream(fileInfo[0] + "." + fileInfo[1]);
        hwb = new HSSFWorkbook(fis);
        hSheet = hwb.getSheetAt(0);

        int rowLength = hSheet.getPhysicalNumberOfRows();

        for (int rowNo = 1; rowNo < rowLength; rowNo++) {
          HSSFRow row = hSheet.getRow(rowNo);

          if (row != null) {
            int cellLength = row.getPhysicalNumberOfCells();

            for (int cellNo = 0; cellNo < cellLength; cellNo++) {
              HSSFCell cell = row.getCell(cellNo);
              String value = "";

              if (cell == null) {
                continue;
              }
              else {
                switch (cell.getCellType()) {
                  case FORMULA:
                    value = cell.getCellFormula();
                    break;

                  case NUMERIC:
                    value = String.valueOf((int)cell.getNumericCellValue());
                    break;

                  case STRING:
                    value = cell.getStringCellValue();
                    break;

                  case BLANK:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;

                  case ERROR:
                    value = String.valueOf(cell.getErrorCellValue());
                    break;
                }
              }
              System.out.println(value + " ");
            }
          }
          System.out.println("------------------------");
        }
      }
      catch (IOException e) {
        System.out.println("오류 발생 : " + e.getMessage());
      }
      finally {
        try {
          if (hwb != null) { hwb.close(); }
          if (fis != null) { fis.close(); }
        }
        catch (Exception e) {}
      }
    }
    else {
      try {
        fis = new FileInputStream(fileInfo[0] + "." + fileInfo[1]);
        xwb = new XSSFWorkbook(fis);
        xSheet = xwb.getSheetAt(0);

        int rowLength = xSheet.getPhysicalNumberOfRows();

        for (int rowNo = 1; rowNo < rowLength; rowNo++) {
          XSSFRow row = xSheet.getRow(rowNo);

          if (row != null) {
            int cellLength = row.getPhysicalNumberOfCells();

            for (int cellNo = 0; cellNo < cellLength; cellNo++) {
              XSSFCell cell = row.getCell(cellNo);
              String value = "";

              if (cell == null) {
                continue;
              }
              else {
                switch (cell.getCellType()) {
                  case FORMULA:
                    value = cell.getCellFormula();
                    break;

                  case NUMERIC:
                    value = String.valueOf((int)cell.getNumericCellValue());
                    break;

                  case STRING:
                    value = cell.getStringCellValue();
                    break;

                  case BLANK:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;

                  case ERROR:
                    value = String.valueOf(cell.getErrorCellValue());
                    break;
                }
              }
              System.out.println(value + " ");
            }
          }
          System.out.println("------------------------");
        }
      }
      catch (IOException e) {
        System.out.println("오류 발생 : " + e.getMessage());
      }
      finally {
        try {
          if (xwb != null) { xwb.close(); }
          if (fis != null) { fis.close(); }
        }
        catch (Exception e) {}
      }
    }
  }

  private String[] fileInfo(String fileName) {
//    파일의 경로 정보를 저장할 배열
    String[] fileInfo = new String[2];

//    파일 명을 사용하여 File 객체 생성
    File file = new File(fileName);
//    getPath() : 지정한 파일 및 디렉토리의 전체 경로를 가져옴
//    getName() : 지정한 파일 및 디렉토리의 경로를 제외한 이름만 가져옴
//    전체 경로를 가져오기
    String fileFullPath = file.getPath();

//    전체 경로 중 뒤에서 부터 시작하여 '.'이 가장 먼저 검색되는 위치를 검색
//    '.' 을 제외한 앞 부분의 정보를 배열의 0번 index에 저장
    fileInfo[0] = fileFullPath.substring(0, fileFullPath.lastIndexOf("."));
//    '.' 을 제외한 뒷 부분의 정보를 배열의 1번 index에 저장
    fileInfo[1] = fileFullPath.substring(fileFullPath.lastIndexOf(".") + 1);

    return fileInfo;
  }

//  인테리어 업체 정보 파일의 내용을 파싱하고 데이터베이스에 저장하기 위한 메소드
  public void interiorDataInfo(String fileName) throws FileNotFoundException {
//    파일 타입 확인
    String[] fileInfo = fileInfo(fileName);
    List<InteriorDTO> itemList = null;

    if (fileInfo[1].equals("xlsx")) {
//      엑셀 파일의 내용을 파싱하여 데이터 가져오기, List<InteriorDTO> 타입으로 반환
      itemList = interiorListParse(new FileInputStream(fileName));
    }
    else {
      System.out.println("지원하지 않는 파일 형식입니다.");
    }

//    가져온 데이터를 데이터 베이스에 입력
    interiorListToDB(itemList);
  }

//  파일 스트림을 통해서 가져온 엑셀 파일의 내용을 POI 라이브러리를 통해서 데이터를 파싱하는 메소드
  private List<InteriorDTO> interiorListParse(FileInputStream fis) {
//    xlsx 확장자용 XSSF 방식 사용
    XSSFWorkbook workbook = null;
    XSSFSheet sheet = null;
//    엑셀 파일의 내용을 저장할 List<InteriorDTO> 객체
    List<InteriorDTO> itemList = new ArrayList<>();

    try {
//      워크북 생성
      workbook = new XSSFWorkbook(fis);
//      지정한 시트 열기
      sheet = workbook.getSheetAt(0);

//      데이터가 포함된 row의 위치 확인
      int rowLength = sheet.getPhysicalNumberOfRows();

//      반복문으로 ROW 객체를 한줄씩 가져오기
      for (int i = 1; i < rowLength; i++) {
//        Sheet에서 ROW 객체 가져오기
        XSSFRow row = sheet.getRow(i);
//        ROW 객체가 가지고 있는 데이터를 저장할 InteriorDTO 클래스 타입의 객체 생성
        InteriorDTO item = new InteriorDTO();

        if (row != null) {
//          사용자가 저장한 데이터가 있는 cell 위치 확인
          int cellLength = row.getPhysicalNumberOfCells();
          
//          반복문으로 각 CELL에 저장된 데이터 가져오기
          for (int j = 0; j < cellLength; j++) {
//            cell의 데이터 가져오기
            XSSFCell cell = row.getCell(j);
            String value = "";

            if (cell == null) {
              continue;
            }
            else {
//              Cell의 데이터 타입 확인 후 타입에 맞게 데이터 가져오기
              switch (cell.getCellType()) {
                case FORMULA:
                  value = cell.getCellFormula();
                  break;

                case STRING:
                  value = cell.getStringCellValue();
                  break;

                case NUMERIC:
                  value = String.valueOf((int)cell.getNumericCellValue());
                  break;

                case BLANK:
                  value = String.valueOf(cell.getBooleanCellValue());
                  break;

                case ERROR:
                  value = String.valueOf(cell.getErrorCellValue());
                  break;
              }

//              InteriorDTO 클래스 타입의 객체에 데이터를 순서에 맞게 저장함
              switch (j) {
                case 0:
                  item.setSeq(Integer.parseInt(value));
                  break;

                case 1:
                  item.setName(value);
                  break;

                case 2:
                  item.setTel(value);
                  break;

                case 3:
                  item.setAddress(value);
                  break;
              }
            }
          }
        }
//        List<InteriorDTO> 객체에 InteriorDTO 객체를 추가함
        itemList.add(item);
      }
    } catch (IOException e) {
    }

//    List 반환
    return itemList;
  }

  private void interiorListToDB(List<InteriorDTO> itemList) {

//    오라클 사용 시
//    String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    String dbUrl = "jdbc:mysql://localhost:3306/testdb?characterEncoding=utf8&serverTimezone=UTC";
    String dbUser = "java404";
    String dbPass = "java404";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
//      오라클 사용 시
//      Class.forName("oracle.jdbc.driver.OracleDriver");
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

      String sql = "insert into interior (seq, name, tel, address) ";
      sql += "values (?, ?, ?, ?) ";

      pstmt = conn.prepareStatement(sql);

////      PreparedStatement 객체 재사용하는 방법 1
////      List<InteriorDTO> 의 크기만큼 반복하여 데이터 베이스에 입력
//      for (InteriorDTO item : itemList) {
////        PreparedStatement 객체에 데이터 설정
//        pstmt.setInt(1, item.getSeq());
//        pstmt.setString(2, item.getName());
//        pstmt.setString(3, item.getTel());
//        pstmt.setString(4, item.getAddress());
//
////        데이터 베이스에 sql 쿼리문 실행
//        pstmt.executeUpdate();
////        PreparedStatement 객체에 설정된 sql문에 '?'로 입력된 데이터를 초기화 시킴
//        pstmt.clearParameters();
//      }

      //      PreparedStatement 객체 재사용하는 방법 2
      for (InteriorDTO item : itemList) {
        pstmt.setInt(1, item.getSeq());
        pstmt.setString(2, item.getName());
        pstmt.setString(3, item.getTel());
        pstmt.setString(4, item.getAddress());

//        sql문의 '?' 부분에 데이터를 설정하고 데이터베이스에서 실행할 쿼리문을 순차적으로 실행할 수 있도록 설정
        pstmt.addBatch();
        pstmt.clearParameters();
      }
//        executeBatch() 실행 시 한번에 여러개의 sql 쿼리문을 실행함
//      배치된 내용이 너무 많으면 메모리를 많이 사용하여 오류가 발생할 수 있음
      pstmt.executeBatch();

      System.out.println("데이터 베이스 입력 완료");
    }
    catch (SQLException e) {
      System.out.println("데이터 베이스 사용 중 오류가 발생했습니다.");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    finally {
      try {
        if (pstmt != null) { pstmt.close(); }
        if (conn != null) { conn.close(); }
      }
      catch (Exception e) {}
    }
  }

//  문제 2) 주어진 엑셀 파일의 내용을 데이터 베이스에 저장하는 프로그램을 작성하세요
//  엑셀파일 : cultural.xlsx
//  DTO 클래스 생성(CulturalDTO)
//  DB 테이블 : cultural
//  컬럼 : name(문화재명), addr1(소재지), care(관리주체), code(지정번호), reg_dt(지정일자), age(시대), type(종별), care_tel(담당자 전화번호), addr2(구군명), upd_dt(데이터 기준일자), lat(위도), lng(경도)

  private boolean fileInfo2(String fileName) {
    File file = new File(fileName);
    String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);

    if (ext.equals("xlsx")) {
      return true;
    }

    return false;
  }

  public void culturalInfo(String fileName) throws FileNotFoundException {
    List<CulturalDTO> itemList = null;

    if (fileInfo2(fileName)) {
      itemList = culturalInfoParse(new FileInputStream(fileName));
    }
    else {
      System.out.println("지원하지 않는 파일 형식입니다.");
    }

    culturalInfoDBInsert(itemList);
  }

  public List<CulturalDTO> culturalInfoParse(InputStream fis) {
    List<CulturalDTO> itemList = new ArrayList<>();

    XSSFWorkbook workbook = null;
    XSSFSheet sheet = null;

    try {
      workbook = new XSSFWorkbook(fis);
      sheet = workbook.getSheetAt(0);

      int rowLength = sheet.getPhysicalNumberOfRows();

      for (int i = 1; i < rowLength; i++) {
        XSSFRow row = sheet.getRow(i);
        CulturalDTO item = new CulturalDTO();

        if (row != null) {
          int cellLength = row.getPhysicalNumberOfCells();

          for (int j = 0; j < cellLength; j++) {
            XSSFCell cell = row.getCell(j);
            String value = "";

            if (cell == null) {
              continue;
            }
            else {
              switch (cell.getCellType()) {
                case FORMULA:
                  value = cell.getCellFormula();
                  break;

                case STRING:
                  value = cell.getStringCellValue();
                  break;

                case NUMERIC:
//                  날짜 정보가 NUMERIC으로 타입이 설정되어 있음
//                  getNumericCellValue()로 날자 데이터를 받으면 실수형 데이터가 넘어옴
//                  POI 라이브러리의 DataFormatter 클래스 타입의 객체를 선언
//                  formatCellValue(cell) 를 실행하면 cell의 값을 입력된 그대로 가져옴
                  DataFormatter formatter = new DataFormatter();
                  value = formatter.formatCellValue(cell);
                  value = value.replace("/", "-");
                  break;

                case BLANK:
                  value = String.valueOf(cell.getBooleanCellValue());
                  break;

                case ERROR:
                  value = String.valueOf(cell.getErrorCellValue());
                  break;
              }

              switch (j) {
                case 0:
                  item.setName(value);
                  break;

                case 1:
                  item.setAddr1(value);
                  break;

                case 2:
                  item.setCare(value);
                  break;

                case 3:
                  item.setCode(value);
                  break;

                case 4:
                  item.setReg_dt(value);
                  break;

                case 5:
                  item.setAge(value);
                  break;

                case 6:
                  item.setType(value);
                  break;

                case 7:
                  item.setCare_tel(value);
                  break;

                case 8:
                  item.setAddr2(value);
                  break;

                case 9:
                  item.setUpd_dt(value);
                  break;

                case 10:
                  item.setLat(value);
                  break;

                case 11:
                  item.setLat(value);
                  break;

                case 12:
                  item.setLng(value);
                  break;
              }
            }
          }
        }
        itemList.add(item);
      }
    } catch (IOException e) {
    }

    return itemList;
  }

  public void culturalInfoDBInsert(List<CulturalDTO> itemList) {
    String dbUrl = "jdbc:mysql://localhost:3306/testdb?characterEncoding=utf8&serverTimezone=UTC";
    String dbUser = "java404";
    String dbPass = "java404";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

      String sql = "insert into cultural (name, addr1, care, code, reg_dt, age, type, care_tel, addr2, upd_dt, lat, lng) ";
      sql += "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

      pstmt = conn.prepareStatement(sql);

      for (CulturalDTO item : itemList) {
        pstmt.setString(1, item.getName());
        pstmt.setString(2, item.getAddr1());
        pstmt.setString(3, item.getCare());
        pstmt.setString(4, item.getCode());
        pstmt.setString(5, item.getReg_dt());
        pstmt.setString(6, item.getAge());
        pstmt.setString(7, item.getType());
        pstmt.setString(8, item.getCare_tel());
        pstmt.setString(9, item.getAddr2());
        pstmt.setString(10, item.getUpd_dt());
        pstmt.setString(11, item.getLat());
        pstmt.setString(12, item.getLng());

        pstmt.addBatch();
        pstmt.clearParameters();
      }
      pstmt.executeBatch();

      System.out.println(" ***** 데이터 베이스 입력 완료 ***** ");
    }
    catch (SQLException e) {
      System.out.println("데이터 베이스 사용 중 오류가 발생했습니다.");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    finally {
      try {
        if (pstmt != null) { pstmt.close(); }
        if (conn != null) { conn.close(); }
      }
      catch (Exception e) {}
    }
  }
}














