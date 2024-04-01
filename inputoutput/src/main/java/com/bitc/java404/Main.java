package com.bitc.java404;

import java.io.File;
import java.io.IOException;
import java.io.StringBufferInputStream;

public class Main {
  public static void main(String[] args) {
    System.out.println("자바 input/output stream 사용하기");

    JavaInputStream jis = new JavaInputStream();
    try {
      System.out.println("\n----- read() -----\n");
      jis.read1();
      System.out.println("\n----- read(byte[] buff) -----\n");
      jis.read2();
      System.out.println("\n----- read(byte[] buff, int offset, int length) -----\n");
      jis.read3();
    }
    catch (IOException e) {
      System.out.println("파일을 읽는 도중 오류가 발생했습니다.");
      e.printStackTrace();
    }

    JavaOutputStream jos = new JavaOutputStream();
    try {
      System.out.println("\n----- write1() -----\n");
      jos.write1("C:\\java404\\testwrite1.txt");
      System.out.println("\n----- write2() -----\n");
      jos.write2("C:\\java404\\testwrite2.txt");
      System.out.println("\n----- write3() -----\n");
      jos.write3("C:\\java404\\testwrite3.txt");

    }
    catch (IOException e) {
      System.out.println("파일에 쓰는 도중 오류가 발생했습니다.");
      e.printStackTrace();
    }

    Quiz quiz = new Quiz();
//    quiz.quiz1();
//    quiz.quiz2();
//    quiz.quiz3();

    JavaReader jr = new JavaReader();
    try {
      System.out.println("\n----- read1() -----\n");
      jr.read1();
      System.out.println("\n----- read2() -----\n");
      jr.read2("c:\\java404\\test2.txt");
      System.out.println("\n----- read3() -----\n");
      jr.read3("c:\\java404\\test2.txt");
    }
    catch (Exception e) {
      System.out.println("오류가 발생했습니다.");
    }

    JavaWriter jw = new JavaWriter();
    try {
      System.out.println("\n----- write1() -----\n");
      jw.write1("C:\\java404\\test3write1.txt");

      System.out.println("\n----- write2() -----\n");
      jw.write2("C:\\java404\\test3write2.txt");

      System.out.println("\n----- write3() -----\n");
      jw.write3("C:\\java404\\test3write3.txt");

      System.out.println("\n----- write4() -----\n");
      jw.write4("C:\\java404\\test3write4.txt");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }


    try {
      File file = new File("C:\\java404\\testFile3.txt");

      if (file.exists()) {
        System.out.println("기존의 파일이 존재합니다.");
      }
      else {
        System.out.println("새 파일을 생성합니다.");
        file.mkdir();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    try {
      File file = new File("c:\\java404");
      String[] fileList = file.list();

      for (int i = 0; i < fileList.length; i++) {
        System.out.println("이름 : " + fileList[i]);
      }
    }
    catch (Exception e) {

    }

    Buffered buf = new Buffered();
    try {
      System.out.println("\n ----- 버퍼 없이 실행 ----- \n");
      buf.inputStreamEx();
      System.out.println("\n ----- 버퍼로 실행 ----- \n");
      buf.bufInputStreamEx();
      System.out.println("\n ----- 키보드 입력을 버퍼로 실행 ----- \n");
      buf.buffReaderEx();
    }
    catch (Exception e) {}

  }
}











