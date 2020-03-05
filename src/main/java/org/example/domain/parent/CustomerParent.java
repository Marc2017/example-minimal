package org.example.domain.parent;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype", discriminatorType=DiscriminatorType.INTEGER)
@DiscriminatorValue(value="0")
public class CustomerParent extends BaseModel {

  @ManyToOne
  public Address address;

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address adress) {
    this.address = adress;
  }
}
