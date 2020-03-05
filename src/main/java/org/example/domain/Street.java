package org.example.domain;

import io.ebean.annotation.Cache;
import org.example.domain.parent.StreetParent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "street")
@DiscriminatorValue(value="1")
public class Street extends StreetParent {

  public String number;

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }
}
