package com.bitc.java404;

import java.util.List;

public class BoxOfficeResultDTO {
  private String boxofficeType;
  private String showRange;
  private List<DailyBoxOfficeItemDTO> dailyBoxOfficeList;

  public String getBoxofficeType() {
    return boxofficeType;
  }

  public void setBoxofficeType(String boxofficeType) {
    this.boxofficeType = boxofficeType;
  }

  public String getShowRange() {
    return showRange;
  }

  public void setShowRange(String showRange) {
    this.showRange = showRange;
  }

  public List<DailyBoxOfficeItemDTO> getDailyBoxOfficeList() {
    return dailyBoxOfficeList;
  }

  public void setDailyBoxOfficeList(List<DailyBoxOfficeItemDTO> dailyBoxOfficeList) {
    this.dailyBoxOfficeList = dailyBoxOfficeList;
  }
}
