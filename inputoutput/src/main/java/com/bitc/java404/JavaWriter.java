package com.bitc.java404;

import java.io.FileWriter;
import java.io.Writer;

public class JavaWriter {
  public void write1(String fileName) throws Exception {
//    부모 클래스인 Write 클래스 타입의 변수에 자식 클래스인 FileWriter 클래스 타입의 객체를 저장
//    부모 클래스인 Write는 추상 클래스
    Writer writer = new FileWriter(fileName);
//    입력된 문자열을 char[] 배열 타입으로 변환 후 저장
    char[] data = "홍길동".toCharArray();

//    변환된 데이터의 크기 만큼 반복문을 실행
    for (int i = 0; i < data.length; i++) {
//      write() 사용하여 파일에 문자를 한자씩 입력함
      writer.write(data[i]);
    }

    writer.flush();
    writer.close();
  }

  public void write2(String fileName) throws Exception {
    Writer writer = new FileWriter(fileName);
    char[] data = "홍길동".toCharArray();
//    writer(char[] cBuff) 를 사용하여 char[] 배열에 저장된 내용을 한번에 파일에 쓰기
    writer.write(data);

    writer.flush();
    writer.close();
  }

  public void write3(String fileName) throws Exception {
    Writer writer = new FileWriter(fileName);
    char[] data = "아이유인나유재석".toCharArray();
//    write(char[] cBuff, int off, int len) 을 사용하여 char[] 배열에 저장된 내용 중 지정한 위치에서 지정한 크기의 데이터만 파일에 쓰기
    writer.write(data, 2, 3);

    writer.flush();
    writer.close();
  }

  public void write4(String fileName) throws Exception {
    Writer writer = new FileWriter(fileName);
    String data = "아이유인나유재석";
//    write(String s) 를 사용하여 문자열 데이터를 한번에 파일에 씀
//    writer.write(data);
//    write(String s, int off, int len) 를 사용하여 문자열 데이터 중 지정한 위치에서 지정한 크기의 데이터를 파일에 씀
    writer.write(data, 2, 3);

    writer.flush();
    writer.close();
  }
}











