package com.bitc.java404;

import java.io.*;

public class Buffered {
  public void inputStreamEx() throws Exception {
//    forest.jpg 파일을 읽어옴
    FileInputStream fis1 = new FileInputStream("c:\\java404\\forest.jpg");
//    시작 시간 설정
    long start = System.currentTimeMillis();

//    파일 내용을 1byte씩 읽기
    while (fis1.read() != -1) { }
//    종료 시간 설정
    long end = System.currentTimeMillis();

//    파일의 내용을 모두 읽어오는데 사용한 시간
    System.out.println("사용하지 않았을 경우 : " + (end - start) + "ms");
    fis1.close();
  }

  public void bufInputStreamEx() throws Exception {
//    FileInputStream으로 forest.jpg 파일을 염
    FileInputStream fis2 = new FileInputStream("c:\\java404\\forest.jpg");
//    가져온 파일을 BufferedInputStream 클래스 타입의 객체에 저장
    BufferedInputStream bis2 = new BufferedInputStream(fis2);
//    시작 시간 설정
    long start = System.currentTimeMillis();

//    파일 내용을 1byte씩 불러옴
    while (bis2.read() != -1) { }

//    종료 시간 설정
    long end = System.currentTimeMillis();

//    파일 내용을 모두 읽는데 걸리는 시간 확인
    System.out.println("사용했을 경우 : " + (end - start) + "ms");
    bis2.close();
    fis2.close();
  }

  public void buffReaderEx() throws Exception {
//    키보드 입력 받기, 1byte씩 데이터를 받음
    InputStream is = System.in;
//    InputStreamReader의 생성자에 InputStream 객체를 사용함, 1문자씩 읽어오는 형태로 변환
    Reader reader = new InputStreamReader(is);
//    InputStreamReader를 BufferedReader에 입력하여 사용, 입력 속도가 빨라짐
    BufferedReader br = new BufferedReader(reader);

    System.out.print("입력 : ");
    String lineString = br.readLine();

    System.out.println("출력 : " + lineString);
  }
}













