package org.example.domain.parent;

import org.example.domain.parent.BaseModel;
import org.example.domain.parent.StreetParent;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address extends BaseModel {

  @ManyToOne
  public StreetParent street;

  public StreetParent getStreet() {
    return street;
  }

  public void setStreet(StreetParent street) {
    this.street = street;
  }
}
