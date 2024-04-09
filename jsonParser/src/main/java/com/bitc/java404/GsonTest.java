package com.bitc.java404;

import com.google.gson.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GsonTest {
//  Gson 객체 생성하기
  public void createGson() {
    // 일반 Gson 객체 생성
    Gson gson1 = new Gson();
    // 빌더 타입으로 Gson 객체 생성하기
    Gson gson2 = new GsonBuilder().create();
  }


//  Json 생성하기
  public void createJson() {
    Gson gson = new Gson();

//    Json 객체 생성
    JsonObject jobj = new JsonObject();
//    addProperty() : Json 객체에 key와 value로 이루어진 데이터를 추가
//    remove() : Json 객체에 저장된 데이터를 삭제
    jobj.addProperty("name", "아이유");
    jobj.addProperty("gender", "여성");
    jobj.addProperty("job", "가수");

//    toJson() : JsonObject 타입으로 생성된 데이터를 Json 문자열로 변환
    String jsonStr = gson.toJson(jobj);

    System.out.println(jsonStr);
  }

//  Java Object를 Json으로 변환
  public void objectToJson() {
//    Student 클래스 타입의 객체 생성 및 데이터 추가
    Student std = new Student(1, "아이유");
//    Student 클래스 타입의 객체에 저장된 내용 확인
    System.out.println("java 객체 std의 값 : " + std.toString());

//    Gson 객체 생성
    Gson gson = new Gson();
//    Student 클래스 타입의 객체를 gson을 사용하여 json 문자열로 변환
    String jsonString = gson.toJson(std);

    System.out.println(jsonString);
  }

//  Json을 Java Object로 변환
  public void jsonToObject() {
//    json 문자열
    String str = "{\"no\":\"2\", \"name\":\"유인나\"}";

//    Gson 객체 생성
    Gson gson = new Gson();
//    Student 클래스 타입의 변수 생성
//    gson을 사용하여 json 문자열을 지정한 Student 클래스 타입으로 파싱
    Student std = gson.fromJson(str, Student.class);

    System.out.println(std.toString());
  }

//  Map타입을 Json으로 변환
  public void mapToJson() {
//    HashMap 타입의 변수 생성 후 데이터 추가
    Map<String, String> mapData = new HashMap<>();
    mapData.put("no", "3");
    mapData.put("name", "유재석");

//    Gson 객체 생성
    Gson gson = new Gson();
//    gson을 사용하여 map 타입의 데이터를 json 문자열로 변환
    String jsonStr = gson.toJson(mapData);

    System.out.println(jsonStr);
  }

//  Json을 Map 타입으로 변환
  public void jsonToMap() {
//    json 문자열 데이터 생성
    String strData = "{'no':'4', 'name':'카리나'}";
    Gson gson = new Gson();

//    변환될 데이터를 저장할 Map 타입의 변수 생성
//    key는 문자열로 사용하는 것이 확실하기 때문에 String으로 설정
//    value는 어떤 데이터가 입력될지 모르기 때문에 Object 타입으로 설정
    Map<String, Object> mapData = gson.fromJson(strData, Map.class);

//    Map 타입으로 변환된 데이터를 모두 출력
    for (Map.Entry<String, Object> entry : mapData.entrySet()) {
      System.out.println(entry.getKey() + " = " + entry.getValue());
    }
  }

/**
 * 원본 json 문자열
 * "{
 * 'no':1,
 * 'students':[
 * '아이유','유인나'
 * ],
 * 'subject':{
 * 'name':'java','professor':'ihd'
 * }
 * }"
 */
public void jsonStringParser() {
//  원본 json 문자열 데이터
    String json = "{'no':1,'students':['아이유','유인나'],'subject':{'name':'java','professor':'ihd'}}";

//    Gson에서 제공하는 JsonParser를 사용하여 json 데이터를 파싱, JsonElement 객체로 반환
    JsonElement jele = JsonParser.parseString(json);

//    파싱된 결과인 JsonElement 객체에서 JsonObject 타입으로 변환
    JsonObject jobj = jele.getAsJsonObject();

//    변환된 데이터인 JsonObject객체에서 최상위 요소 중 key에 대한 value가 리터럴 데이터를 출력
    long no = jobj.get("no").getAsLong();
    System.out.println("no : " + no);

//    변환된 데이터인 JsonObject객체에서 최상위 요소 중 key에 대한 value가 JsonArray인 데이터를 출력
    JsonArray stdArr = jobj.get("students").getAsJsonArray();
//    JsonArray 타입에 저장된 모든 데이터를 반복문으로 출력
    for (int i = 0; i < stdArr.size(); i++) {
      String name = stdArr.get(i).getAsString();
      System.out.println("학생[" + i + "] : " + name);
    }

  //    변환된 데이터인 JsonObject객체에서 최상위 요소 중 key에 대한 value가 JsonObject인 데이터를 출력
    JsonObject sjObj = jobj.get("subject").getAsJsonObject();
//    출력된 JsonObject 타입의 객체에서 원하는 데이터를 출력
    String sjObjName = sjObj.get("name").getAsString();
    String sjObjProfessor = sjObj.get("professor").getAsString();
    System.out.println("과목명 : " + sjObjName);
    System.out.println("담당교수 : " + sjObjProfessor);
  }

  public void dailyBoxOfficeParser() {
    String jsonData = "{\"boxOfficeResult\":{\"boxofficeType\":\"일별 박스오피스\",\"showRange\":\"20240401~20240401\",\"dailyBoxOfficeList\":[{\"rnum\":\"1\",\"rank\":\"1\",\"rankInten\":\"0\",\"rankOldAndNew\":\"OLD\",\"movieCd\":\"20234675\",\"movieNm\":\"파묘\",\"openDt\":\"2024-02-22\",\"salesAmt\":\"435784461\",\"salesShare\":\"35.5\",\"salesInten\":\"-1014977806\",\"salesChange\":\"-70\",\"salesAcc\":\"106229301669\",\"audiCnt\":\"46030\",\"audiInten\":\"-100505\",\"audiChange\":\"-68.6\",\"audiAcc\":\"11003337\",\"scrnCnt\":\"1242\",\"showCnt\":\"4285\"},{\"rnum\":\"2\",\"rank\":\"2\",\"rankInten\":\"0\",\"rankOldAndNew\":\"OLD\",\"movieCd\":\"20231041\",\"movieNm\":\"댓글부대\",\"openDt\":\"2024-03-27\",\"salesAmt\":\"379006927\",\"salesShare\":\"30.9\",\"salesInten\":\"-852048174\",\"salesChange\":\"-69.2\",\"salesAcc\":\"5421269040\",\"audiCnt\":\"39333\",\"audiInten\":\"-83686\",\"audiChange\":\"-68\",\"audiAcc\":\"580380\",\"scrnCnt\":\"1058\",\"showCnt\":\"4091\"},{\"rnum\":\"3\",\"rank\":\"3\",\"rankInten\":\"0\",\"rankOldAndNew\":\"OLD\",\"movieCd\":\"20248496\",\"movieNm\":\"고질라 X 콩: 뉴 엠파이어\",\"openDt\":\"2024-03-27\",\"salesAmt\":\"156068247\",\"salesShare\":\"12.7\",\"salesInten\":\"-745192723\",\"salesChange\":\"-82.7\",\"salesAcc\":\"3376865761\",\"audiCnt\":\"15627\",\"audiInten\":\"-72419\",\"audiChange\":\"-82.3\",\"audiAcc\":\"336608\",\"scrnCnt\":\"813\",\"showCnt\":\"2636\"},{\"rnum\":\"4\",\"rank\":\"4\",\"rankInten\":\"0\",\"rankOldAndNew\":\"OLD\",\"movieCd\":\"20236295\",\"movieNm\":\"듄: 파트2\",\"openDt\":\"2024-02-28\",\"salesAmt\":\"94768610\",\"salesShare\":\"7.7\",\"salesInten\":\"-174939236\",\"salesChange\":\"-64.9\",\"salesAcc\":\"22035923072\",\"audiCnt\":\"7351\",\"audiInten\":\"-13759\",\"audiChange\":\"-65.2\",\"audiAcc\":\"1893376\",\"scrnCnt\":\"456\",\"showCnt\":\"711\"},{\"rnum\":\"5\",\"rank\":\"5\",\"rankInten\":\"0\",\"rankOldAndNew\":\"OLD\",\"movieCd\":\"20236714\",\"movieNm\":\"극장판 스파이 패밀리 코드 : 화이트\",\"openDt\":\"2024-03-20\",\"salesAmt\":\"28625638\",\"salesShare\":\"2.3\",\"salesInten\":\"-112902118\",\"salesChange\":\"-79.8\",\"salesAcc\":\"2372349124\",\"audiCnt\":\"2983\",\"audiInten\":\"-11429\",\"audiChange\":\"-79.3\",\"audiAcc\":\"237991\",\"scrnCnt\":\"393\",\"showCnt\":\"535\"},{\"rnum\":\"6\",\"rank\":\"6\",\"rankInten\":\"0\",\"rankOldAndNew\":\"OLD\",\"movieCd\":\"20219375\",\"movieNm\":\"1980\",\"openDt\":\"2024-03-27\",\"salesAmt\":\"20617634\",\"salesShare\":\"1.7\",\"salesInten\":\"-39964244\",\"salesChange\":\"-66\",\"salesAcc\":\"312519379\",\"audiCnt\":\"2302\",\"audiInten\":\"-4144\",\"audiChange\":\"-64.3\",\"audiAcc\":\"34539\",\"scrnCnt\":\"396\",\"showCnt\":\"574\"},{\"rnum\":\"7\",\"rank\":\"7\",\"rankInten\":\"2\",\"rankOldAndNew\":\"OLD\",\"movieCd\":\"20235838\",\"movieNm\":\"악은 존재하지 않는다\",\"openDt\":\"2024-03-27\",\"salesAmt\":\"13852898\",\"salesShare\":\"1.1\",\"salesInten\":\"-23044817\",\"salesChange\":\"-62.5\",\"salesAcc\":\"210039154\",\"audiCnt\":\"1477\",\"audiInten\":\"-1895\",\"audiChange\":\"-56.2\",\"audiAcc\":\"20785\",\"scrnCnt\":\"134\",\"showCnt\":\"192\"},{\"rnum\":\"8\",\"rank\":\"8\",\"rankInten\":\"7\",\"rankOldAndNew\":\"OLD\",\"movieCd\":\"20173226\",\"movieNm\":\"패왕별희 디 오리지널\",\"openDt\":\"2017-03-30\",\"salesAmt\":\"7004668\",\"salesShare\":\"0.6\",\"salesInten\":\"-3475701\",\"salesChange\":\"-33.2\",\"salesAcc\":\"1287422150\",\"audiCnt\":\"864\",\"audiInten\":\"-422\",\"audiChange\":\"-32.8\",\"audiAcc\":\"139937\",\"scrnCnt\":\"92\",\"showCnt\":\"122\"},{\"rnum\":\"9\",\"rank\":\"9\",\"rankInten\":\"3\",\"rankOldAndNew\":\"OLD\",\"movieCd\":\"20236732\",\"movieNm\":\"가여운 것들\",\"openDt\":\"2024-03-06\",\"salesAmt\":\"7090214\",\"salesShare\":\"0.6\",\"salesInten\":\"-9612775\",\"salesChange\":\"-57.6\",\"salesAcc\":\"1472869470\",\"audiCnt\":\"733\",\"audiInten\":\"-890\",\"audiChange\":\"-54.8\",\"audiAcc\":\"147399\",\"scrnCnt\":\"76\",\"showCnt\":\"94\"},{\"rnum\":\"10\",\"rank\":\"10\",\"rankInten\":\"11\",\"rankOldAndNew\":\"OLD\",\"movieCd\":\"20236568\",\"movieNm\":\"목스박\",\"openDt\":\"2024-03-20\",\"salesAmt\":\"5776700\",\"salesShare\":\"0.5\",\"salesInten\":\"1281222\",\"salesChange\":\"28.5\",\"salesAcc\":\"144823705\",\"audiCnt\":\"720\",\"audiInten\":\"170\",\"audiChange\":\"30.9\",\"audiAcc\":\"16460\",\"scrnCnt\":\"23\",\"showCnt\":\"24\"}]}}";

    Gson gson = new Gson();
//    미리 생성해 놓은 DTO 클래스(혹은 VO 클래스) 타입에 맞춰서 gson 라이브러리가 데이터를 파싱함
//    최상위 클래스가 BoxOfficeDTO 클래스이므로 json 문자열의 데이터가 BoxOfficeDTO 클래스 타입의 객체에 모두 저장됨
    BoxOfficeDTO boxOffice = gson.fromJson(jsonData, BoxOfficeDTO.class);
    BoxOfficeResultDTO boxOfficeResult = boxOffice.getBoxOfficeResult();
//    일간 영화 순위 정보가 있는 DailyBoxOfficeItemDto 클래스 타입의 객체를 List에 담아 모두 가져옴
    List<DailyBoxOfficeItemDTO> itemList = boxOfficeResult.getDailyBoxOfficeList();
    
    for (DailyBoxOfficeItemDTO item : itemList) {
      System.out.println("순위 : " + item.getRank() + ", 제목 : " + item.getMovieNm());
    }
    System.out.println();
  }

  public void tagoParser() {
    String jsonData = "{\"response\":{\"header\":{\"resultCode\":\"00\",\"resultMsg\":\"NORMAL SERVICE.\"},\"body\":{\"items\":{\"item\":[{\"adultcharge\":23700,\"arrplacename\":\"대전\",\"arrplandtime\":20240406061200,\"depplacename\":\"서울\",\"depplandtime\":20240406051200,\"traingradename\":\"KTX\",\"trainno\":1},{\"adultcharge\":23700,\"arrplacename\":\"대전\",\"arrplandtime\":20240406063200,\"depplacename\":\"서울\",\"depplandtime\":20240406052700,\"traingradename\":\"KTX\",\"trainno\":3},{\"adultcharge\":23700,\"arrplacename\":\"대전\",\"arrplandtime\":20240406070200,\"depplacename\":\"서울\",\"depplandtime\":20240406055700,\"traingradename\":\"KTX\",\"trainno\":5},{\"adultcharge\":23700,\"arrplacename\":\"대전\",\"arrplandtime\":20240406073800,\"depplacename\":\"서울\",\"depplandtime\":20240406063200,\"traingradename\":\"KTX\",\"trainno\":7},{\"adultcharge\":23700,\"arrplacename\":\"대전\",\"arrplandtime\":20240406074800,\"depplacename\":\"서울\",\"depplandtime\":20240406064200,\"traingradename\":\"KTX\",\"trainno\":233},{\"adultcharge\":23700,\"arrplacename\":\"대전\",\"arrplandtime\":20240406080700,\"depplacename\":\"서울\",\"depplandtime\":20240406065600,\"traingradename\":\"KTX\",\"trainno\":9},{\"adultcharge\":23700,\"arrplacename\":\"대전\",\"arrplandtime\":20240406083200,\"depplacename\":\"서울\",\"depplandtime\":20240406072700,\"traingradename\":\"KTX\",\"trainno\":11},{\"adultcharge\":23700,\"arrplacename\":\"대전\",\"arrplandtime\":20240406084700,\"depplacename\":\"서울\",\"depplandtime\":20240406074900,\"traingradename\":\"KTX\",\"trainno\":13},{\"adultcharge\":23700,\"arrplacename\":\"대전\",\"arrplandtime\":20240406090200,\"depplacename\":\"서울\",\"depplandtime\":20240406075700,\"traingradename\":\"KTX\",\"trainno\":15},{\"adultcharge\":17200,\"arrplacename\":\"대전\",\"arrplandtime\":20240406095500,\"depplacename\":\"서울\",\"depplandtime\":20240406081100,\"traingradename\":\"KTX\",\"trainno\":121}]},\"numOfRows\":10,\"pageNo\":1,\"totalCount\":66}}}";

    System.out.println("\n ----- TAGO 열차 정보 출력하기 ----- \n");

//    Gson 라이브러리 사용을 위해 Gson 객체 생성
    Gson gson = new Gson();
//    Gson 을 사용하여 TagoDTO 클래스 타입으로 Json 문자열 데이터를 파싱
    TagoDTO tago = gson.fromJson(jsonData, TagoDTO.class);
//    아래의 부분은 필요없으면 사용하지 않아도 됨
//    TagoResponseDTO response = tago.getResponse();
//    TagoHeaderDTO header = response.getHeader();
//    TagoBodyDTO body = response.getBody();
//    TagoItemsDTO items = body.getItems();
//    List<TagoItemDTO> itemList = items.getItem();
//   TagoDTO 객체에서 한번에 필요한 데이터만 출력함
    List<TagoItemDTO> itemList = tago.getResponse().getBody().getItems().getItem();

    for (TagoItemDTO item : itemList) {
      System.out.println("열차번호 : " + item.getTrainno());
      System.out.println("열차종류 : " + item.getTraingradename());
      System.out.println("출발지 : " + item.getDepplacename());
      System.out.println("출발시간 : " + item.getDepplandtime());
      System.out.println("도착지 : " + item.getArrplacename());
      System.out.println("도착시간 : " + item.getArrplandtime());
      System.out.println("열차운임 : " + item.getAdultcharge());
      System.out.println("----------------------------------");
    }
  }

  public void tagoParserUrl(String serviceUrl) throws Exception {
//  열차 정보를 저장할 list
    List<TagoItemDTO> itemList = null;

//    열차 정보를 제공하는 서비스의 url 정보를 입력하기 위한 자바에서 제공하는 URL 클래스 객체
    URL url = null;
//    지정한 URL 을 기반으로 http 프로토콜로 접속하여 데이터를 가져오는 클래스
    HttpURLConnection urlConn = null;
//    HttpURLConnection을 통해서 서비스에 접근하여 가져온 데이터를 빠르게 사용하기 위해서 BufferedReader을 사용함
    BufferedReader reader = null;

    try {
//      매개변수로 받아온 서비스 주소를 URL 클래스 타입의 객체로 생성
      url = new URL(serviceUrl);
//      openConnection() 을 사용하여 지정한 url로 접속
//      접속 정보를 HttpURLConnection 타입의 객체에 넘김
      urlConn = (HttpURLConnection) url.openConnection();
//      접속 방식을 GET 타입으로 설정
      urlConn.setRequestMethod("GET");

//      getInputStream()을 사용하여 접속한 서비스에서 데이터를 가져옴
//      BufferedReader를 사용하여 서비스를 제공하는 곳에서 데이터를 가져와서 버퍼에 저장 후 한번에 가져옴
      reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

//      StringBuilder 혹은 StringBuffer 타입을 사용하는 이유
//      String 타입은 2개 이상의 문자열을 합하여 하나의 문자열을 생성 시 기존 문자열에 새로운 문자열을 합하여 하나의 길다란 문자열을 만드는 것이 아니라, 기존의 문자열이 저장되어 있는 메모리 공간을 그대로 두고, 새로운 메모리 공간을 생성하여 하나의 길다란 문자열을 입력하여 사용자에게 제공하는 형태임
//      String 타입을 통한 빈번한 문자열의 연결은 비효율적인 메모리 사용을 하게 되기 때문에 StringBuilder, StringBuffer 타입을 사용하는 것이 좋음
//      StringBuilder, StringBuffer 타입은 객체 생성 시 만들어진 하나의 메모리 공간에서 데이터를 추가, 수정, 삭제하는 것이 가능하기 때문에 메모리를 효율적으로 사용할 수 있음
//      StringBuffer는 스레드에 안전함(멀티스레드 환경에서 사용)
//      StringBuilder는 스레드에 안전하지 않음(싱글스레드 환경에서 사용)
//      StringBuilder 타입의 객체를 생성
      StringBuilder sb = new StringBuilder();
      String line = null;

//      HttpURLConnection을 통해서 가져온 네트워크 데이터를 BufferedReader 를 통해서 데이터를 한줄씩 가져옴
      while ((line = reader.readLine()) != null) {
//        가져온 데이터를 StringBuilder 타입의 문자열 변수 sb에 추가함
        sb.append(line);
      }

      Gson gson = new Gson();
//      sb.toString()을 사용하여 가져온 json 문자열 데이터를 TagoDTO 클래스 타입으로 파싱
      TagoDTO tago = gson.fromJson(sb.toString(), TagoDTO.class);
//      TagoDTO 클래스 타입의 객체에서 필요한 데이터를 모두 출력
      itemList = tago.getResponse().getBody().getItems().getItem();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
//      사용 후 반드시 외부 리소스 해제
      if (reader != null) { reader.close(); }
      if (urlConn != null) { urlConn.disconnect(); }
    }

    System.out.println("\n ----- TAGO 열차 정보 URL에서 직접 가져와서 출력하기 ----- \n");
    for (TagoItemDTO item : itemList) {
      System.out.println("열차번호 : " + item.getTrainno());
      System.out.println("열차종류 : " + item.getTraingradename());
      System.out.println("출발지 : " + item.getDepplacename());
      System.out.println("출발시간 : " + item.getDepplandtime());
      System.out.println("도착지 : " + item.getArrplacename());
      System.out.println("도착시간 : " + item.getArrplandtime());
      System.out.println("열차운임 : " + item.getAdultcharge());
      System.out.println("----------------------------------");
    }
  }
  
  public void dailyBoxOfficeUrl(String serviceUrl) throws Exception {
//  일일 박스 오피스 리스트를 저장할 변수
    List<DailyBoxOfficeItemDTO> itemList = null;

    URL url = null; // 서비스 URL 저장 클래스
    HttpURLConnection urlConn = null; // Http 프로토콜 방식으로 서비스 url에 접속할 클래스
    BufferedReader reader = null; // 서비스 url에서 제공하는 데이터를 저장할 스트림 클래스

    try {
      url = new URL(serviceUrl); // url 설정
      urlConn = (HttpURLConnection) url.openConnection(); // 지정한 url로 접속
      urlConn.setRequestMethod("GET");

//      서비스 url에서 스트림으로 데이터 가져오기
      reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

      StringBuilder sb = new StringBuilder();
      String line = null;

//      스트림에 저장된 데이터를 한줄씩 읽어오기, 모두 읽어오면 json 문자열이 됨
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }

      Gson gson = new Gson();
//      가져온 json 문자열을 BoxOfficeDTO 클래스 타입의 객체로 변환
      BoxOfficeDTO boxOffice = gson.fromJson(sb.toString(), BoxOfficeDTO.class);
//      필요한 정보만 가져오기
      itemList = boxOffice.getBoxOfficeResult().getDailyBoxOfficeList();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (reader != null) { reader.close(); }
      if (urlConn != null) { urlConn.disconnect(); }
    }

    System.out.println("\n ----- 영화진흥원 일일 박스 오피스 ----- \n");
    for (DailyBoxOfficeItemDTO item : itemList) {
      System.out.println("영화 순위 : " + item.getRank());
      System.out.println("영화 코드 : " + item.getMovieCd());
      System.out.println("영화 이름 : " + item.getMovieNm());
      System.out.println("개봉일 : " + item.getOpenDt());
      System.out.println("일일 관람객 : " + item.getAudiCnt());
      System.out.println("누적 관람객 : " + item.getAudiAcc());
      System.out.println("----------------------------------");
    }
  }

  public void xmlToObject() throws Exception {
//    JAXB 라이브러리를 사용하여 XML 파싱
//    jaxb : 자바에서 xml 데이터 파싱을 도와주는 라이브러리
//    jaxb가 Java9부터 자바 기본 라이브러리에서 제외됨
//    json 문자열 파싱과 동일하게 미리 해당 데이터와 맞는 DTO(혹은 VO) 클래스를 선언하여 XML 데이터를 파싱
//    종속성 추가가 필요함
//      jakarta.xml.bind-api
//      jakarta.activation-api
//      jaxb-impl

//    Marshal : 자바 클래스를 XML 데이터로 변환
//    UnMarshal : XML 데이터를 자바 클래스 타입의 객체로 변환

    JAXBContext jc = JAXBContext.newInstance(PharmacyDTO.class);
    Unmarshaller um = jc.createUnmarshaller();

    PharmacyDTO pharmacy = (PharmacyDTO) um.unmarshal(new File("C:\\java404\\pharmacy.xml"));
    List<PharmacyItemDTO> itemList = pharmacy.getBody().getItems().getItem();

    for (PharmacyItemDTO item : itemList) {
      System.out.println("약국 이름 : " + item.getDutyName());
      System.out.println("전화 번호: " + item.getDutyTel1());
      System.out.println("전화 주소: " + item.getDutyAddr());
      System.out.println("시작 시간 : " + item.getDutyTime1s());
      System.out.println("종료 시간 : " + item.getDutyTime1c());
      System.out.println("-------------------------");
    }
  }


  public void xmlToObjectUrl(String serviceUrl) throws Exception {
    List<PharmacyItemDTO> itemList = null;

    URL url = null;
    HttpURLConnection urlConn = null;

    try {
      url = new URL(serviceUrl);
      urlConn = (HttpURLConnection) url.openConnection();
      urlConn.setRequestMethod("GET");

      JAXBContext jc = JAXBContext.newInstance(PharmacyDTO.class);
      Unmarshaller um = jc.createUnmarshaller();

      PharmacyDTO pharmacy = (PharmacyDTO) um.unmarshal(url);
      itemList = pharmacy.getBody().getItems().getItem();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (urlConn != null) { urlConn.disconnect(); }
    }

    System.out.println("\n ----- 전국 약국 정보 api ----- \n");

    for (PharmacyItemDTO item : itemList) {
      System.out.println("약국 이름 : " + item.getDutyName());
      System.out.println("전화 번호: " + item.getDutyTel1());
      System.out.println("전화 주소: " + item.getDutyAddr());
      System.out.println("시작 시간 : " + item.getDutyTime1s());
      System.out.println("종료 시간 : " + item.getDutyTime1c());
      System.out.println("-------------------------");
    }
  }
}












