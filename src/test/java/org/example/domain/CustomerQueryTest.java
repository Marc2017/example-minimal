package org.example.domain;

import io.ebean.DB;
import org.junit.Test;

public class CustomerQueryTest {

  @Test
  public void findAll() {

    DB.find(Customer.class)
        .findList();

    //new QCustomer()
    //  .id.greaterOrEqualTo(1L)
    //  .findList();
  }
}
