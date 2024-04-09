package com.bitc.java404;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "items")
public class PharmacyItemsDTO {
  private List<PharmacyItemDTO> item;

  @XmlElement(name = "item")
  public List<PharmacyItemDTO> getItem() {
    return item;
  }

  public void setItem(List<PharmacyItemDTO> item) {
    this.item = item;
  }
}
