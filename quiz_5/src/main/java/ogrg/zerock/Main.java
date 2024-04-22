package ogrg.zerock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("JSON 파싱하기");

        try {
            // API URL 설정
            String url = "https://apis.data.go.kr/1613000/TrainInfoService/getStrtpntAlocFndTrainInfo";
            String serviceKey = "?serviceKey=";
            String basic = "&depPlaceId=NAT010000&arrPlaceId=NAT011668";
            String opt1 = "&depPlandTime=20240420";
            String opt2 = "&trainGradeCode=00";
            String opt3 = "&pageNo=1";
            String opt4 = "&numOfRows=10";
            String opt5 = "&_type=json";
            String myKey = "XuPS49v2X6FCXaKumXRzFARkyM3Fak6i1TweH1DwHV5SR9yfxG2E1BbmjDO51PXYp068TLQ824LYUcHhKkFgSQ==";
            String serviceUrl = url + serviceKey + myKey + basic + opt1 + opt2 + opt3 + opt4 + opt5;

            // GsonTest의 인스턴스 생성
            GsonTest gt = new GsonTest();

            // JSON 데이터를 가져와서 List<TrainInfoDTO> 형태로 변환
            List<TagoDTO> trainInfoList = gt.tagoParserUrl(serviceUrl);

            // 데이터베이스 연결 정보
            String dbUrl = "jdbc:mysql://localhost:3306/quiz5_lkw_db?characterEncoding=utf8&serverTimezone=UTC";
            String userId = "root"; // 사용자 ID
            String userPw = "java404"; // 사용자 비밀번호

            // 데이터베이스 연결
            try (Connection conn = DriverManager.getConnection(dbUrl, userId, userPw);
                 PreparedStatement pstmt = conn.prepareStatement(
                         "INSERT INTO tago (train_no, dep_place, arr_place, dep_time, arr_time) VALUES (?, ?, ?, ?, ?)")) {

                // 각 열차 정보를 데이터베이스에 저장
                for (TagoDTO trainInfo : trainInfoList) {
                    // 데이터베이스에 저장할 값 콘솔에 출력
                    System.out.println("Train No: " + trainInfo.getTrainNo());
                    System.out.println("Dep Place: " + trainInfo.getDepPlace());
                    System.out.println("Arr Place: " + trainInfo.getArrPlace());
                    System.out.println("Dep Time: " + trainInfo.getDepTime());
                    System.out.println("Arr Time: " + trainInfo.getArrTime());

                    // 데이터베이스에 저장
                    pstmt.setString(1, trainInfo.getTrainNo());
                    pstmt.setString(2, trainInfo.getDepPlace());
                    pstmt.setString(3, trainInfo.getArrPlace());
                    pstmt.setString(4, trainInfo.getDepTime());
                    pstmt.setString(5, trainInfo.getArrTime());

                    pstmt.executeUpdate();
                }
                System.out.println("데이터베이스에 저장되었습니다.");
            } catch (SQLException e) {
                System.out.println("데이터베이스 오류: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("API 호출 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
