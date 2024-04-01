import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JavaTest {
    public static void main(String args[]) {
        System.out.println("Hello World!!");

//    System.out.println("\n ----- 다음 홈페이지 가져오기 -----\n ");
//    try {
//      Document daum = Jsoup.connect("https://www.daum.net").get();
//      System.out.println(daum);
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//
//    System.out.println("\n ----- 구글 홈페이지 가져오기 ----- \n");
//    try {
//      Connection.Response res = Jsoup.connect("https://www.google.co.kr").method(Connection.Method.GET).execute();
//      Document google = res.parse();
//      System.out.println(google);
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }

        Document doc = null;
        Connection.Response res = null;
        try {
            res = Jsoup.connect("https://www.google.co.kr").method(Connection.Method.GET).execute();
            doc = res.parse();
        }
        catch (IOException e) {
            System.out.println("Jsoup로 html 파싱 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

//    System.out.println("\n ----- html()로 출력 ----- \n");
//    System.out.println(doc.html());
//
//    System.out.println("\n ----- text() 로 출력 ----- \n");
//    System.out.println(doc.text());

//    select() 사용하기
//    Element btnK = doc.select("input[name=btnK]").first();
//    String btnKValue = btnK.attr("value");
//    System.out.println("select() 로 출력 : " + btnKValue);
//
//    Elements btns = doc.select("input[name=btnK]");
//    for (int i = 0; i < btns.size(); i++) {
//      Element btn = btns.get(i);
//      String btnValue = btn.attr("value");
//      System.out.println("select() 로 출력 - " + i + " : " + btnValue);
//    }

//    select()를 이용하여 html 태그 명을 검색
        Elements imgs = doc.select("img");
        System.out.println("img태그의 수 : " + imgs.size());

        for (int i = 0; i < imgs.size(); i++) {
            Element img = imgs.get(i);
            String imgSrc = img.attr("src");

            if (imgSrc.isEmpty()) {
                continue;
            }
            System.out.println(i + "번째 img 태그 : " + imgSrc);
        }

//    select()를 사용하여 id 명으로 검색
        Elements inputs = doc.select("#APjFqb");
        System.out.println("사용자 입력란 수 : " + inputs.size());

//    first()를 사용하여 검색된 태그 중 첫번째 태그를 가져옴
        Element input = doc.select("#APjFqb").first();
        String inputValue = input.attr("title");
        System.out.println("사용자 입력란 title : " + inputValue);

//    select()를 사용하여 class 값으로 검색
        Elements classz = doc.select(".o3j99");
        System.out.println(classz.size());

        for(int i = 0; i < classz.size(); i++) {
            Element item = classz.get(i);
            Element style = item.select("style").first();
            System.out.println(style);
        }

//    select()를 사용하여 지정한 태그를 제외한 나머지 태그를 모두 가져오기
        Elements elements = doc.select(".o3j99");
        System.out.println("class 값 .o3j99 로 검색 결과 : " + elements.size());

        Elements divList1 = elements.select("div");
        Elements divList2 = elements.select("div").not(".LX3sZb");

        System.out.println("divList1의 요소 수 : " + divList1.size());
        System.out.println("divList2의 요소 수 : " + divList2.size());

//    select()를 사용하여 지정한 태그를 제외한 나머지 태그를 모두 가져오기
//    전체 html문서에서 클래스 값이 L3eUgb 인 태그 중 가장 먼저 검색되는 태그를 한개 가져옴
        Element element = doc.select(".L3eUgb").first();
//    클래스 값이 L3eUgb 인 태그의 자식 태그 중 div 태그를 모두 가져옴
        divList1 = element.select("div");
//    클래스 값이 L3eUgb 인 태그의 자식 태그 중 div 태그를 모두 가져오는데 클래스 값이 o3j99인 태그를 제외함
        divList2 = element.select("div").not(".o3j99");

        System.out.println(".o3j99 포함 : " + divList1.size() + "개");
        System.out.println(".o3j99 미포함 : " + divList2.size() + "개");


//    getElementById() 를 사용하여 태그 1개 검색
//    Element divTag = doc.getElementById("sZmt3b");
//    System.out.println(divTag);

//    getElementByTag() 를 사용하여 지정한 태그를 모두 검색
        Elements divList = doc.getElementsByTag("div");
        System.out.println("divList 수 : " + divList.size());
//    getElementByClass() 를 사용하여 지정한 class 값을 가지고 있는 모든 태그를 검색

        divList = doc.getElementsByClass("o3j99");
        System.out.println("divList 수 : " + divList.size());


//      Element 객체에서 제공하는 메소드 사용하기
        Element imgTag = doc.select("img").first();
//    attr()로 값 가져오기
        String attrValue = imgTag.attr("src");
        System.out.println("img의 src 값 가져오기 : " + attrValue);
        attrValue = imgTag.attr("alt");
        System.out.println("img의 alt 값 가져오기 : " + attrValue);
//    attr()로 값 설정하기
        imgTag.attr("alt", "구글");
        attrValue = imgTag.attr("alt");
        System.out.println("attr()로 속성값 변경 후 : " + attrValue);

//    id() 로 id 속성값 가져오기
        String idValue = imgTag.id();
        System.out.println("id 속성값 : " + idValue);

//    className() 으로 class 속성값 가져오기
        String classValue = imgTag.className();
        System.out.println("class 속성값 : " + classValue);

//    text()로 문자열 출력
        Elements divj99List = doc.select(".o3j99");

        for (int i = 0; i < divj99List.size(); i++) {
            Element item = divj99List.get(i);
            Elements subItemList = item.select(".MV3Tnb");

            for (int j = 0; j < subItemList.size(); j++) {
                Element tag = subItemList.get(i);
                System.out.println(tag);
                System.out.println(tag.text());
                System.out.println("----------");
            }
        }
    }
}







