package com.bitc.java404;

import com.bitc.java404.dto.BoxOffice;
import com.bitc.java404.dto.DailyBoxOfficeList;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BoxOfficeParser {

  public List<DailyBoxOfficeList> jsonToObjectUrl(String serviceUrl) {
    List<DailyBoxOfficeList> itemList = null;

    URL url = null;
    HttpURLConnection urlConn = null;
    BufferedReader br = null;

    try {
      url = new URL(serviceUrl);
      urlConn = (HttpURLConnection) url.openConnection();
      urlConn.setRequestMethod("GET");

      br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

      StringBuilder sb = new StringBuilder();
      String line;

      while ((line = br.readLine()) != null) {
        sb.append(line);
      }

      Gson gson = new Gson();
      BoxOffice boxOffice = gson.fromJson(sb.toString(), BoxOffice.class);
      itemList = boxOffice.getBoxOfficeResult().getDailyBoxOfficeList();
    }
    catch (IOException e) {
      System.out.println("JSON 데이터를 파싱 중 오류가 발생했습니다.");
      System.out.println("IOException : " + e.getMessage());
    }
    catch (Exception e) {
      System.out.println("Exception : " + e.getMessage());
    }
    finally {
      try {
        if (br != null) { br.close(); }
        if (urlConn != null) { urlConn.disconnect(); }
      }
      catch (Exception e) {}
    }

    return itemList;
  }

  public int insertDailyBoxOffice(DailyBoxOfficeList item) {
    Connection conn = null;

    String dbUrl = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    String dbUser = "java404";
    String dbPass = "java404";

    PreparedStatement pstmt = null;
    int result = 0;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

      String sql = "insert into daily_box_office ";
      sql += "(rnum, movie_cd, movie_nm, ranks, rank_inten, rank_old_and_new, open_dt, ";
      sql += "sales_amt, sales_share, sales_inten, sales_change, sales_acc, audi_cnt, ";
      sql += "audi_inten, audi_change, audi_acc, scrn_cnt, show_cnt) ";
      sql += "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, item.getRnum());
      pstmt.setString(2, item.getMovieCd());
      pstmt.setString(3, item.getMovieNm());
      pstmt.setString(4, item.getRank());
      pstmt.setString(5, item.getRankInten());
      pstmt.setString(6, item.getRankOldAndNew());
      pstmt.setString(7, item.getOpenDt());
      pstmt.setString(8, item.getSalesAmt());
      pstmt.setString(9, item.getSalesShare());
      pstmt.setString(10, item.getSalesInten());
      pstmt.setString(11, item.getSalesChange());
      pstmt.setString(12, item.getSalesAcc());
      pstmt.setString(13, item.getAudiCnt());
      pstmt.setString(14, item.getAudiInten());
      pstmt.setString(15, item.getAudiChange());
      pstmt.setString(16, item.getAudiAcc());
      pstmt.setString(17, item.getScrnCnt());
      pstmt.setString(18, item.getShowCnt());

      result = pstmt.executeUpdate();
    }
    catch (SQLException e) {
      System.out.println("데이터 추가 시 오류가 발생했습니다.");
      System.out.println("SQLException : " + e.getMessage());
    }
    catch (Exception e) {
      System.out.println("Exception : " + e.getMessage());
    }
    finally {
      try {
        if (pstmt != null) { pstmt.close(); }
        if (conn != null) { conn.close(); }
      }
      catch (Exception e) {}
    }

    return result;
  }
}













