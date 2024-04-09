package com.bitc.java404.dto;

import java.util.List;

public class BoxOfficeResult {
  private String boxofficeType;
  private String showRange;
  private List<DailyBoxOfficeList> dailyBoxOfficeList;

  public String getBoxofficeType() { return boxofficeType; }
  public void setBoxofficeType(String value) { this.boxofficeType = value; }

  public String getShowRange() { return showRange; }
  public void setShowRange(String value) { this.showRange = value; }

  public List<DailyBoxOfficeList> getDailyBoxOfficeList() { return dailyBoxOfficeList; }
  public void setDailyBoxOfficeList(List<DailyBoxOfficeList> value) { this.dailyBoxOfficeList = value; }
}







