import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DaumNewsTodaySerises {
    public static void main(String[] args) {
        System.out.println("다움뉴스의 오늘의 연재 출력");

//    문제 1) jsoup을 사용하여 다음 뉴스의 오늘의 연재 부분을 파싱하여 화면에 출력하는 프로그램을 작성하세요
//    출력 형태 = 기사 제목, 기사 링크 2가지 내용을 출력하세요
//    실행 순서
//    1. url 설정
//    2. Document 객체 생성
//    3. Connection.Response 객체 생성 및 Jsoup.connect()로 지정한 url에 접속
//    4. 받아온 데이터를 Document 객체로 변환
//    5. 가져올 데이터가 있는 태그 중 가장 가까운 조상 태그 가져오기
//    6. select()를 사용하여 원하는 태그 가져오기
//    7. 마지막에 선택한 태그에서 기사 제목 및 기사 링크 가져오기

        String url = "https://news.daum.net/?nil_profile=mini&nil_src=news";
        Document doc = null;
        Connection.Response res = null;

        try {
            res = Jsoup.connect(url).method(Connection.Method.GET).execute();
            doc = res.parse();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        Elements list_newsissue = doc.select(".item_todayseries");
        Elements item_issue = list_newsissue.select(".cont_thumb");
        for (int i = 0; i < item_issue.size(); i++) {
            Element item = item_issue.get(i);

            Elements newsATag = item.select(".link_txt");
            String newsTitle = newsATag.text();
            String newsLink = newsATag.attr("href");

            System.out.println((i+1) + "번째 기사 제목 : " + newsTitle);
            System.out.println((i+1) + "번째 기사 링크 : " + newsLink);

            System.out.println("----------------------\n");
        }
    }
}

