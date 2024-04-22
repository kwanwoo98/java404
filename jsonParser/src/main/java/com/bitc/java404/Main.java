package com.bitc.java404;

import com.google.gson.Gson;

public class Main {
  public static void main(String[] args) {
    System.out.println("JSON 파싱하기");

//    JSON이란 : javascript object notation 의 약자로 자바스크립트 객체 표기법이라는 의미로 데이터를 쉽게 전달하고 저장하기 위한 텍스트 기반의 데이터 교환 표준을 의미함
//    자바스크립트의 object 타입을 흉내내서 문자열로 만들어 놓은 것
//    key:value 형태로 데이터를 저장하고, 각각의 데이터는 , 로 구분
//    value로 값 자체를 입력할 수 있고, value로 배열[]이나, object{} 타입을 입력할 수 있음
//    자바에서 직접 JSON을 사용할 수 없기 때문에 JSON 데이터를 파싱하기 위한 라이브러리가 필요함

//    GSON : 구글에서 개발한 자바용 JSON 파싱 라이브러리

    GsonTest gt = new GsonTest();
    gt.createJson();
    gt.objectToJson();
    gt.jsonToObject();
    gt.mapToJson();
    gt.jsonToMap();
    System.out.println("\n\n");
    gt.jsonStringParser();

    System.out.println("\n\n");
    gt.dailyBoxOfficeParser();
    gt.tagoParser();

    try {
      String url = "https://apis.data.go.kr/1613000/TrainInfoService/getStrtpntAlocFndTrainInfo";
      String serviceKey = "?serviceKey=";
      String basic = "&depPlaceId=NAT010000&arrPlaceId=NAT014445";
      String opt1 = "&depPlandTime=";
      String opt2 = "&trainGradeCode=";
      String opt3 = "&pageNo=";
      String opt4 = "&numOfRows=";
      String opt5 = "&_type=";
      String myKey = "MQ8moCpDCqz42c3kxwz2LHfm%2BevXvXYOTYnrZjpLTYgYqArm4QD7hxWxCGM%2F24BBmgODLVMFUM6mAAH4XCFQcg%3D%3D";
      String serviceUrl = url + serviceKey + myKey + basic + opt1 + "20240405" + opt2 + "00" + opt3 + "1" + opt4 + "20" + opt5 + "json";

      gt.tagoParserUrl(serviceUrl);
    }
    catch (Exception e) {

    }

//    문제 1) 영화 진흥원 API 중 일간 박스 오피스 API를 사용하여 지정한 날짜의 박스 오피스 내용을 가져와서 화면에 출력하는 프로그램을 작성하세요
//    메소드명 : dailyBoxOfficeUrl(String url);
//    main 메소드에서 dailyBoxOfficeUrl()로 url 전달 시 날짜를 입력할 수 있도록 작성
//    출력 정보 : 랭크, 영화 코드, 영화명, 개봉일, 일일 관람객 수, 누적 관람객 수
    try {
      String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
      String serviceKey = "?key=";
      String opt1 = "&targetDt=";
      String myKey = "f5eef3421c602c6cb7ea224104795888";
      String serviceUrl = url + serviceKey + myKey + opt1 + "20230401";

      gt.dailyBoxOfficeUrl(serviceUrl);
    }
    catch (Exception e) {

    }

    try {
      gt.xmlToObject();
    }
    catch (Exception e) {

    }

    try {
      String url = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown";
      String serviceKey = "?serviceKey=";
      String myKey = "MQ8moCpDCqz42c3kxwz2LHfm%2BevXvXYOTYnrZjpLTYgYqArm4QD7hxWxCGM%2F24BBmgODLVMFUM6mAAH4XCFQcg%3D%3D";
      String opt1 = "&pageNo=";
      String opt2 = "&numOfRows=";
      String serviceUrl = url + serviceKey + myKey + opt1 + "3" + opt2 + "20";

      gt.xmlToObjectUrl(serviceUrl);
    }
    catch (Exception e) {

    }
  }
}





