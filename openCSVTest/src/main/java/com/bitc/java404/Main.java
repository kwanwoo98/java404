package com.bitc.java404;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

  public static void writeData(String fileName) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter(fileName));

    String[] data1 = "아이유#가수#여성".split("#");
    writer.writeNext(data1);

    String[] data2 = {"유인나", "배우", "여성"};
    writer.writeNext(data2);

    String[] data3 = {"유재석", "코메디언", "남성"};
    writer.writeNext(data3);

    writer.close();
  }

  public static void readData(String fileName) throws IOException, CsvValidationException {
    CSVReader reader = new CSVReader(new FileReader(fileName));

    String[] nextLine;

    while ((nextLine = reader.readNext()) != null) {
      for (int i = 0; i < nextLine.length; i++) {
        System.out.println(i + " " + nextLine[i]);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    System.out.println("\n----- OpenCsv 라이브러리로 csv 파일 읽고 쓰기 -----\n");

//    CSVWriter : OpenCsv 라이브러리에서 제공하는 클래스로 csv 파일을 생성 및 내용 쓰기 시 사용하는 클래스
//    writeNext(문자열) : 지정한 문자열의 배열을 csv 파일에 쓰기, 한줄 쓰기
//    writeAll(List<String[]>) : 지정한 List<String[] > 타입의 데이터를 csv 파일에 한번에 모두 쓰기
//    close() : csv 파일을 닫기
//    CSVReader : OpenCsv 라이브러리에서 제공하는 클래스로 csv 파일의 내용을 읽을 경우 사용하는 클래스 
//    readNext() : csv 파일의 데이터를 한 라인씩 가져와서 배열에 저장
//    readAll() : csv 파일의 데이터를 모두 가져와서 List<String[] > 타입에 한번에 저장
//    close() : csv 파일 닫기

    try {
      writeData("c:\\java404\\test.csv");
      readData("c:\\java404\\test.csv");
    }
    catch (Exception e) {

    }
  }
}








