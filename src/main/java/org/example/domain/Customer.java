package org.example.domain;

import io.ebean.Model;
import io.ebean.annotation.Cache;
import org.example.domain.parent.CustomerParent;

import javax.persistence.*;

@Entity
@Table(name = "document")
@DiscriminatorValue(value="1")
public class Customer extends CustomerParent {

  public String notes;

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
