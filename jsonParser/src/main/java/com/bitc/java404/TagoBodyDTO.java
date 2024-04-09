package com.bitc.java404;

public class TagoBodyDTO {
  private TagoItemsDTO items;
  private int numOfRows;
  private int pageNo;
  private int totalCount;

  public TagoItemsDTO getItems() {
    return items;
  }

  public void setItems(TagoItemsDTO items) {
    this.items = items;
  }

  public int getNumOfRows() {
    return numOfRows;
  }

  public void setNumOfRows(int numOfRows) {
    this.numOfRows = numOfRows;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }
}
