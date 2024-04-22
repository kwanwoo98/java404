package com.bitc.java404.dto;

import java.time.LocalDate;

public class CulturalDTO {
  private String name;
  private String addr1;
  private String care;
  private String code;
  private String reg_dt;
  private String age;
  private String type;
  private String care_tel;
  private String addr2;
  private String upd_dt;
  private String lat;
  private String lng;

  public String getLng() {
    return lng;
  }

  public void setLng(String lng) {
    this.lng = lng;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getUpd_dt() {
    return upd_dt;
  }

  public void setUpd_dt(String upd_dt) {
    this.upd_dt = upd_dt;
  }

  public String getAddr2() {
    return addr2;
  }

  public void setAddr2(String addr2) {
    this.addr2 = addr2;
  }

  public String getCare_tel() {
    return care_tel;
  }

  public void setCare_tel(String care_tel) {
    this.care_tel = care_tel;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getReg_dt() {
    return reg_dt;
  }

  public void setReg_dt(String reg_dt) {
    this.reg_dt = reg_dt;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCare() {
    return care;
  }

  public void setCare(String care) {
    this.care = care;
  }

  public String getAddr1() {
    return addr1;
  }

  public void setAddr1(String addr1) {
    this.addr1 = addr1;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
