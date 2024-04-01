package com.bitc.java404;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// 문제1) CoffeeBean 사이트 크롤링을 참고하여 스타벅스 사이트의 매장 정보를 가져오는 프로그램을 작성하세요
// # 매장명, 매장주소, 매장 전호번호를 출력
// # 매장 번호 1 ~ 10까지만 사용
public class StarBucks extends WebBase {

  public StarBucks() {
    System.setProperty(WebBase.CHROME_DRIVER_ID, WebBase.CHROME_DRIVER_PATH);
    options = chromeOptionSetup();
    driver = new ChromeDriver(options);
  }

  public ChromeOptions chromeOptionSetup() {
    ChromeOptions opt = new ChromeOptions();
    opt.addArguments("--start-maximized");
    opt.addArguments("--disable-popup-blocking");
    opt.addArguments("--remote-allow-origins=*");

    return opt;
  }

  public void getStoreInfo() throws InterruptedException {
//    스타벅스 매장 찾기 주소 설정
    requestUrl = "https://www.starbucks.co.kr/store/store_map.do";

    JavascriptExecutor js = (JavascriptExecutor) driver;

    for (int i = 0; i < 10; i++) {
      driver.get(requestUrl);
      Thread.sleep(1000);

      try {
//        스타벅스의 매장 상세 정보 보기 자바스크립트 함수가 getStoreDetail() 임
        String script = String.format("getStoreDetail(%d);", i);
//        String s = "getStoreDetail(" + i + ")";
        js.executeScript(script);
        Thread.sleep(500);

        String html = driver.getPageSource();

//        가져온 html 문자열을 Jsoup으로 파싱
        Document doc = Jsoup.parse(html);
//        매장 상세 정보 부분을 검색, css 선택자로 태그명과 class 값을 함께 사용하여 검색
        Element popup_inner = doc.select("section.shopArea_pop01_inner").first();

//        제목을 저장하고 있는 h6 태그를 한번에 검색하여 저장
        Element titl = popup_inner.select("header.titl").first().select("h6").first();

//        주소 및 전화번호 정보가 있는 dd 태그를 한번에 검색하여 저장
        Elements storeInfoList = popup_inner.select("div.shopArea_infoWrap").first().select("dd");
        Element addr = storeInfoList.get(0); // 주소
        Element tel = storeInfoList.get(1); // 전화번호

//        text()를 사용하여 필요한 데이터를 가져옴
        String storeName = titl.text();
        String storeAddr = addr.text();
        String storeTel = tel.text();

//        화면 출력
        System.out.println("매장명(" + i + ") : " + storeName);
        System.out.println("매장주소 : " + storeAddr);
        System.out.println("전화번호 : " + storeTel);
        System.out.println("\n------------------------\n");
      }
      catch (Exception e) {
      }
    }

    driver.quit();
  }
}
