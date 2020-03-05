package org.example.domain.parent;

import io.ebean.Model;
import io.ebean.annotation.Cache;

import javax.persistence.*;

@Cache(enableQueryCache=true)
@MappedSuperclass
public abstract class BaseModel extends Model {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  public Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
