package com.ado.moviesub.app.entity.movie.locator.google.query;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IdQueryFactoryTest {

  @Test
  public void testCreateAttributeQueryWithEqualOperation(){
    String value = "something";
    String expectedQuery = String.format("id = '%s'", value);

    AttributeQuery attributeQuery = new IdQueryFactory(value).equal();
    assertEquals(expectedQuery, attributeQuery.toString());
  }



}
