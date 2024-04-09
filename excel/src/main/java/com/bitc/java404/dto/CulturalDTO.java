package com.bitc.java404.dto;

public class CulturalDTO {
//  문제2) 주어진 엑셀 파일의 내용을 데이터베이스에 저장하는 프로그램을 작성
//  엑셀파일 cultural.xls
//  DTO클래스 생성 CulturalDTO
//  DB 테이블 cultural
//  컬럼 : name(문화재명), addr1(소재지), care(관리주체), code(지정번호), reg_dt(지정일자), age(시대), type(종별),
//  care_tel(담당자번호), addr2(구군명), upd_dt(데이터기준일자), lat(위도), lng(경도)

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getCare() {
        return care;
    }

    public void setCare(String care) {
        this.care = care;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReg_dt() {
        return reg_dt;
    }

    public void setReg_dt(String reg_dt) {
        this.reg_dt = reg_dt;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCare_tel() {
        return care_tel;
    }

    public void setCare_tel(String care_tel) {
        this.care_tel = care_tel;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getUpd_dt() {
        return upd_dt;
    }

    public void setUpd_dt(String upd_dt) {
        this.upd_dt = upd_dt;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
