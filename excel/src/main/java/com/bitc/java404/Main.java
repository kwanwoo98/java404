package com.bitc.java404;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("Hello world!");

//    UseExcel useExcel = new UseExcel();
//    useExcel.readExcel("C:\\java404\\test.xlsx");
//    useExcel.interiorList("c:\\java404\\interior.xlsx");

//    Scanner sc = new Scanner(System.in);
//    System.out.print("엑셀 파일의 경로를 입력하세요 : ");
//    String fileName = sc.nextLine();
//
//    useExcel.culturalList(fileName);

//    엑셀 데이터 DB에 저장하기
    System.out.println("\n ----- 엑셀 데이터를 DB에 저장하기 ----- \n");

//    키보드 입력
//    Scanner sc = new Scanner(System.in);
//    System.out.print("엑셀 파일의 경로를 입력하세요 >> ");
////    파일 전체 경로 입력받기
//    String fileName = sc.nextLine();
//
////    프로그램 실행
//    UseExcel useExcel = new UseExcel();
//    useExcel.interiorDataInfo(fileName);

//    문제2) 엑셀 데이터 DB에 저장하기
    Scanner sc = new Scanner(System.in);
    System.out.print("엑셀 파일의 경로를 입력하세요 >> ");
//    파일 전체 경로 입력받기
    String fileName = sc.nextLine();

//    프로그램 실행
    UseExcel useExcel = new UseExcel();
    useExcel.culturalInfo(fileName);
  }
}











