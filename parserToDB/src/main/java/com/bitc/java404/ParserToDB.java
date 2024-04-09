package com.bitc.java404;

import java.sql.*;

public class ParserToDB {
  public void insertDB(MemberDTO member) {
//    데이터 베이스 접속을 위한 클래스 객체 생성
    Connection conn = null;

//    JDBC를 이용한 데이터 베이스 접속 주소 설정
    String dbUrl = "jdbc:mysql://localhost:3306/testdb?characterEncoding=utf8&serverTimezone=UTC";
//    사용자 ID/PW
    String dbUser = "java404";
    String dbPass = "java404";

//    JDBC에서 DBMS로 SQL 쿼리문을 대신 전달하고 해당 결과값을 받아오는 클래스
//    Statement : 간단한 SQL 쿼리문을 실행 시 사용, 실행 후 객체를 닫아야 함(재활용 불가), sql문이 String 타입의 문자열로 만들어져 있음
//    PreparedStatement : 복잡한 SQL 쿼리문을 실행 시 많이 사용, 실행 후 객체를 재활용하여 다시 쿼리문을 전달할 수 있음, String 타입 문자열로 만들어진 sql에 '?' 를 사용하여 원하는 데이터를 나중에 입력할 수 있음
    PreparedStatement pstmt = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

//      실행할 SQL 쿼리문 생성
//      변경될 수 있는 데이터 부분에 '?'를 사용하여 나중에 실제 데이터를 입력할 수 있음
      String sql = "insert into member (user_id, user_pw, user_name, user_email, user_phone, user_addr) values (?, ?, ?, ?, ?, ?) ";

//      데이터 베이스에 접속된 Connection 객체를 통해서 PreparedStatement 객체를 받아옴
      pstmt = conn.prepareStatement(sql);
//      setXXX() 메소드를 통해서 sql에 입력된 '?' 부분에 데이터를 추가함
//      setXXX() 메소드는 기본 데이터 타입을 모두 제공함, (setInt, setShort, setBoolean, setLong, setByte, setFloat, setDouble, setString)
//      setXXX() 메소드의 첫번째 매개변수는 '?'의 번호, 두번째 매개변수는 입력될 데이터
      pstmt.setString(1, member.getUser_id());
      pstmt.setString(2, member.getUser_pw());
      pstmt.setString(3, member.getUser_name());
      pstmt.setString(4, member.getUser_email());
      pstmt.setString(5, member.getUser_phone());
      pstmt.setString(6, member.getUser_addr());

//      executeQuery() : select 문 실행 시 사용, select문 실행 결과를 ResultSet 이라는 타입으로 출력
//      executeUpdate() : insert, update, delete 문 실행 시 사용, 반환값으로 실행된 건수가 출력됨
      int result = pstmt.executeUpdate();

      if (result > 0) {
        System.out.println(result + "건의 데이터가 추가되었습니다.");
      }
      else {
        System.out.println("데이터 베이스에 데이터가 추가되지 않았습니다.");
      }
    }
    catch (SQLException e) {
      System.out.println("데이터 베이스 연결 중 오류가 발생했습니다.");
      System.out.println("SQLException : " + e.getMessage());
    }
    catch (Exception e) {
      System.out.println("Exception : " + e.getMessage());
    }
    finally {
      try {
        if (conn != null) { conn.close(); }
      }
      catch (Exception e) {}
    }
  }

  public void selectDB() {
    Connection conn = null;

    String dbUrl = "jdbc:mysql://localhost:3306/testdb?characterEncoding=utf8&serverTimezone=UTC";
    String dbUser = "java404";
    String dbPass = "java404";

//    SQL 쿼리문 사용을 위한 Statement 객체 생성
    Statement stmt = null;
//    데이터 베이스 정보를 받아오는 클래스
    ResultSet rs = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

//      select 쿼리문 생성
      String sql = "select * from member ";

//      Connection 객체를 통해서 Statement 객체를 받아옴
      stmt = conn.createStatement();
//      executeQuery()를 사용하여 select 문 실행 후 결과를 받아옴
//      받아온 결과를 ResultSet 타입의 객체 rs에 저장
      rs = stmt.executeQuery(sql);

//      next() : 데이터가 존재 시 데이터를 가져옴, 데이터가 없으면 false를 출력
      while (rs.next()) {
//        getXXX() 를 사용하여 데이터를 가져옴
//        getXXX()는 메소드 오버로딩을 통해서 여러가지 매개변수를 받음(주로 2개 사용)
//        1. index 번호를 받아서 해당 하는 순서의 컬럼 데이터를 가져옴
//        2. 컬럼명을 직접 입력하여 해당 컬럼 데이터를 가져옴
        String userId = rs.getString("user_id");
        String userPw = rs.getString("user_pw");
        String userName = rs.getString("user_name");
        String userEmail = rs.getString("user_email");
        String userPhone = rs.getString("user_phone");
        String userAddr = rs.getString("user_addr");

        System.out.println("ID : " + userId);
        System.out.println("PW : " + userPw);
        System.out.println("Name : " + userName);
        System.out.println("Email : " + userEmail);
        System.out.println("Phone : " + userPhone);
        System.out.println("Address : " + userAddr);
        System.out.println("---------------------------------");
      }
    }
    catch (SQLException e) {
      System.out.println("데이터 베이스 사용 중 오류가 발생했습니다.");
      System.out.println("SQLException : " + e.getMessage());
    }
    catch (Exception e) {
      System.out.println("Exception : " + e.getMessage());
    }
    finally {
      try {
//        리소스 닫는 순서는 리소스를 열었던 순서의 반대로 진행
//        ResultSet > Statement > Connection 순으로 진행
        if (rs != null) { rs.close(); }
        if (stmt != null) { stmt.close(); }
        if (conn != null) { conn.close(); }
      }
      catch (Exception e) {}
    }
  }

  public void deleteDB(String userId) {
    Connection conn = null;

    String dbUrl = "jdbc:mysql://localhost:3306/testdb";
    String dbUser = "java404";
    String dbPass = "java404";

    Statement stmt = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

      String sql = "delete from ";
      sql += "member where ";
      sql += "user_id = '" + userId + "' ";

      stmt = conn.createStatement();
      int result = stmt.executeUpdate(sql);

      if (result > 0) {
        System.out.println(result + "건이 삭제 되었습니다.");
      }
      else {
        System.out.println("삭제된 데이터가 없습니다.");
      }
    }
    catch (SQLException e) {
      System.out.println("데이터 베이스 사용 중 오류가 발생했습니다.");
      System.out.println("SQLException : " + e.getMessage());
    }
    catch (Exception e) {
      System.out.println("Exception : " + e.getMessage());
    }
    finally {
      try {
        if (stmt != null) { stmt.close(); }
        if (conn != null) { conn.close(); }
      }
      catch (Exception e) {}
    }
  }

  public void updateDB(MemberDTO member) {
    Connection conn = null;

    String dbUrl = "jdbc:mysql://localhost:3306/testdb?characterEncoding=utf8&serverTimezone=UTC";
    String dbUser = "java404";
    String dbPass = "java404";

    PreparedStatement pstmt = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

      String sql = "update member set ";
      sql += "user_name = ?, user_email = ?, user_phone = ? ";
      sql += "where user_id = ? ";

      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, member.getUser_name());
      pstmt.setString(2, member.getUser_email());
      pstmt.setString(3, member.getUser_phone());
      pstmt.setString(4, member.getUser_id());

      int result = pstmt.executeUpdate();

      if (result > 0) {
        System.out.println(result + "건이 수정되었습니다.");
      }
      else {
        System.out.println("수정된 내용이 없습니다.");
      }
    }
    catch (SQLException e) {}
    catch (Exception e) {}
    finally {
      try {
        if (pstmt != null) { pstmt.close(); }
        if (conn != null) { conn.close(); }
      }
      catch (Exception e) {}
    }
  }
}











