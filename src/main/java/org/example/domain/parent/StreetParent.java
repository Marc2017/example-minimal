package org.example.domain.parent;

import org.example.domain.parent.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "street")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype", discriminatorType=DiscriminatorType.INTEGER)
@DiscriminatorValue(value="0")
public class StreetParent extends BaseModel {

  public String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
