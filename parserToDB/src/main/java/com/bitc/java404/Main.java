package com.bitc.java404;

import com.bitc.java404.dto.DailyBoxOfficeList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

  public static void dbTest() {
    System.out.println("데이터 베이스 사용 테스트");

//    데이터베이스 서버 접속용 클래스
    Connection conn = null;

    try {
//      데이터 베이스 접속 주소
      String url = "jdbc:mysql://localhost:3306/testdb?characterEncoding=utf8&serverTimezone=UTC";
      String userId = "java404"; // 사용자 ID
      String userPw = "java404"; // 사용자 비밀번호

//      JDBC 드라이버 로딩
      Class.forName("com.mysql.cj.jdbc.Driver");
//      DriverManager를 사용하여 지정한 데이터베이스에 사용자ID/PW를 사용하여 접속
      conn = DriverManager.getConnection(url, userId, userPw);
      System.out.println("데이터 베이스 연결이 성공했습니다.");
    }
    catch (SQLException e) {
      System.out.println("데이터 베이스 연결이 실패했습니다.");
      System.out.println("SQLException : " + e.getMessage());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    finally {
      try {
//        Connection 객체도 외부 리소스이기 때문에 반드시 사용자가 직접 close() 해야 함
        if (conn != null) { conn.close(); }
      }
      catch (Exception e) {}
    }
  }

  public static void main(String[] args) {
//    dbTest();

//    ParserToDB parse = new ParserToDB();
//    parse.insertDB();
//    parse.selectDB();
//    System.out.println("----- 데이터 삭제 -----");
//    parse.deleteDB("test3");
//    parse.selectDB();
//    System.out.println("----- 데이터 수정 -----");
//    parse.updateDB();
//    parse.selectDB();

//    사용자 데이터 추가하기
//    MemberDTO member = new MemberDTO();
//    member.setUser_id("test4");
//    member.setUser_pw("1234");
//    member.setUser_name("테스트유저4");
//    member.setUser_email("testuser4@bitc.ac.kr");
//    member.setUser_phone("01012349876");
//    member.setUser_addr("서울");

//    ParserToDB parser = new ParserToDB();
//    System.out.println("----- 기존 사용자 정보 확인 -----");
//    parser.selectDB();
//    System.out.println("----- 사용자 정보 추가 -----");
//    parser.insertDB(member);
//    System.out.println("----- 추가 후 전체 사용자 정보 확인 -----");
//    parser.selectDB();

//    member.setUser_id("test5");
//    member.setUser_pw("4321");
//    member.setUser_name("테스트유저5");
//    member.setUser_email("testuser5@bitc.ac.kr");
//    member.setUser_phone("01011223344");
//    member.setUser_addr("대전");

//    System.out.println("----- 새 사용자 정보 추가 -----");
//    parser.insertDB(member);
//    System.out.println("----- 추가 후 전체 사용자 정보 확인 -----");
//    parser.selectDB();

//    System.out.println("----- 데이터 수정하기 -----");
//
//    member.setUser_id("test5");
//    member.setUser_pw("1234567890");
//    member.setUser_name("유저테스터5");
//    member.setUser_email("user5@bitc.ac.kr");
//    member.setUser_phone("01011223344");
//    member.setUser_addr("대전");
//
//    parser.updateDB(member);
//    System.out.println("----- 데이터 수정 후 전체 내용 확인 -----");
//    parser.selectDB();

    System.out.println("\n ----- url을 통해 받아온 json 데이터를 DB에 저장하기 -----\n");

//    json 데이터를 제공하는 서비스 주소
    String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
//    서비스를 사용하기 위한 키 설정
    String serviceKey = "?key=";
    String myKey = "f5eef3421c602c6cb7ea224104795888";
//    서비스 제공 옵션 설정
    String opt1 = "&targetDt=";

//    전체 서비스 url 생성하기
    String serviceUrl = url + serviceKey + myKey + opt1 + "20240404";

//    BoxOfficeParser 타입의 객체 생성
    BoxOfficeParser boxOfficeParser = new BoxOfficeParser();
//    jsonToObjectUrl() 를 실행하여 api 서비스에 접속하여 json데이터를 가져와서 파싱함
    List<DailyBoxOfficeList> itemList = boxOfficeParser.jsonToObjectUrl(serviceUrl);

    
    for (DailyBoxOfficeList item : itemList) {
//      insertDailyBoxOffice()를 실행하여 DBMS에 접속하여 DB에 데이터를 추가함
      boxOfficeParser.insertDailyBoxOffice(item);
    }
  }
}











