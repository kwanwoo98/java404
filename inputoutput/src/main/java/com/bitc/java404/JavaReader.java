package com.bitc.java404;

import java.io.FileReader;
import java.io.Reader;

public class JavaReader {
//  read() 사용하기
  public void read1() throws Exception {
//    문자를 읽어오는 Reader 클래스를 사용하여 파일을 열기
//    Reader 클래스는 추상 클래스이므로 Reader 클래스를 상속받은 FileReader 클래스 타입의 객체를 Reader 타입의 변수에 저장하여 사용함
    Reader reader = new FileReader("C:\\java404\\test2.txt");
    int readData; // 읽어온 문자를 저장할 변수

//    반복문을 통해서 문자을 계속 읽어옴
    while (true) {
//      read() 메소드를 사용하여 하나의 문자을 파일에서 읽어옴
//      읽어온 데이터가 -1 이면 읽은 데이터 없음
      readData = reader.read();
      if (readData == -1) {
        break;
      }
//      읽어온 데이터가 int 타입이지만 문자로 표현하기 위해서 char 타입으로 강제 타입 변환
      System.out.print((char)readData);
    }
//    Reader도 외부 리소스이기 때문에 사용 후 반드시 닫아줘야 함
    reader.close();
  }

//  read(char[] cBuff) 사용하기
  public void read2(String fileName) throws Exception {
//    Reader 를 통해서 파일 열기
    Reader reader = new FileReader(fileName);
    int readCharNo; // 읽어온 문자의 수를 저장할 변수
    char[] cBuff = new char[2]; // 읽어온 데이터를 저장할 char[] 배열
    String data = ""; // 읽어온 데이터를 문자열로 출력하기 위한 변수

//    반복문을 통해서 파일의 내용을 계속 읽어옴
    while (true) {
//      read(char[] cBuff) 를 사용하여 지정한 char[] 배열의 크기만큼 파일에서 문자를 읽어옴
      readCharNo = reader.read(cBuff);
      if (readCharNo == -1) { break; } // 더이상 읽어온 데이터가 없으면 종료
      data += new String(cBuff, 0, readCharNo); // 읽어온 데이터를 String 타입으로 변환 후 저장
    }

    System.out.println(data);
    reader.close();
  }

//  read(char[] cBuff, int off, int len) 사용하기
  public void read3(String fileName) throws Exception {
//    FileReader를 통해서 파일 열기
    Reader reader = new FileReader(fileName);
    int readCharNo; // 읽어온 문자의 수를 저장하기 위한 변수
    char[] cbuff = new char[4]; // 파일에서 읽어온 데이터를 저장할 char[] 배열
//    read(char[] cBuff, int off, int len) 을 사용하여 파일에서 데이터를 읽어와서 지정한 배열에 저장 시 offset으로 지정한 위치에 length 크기만큼 저장함
    readCharNo = reader.read(cbuff, 1, 2);

    for (int i = 0; i <cbuff.length; i++) {
      System.out.println(cbuff[i]);
    }
    reader.close();
  }
}












