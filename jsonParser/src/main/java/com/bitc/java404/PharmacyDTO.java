package com.bitc.java404;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class PharmacyDTO {
  private PharmacyHeaderDTO header;
  private PharmacyBodyDTO body;

  @XmlElement(name = "header")
  public PharmacyHeaderDTO getHeader() {
    return header;
  }

  public void setHeader(PharmacyHeaderDTO header) {
    this.header = header;
  }

  @XmlElement(name = "body")
  public PharmacyBodyDTO getBody() {
    return body;
  }

  public void setBody(PharmacyBodyDTO body) {
    this.body = body;
  }
}
