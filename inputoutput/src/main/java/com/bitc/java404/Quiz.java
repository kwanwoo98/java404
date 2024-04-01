package com.bitc.java404;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Quiz {
  private Scanner sc; // 사용자 입력을 위한 객체

  public Quiz() {
    sc = new Scanner(System.in);
  }

  //  문제 1) 사용자 입력을 통해서 파일 이름을 입력받고 해당 파일의 내용을 화면에 출력하는 프로그램을 작성하세요
//  파일명 : quiz1.txt
//  내용 : 영어로 아무꺼나 입력
  public void quiz1() {
    System.out.println("\n----- quiz1 -----\n");

    System.out.print("오픈할 파일의 이름을 입력하세요 : ");
    String fileName = sc.next(); // 파일이름 받기

    InputStream is = null; // InputStream 객체
    try {
      is = new FileInputStream("C:\\java404\\" + fileName);

      int readByteNo; // 가져온 데이터 수를 저장
      byte[] readBytes = new byte[5]; // 가져온 데이터를 저장하는 배열
      String data = ""; // 화면 출력용 변수

      while (true) {
//        byte[] 배열에 있는 모든 데이터 한번에 읽어오기
        readByteNo = is.read(readBytes);

//        가져온 데이터가 없으면 종료
        if (readByteNo == -1) { break; }
        data += new String(readBytes);
      }

      System.out.print("파일 내용 : " + data);
      System.out.println("\n\n");
    }
    catch (FileNotFoundException e) {
      errMsg("open", e);
    }
    catch (IOException e) {
      errMsg("read", e);
    }
//    stream 사용 시 반드시 stream을 해제해야 하기 때문에 finally를 사용하여 제거
    finally {
      try {
//        stream이 null인지 확인 후 close()로 제거
        if (is != null) { is.close(); }
      }
      catch(Exception e){
      }
    }
  }

  //  문제 2) 기존에 존재하는 파일을 열고 사용자 입력을 통해서 입력받은 내용을 기존 파일의 뒤에 추가하는 프로그램을 작성하세요
//  파일명 : quiz2.txt
//  원본 내용 : network programing
  public void quiz2() {
    System.out.println("\n----- quiz2 -----\n");

    System.out.print("오픈할 파일의 이름을 입력하세요 : ");
    String fileName = sc.next();

    InputStream is = null; // 파일 읽기용 stream
    OutputStream os = null; // 파일 쓰기용 stream
    String readData = ""; // 읽어온 데이터 저장용 변수
    String writeData = ""; // 키보드 입력받은 데이터 저장용 변수
    
//    파일 읽기 시작
    try {
      is = new FileInputStream("C:\\java404\\" + fileName);

      int readByteNo;
      byte[] readBytes = new byte[5];

      while (true) {
        readByteNo = is.read(readBytes); // 파일에서 데이터 가져오기

        if (readByteNo == -1) { break; } // 더이상 읽은 데이터가 없으면 반복문 종료
//        파일에서 읽어온 byte[] 타입 데이터를 String 타입으로 변환
//        String 타입의 변수에 저장
        readData += new String(readBytes);
      }
    }
    catch (FileNotFoundException e) {
      errMsg("open", e);
    }
    catch (IOException e) {
      errMsg("read", e);
    }
    finally {
      try {
        if (is != null) { is.close(); }
      }
      catch(IOException e){ }
    }

//    사용자가 키보드로 추가할 내용 입력
    System.out.println("추가할 내용을 입력하세요.");

//    반복문이 종료되때까지 계속 입력
    while (true) {
      System.out.print(">> ");
      String data = sc.next(); // 키보드 입력 받기

//      키보드 입력 데이터 중 quit가 들어오면 입력 종료
      if (data.equals("quit")) {
        break;
      }

//      변수 writeData 에 키보드 입력 데이터를 계속 저장함
      writeData += data;
    }

//    입력 받은 데이터 파일에 쓰기
    try {
//      OutputStream 객체 생성, 파일 열기
      os = new FileOutputStream("C:\\java404\\" + fileName);
//      파일에서 읽어온 데이터와 키보드로 입력받은 데이터를 합함
      writeData = readData + writeData;
//      데이터를 byte[] 배열로 변경
      byte[] writeBytes = writeData.getBytes();
      // 파일 쓰기
      os.write(writeBytes);
      System.out.println("*** 파일에 저장했습니다.***");
    } catch (FileNotFoundException e) {
      errMsg("open", e);
    }
    catch (IOException e) {
      errMsg("write", e);
    }
    finally {
      try {
        if (os != null) {
          os.flush();
          os.close();
        }
      }
      catch (Exception e) { }
    }

    System.out.println("\n\n");
  }

  //  문제 3) 사용자 입력을 통해서 파일의 원본 파일의 이름과 사본 파일의 이름을 입력받고, 원본 파일의 내용을 사본 파일에 저장하는 프로그램을 작성하세요
//  원본 파일명 : quiz3.txt
//  원본 내용 : network programing quiz3
//  사본 파일명 : quiz3_copy.txt
  public void quiz3() {
    System.out.println("\n----- quiz3 -----\n");

    System.out.print("원본 파일의 이름을 입력하세요 : ");
    String fileName = sc.next(); // 원본 파일명 받아오기

//    파일 읽기용 stream 객체
    InputStream is = null;
//    파일 쓰기용 stream 객체
    OutputStream os = null;
//    파일에서 읽어온 내용을 저장할 변수
    String readData = "";
    
//    파일 읽기 시작
    try {
      is = new FileInputStream("C:\\java404\\" + fileName);

      int readByteNo;
      byte[] readBytes = new byte[5];

      while (true) {
        readByteNo = is.read(readBytes);

        if (readByteNo == -1) { break; } // 더이상 파일에서 읽어올 내용이 없을때까지 읽기
//        읽어온 byte[] 배열 데이터를 String 타입의 데이터로 변환 후 String 변수 readData에 저장
        readData += new String(readBytes);
      }
    }
    catch (FileNotFoundException e) {
      errMsg("open", e);
    }
    catch (IOException e) {
      errMsg("read", e);
    }
    finally {
      try {
        if (is != null) { is.close(); }
      }
      catch(IOException e){ }
    }

//    사본 파일에 저장하기 위해서 사본 파일명 입력 받기
    System.out.print("사본 파일의 이름을 입력하세요 : ");
    fileName = sc.next();

    try {
      os = new FileOutputStream("C:\\java404\\" + fileName);
//      프로그램 시작 시 받아온 파일 데이터를 통하여 byte[] 타입의 변수에 저장
      byte[] writeBytes = readData.getBytes();
//      가져온 데이터를 파일에 쓰기
      os.write(writeBytes);
      System.out.println("*** 파일을 복사했습니다.***");
    } catch (FileNotFoundException e) {
      errMsg("open", e);
    }
    catch (IOException e) {
      errMsg("write", e);
    }
    finally {
      try {
        if (os == null) {
          os.flush();
          os.close();
        }
      }
      catch (Exception e) { }
    }

    System.out.println("\n\n");
  }

  private void errMsg(String type, Exception e) {
    switch (type) {
      case "open":
        System.out.println("파일을 여는 도중 오류가 발생했습니다.");
        break;

      case "read":
        System.out.println("파일을 읽는 도중 오류가 발생했습니다.");
        break;

      case "write":
        System.out.println("파일에 쓰는 도중 오류가 발생했습니다.");
        break;
    }

    e.printStackTrace();
  }
}
