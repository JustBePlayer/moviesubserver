package com.ado.moviesub.app.entity.movie.locator.google.query;

import static org.junit.Assert.*;
import org.junit.Test;

public class NameQueryFactoryTest {
  @Test
  public void testCreateAttributeQueryWithEqualOperation(){
    String value = "something";
    String expectedQuery = String.format("name = '%s'", value);

    AttributeQuery attributeQuery = new NameQueryFactory(value).equal();
    assertEquals(expectedQuery, attributeQuery.toString());
  }

  @Test
  public void testCreateAttributeQueryWithContainsOperation(){
    String value = "something";
    String expectedQuery = String.format("name contains '%s'", value);

    AttributeQuery attributeQuery = new NameQueryFactory(value).contains();
    assertEquals(expectedQuery, attributeQuery.toString());
  }

  @Test
  public void testCreateAttributeQueryWithNotEqualOperation(){
    String value = "something";
    String expectedQuery = String.format("name != '%s'", value);

    AttributeQuery attributeQuery = new NameQueryFactory(value).notEqual();
    assertEquals(expectedQuery, attributeQuery.toString());
  }


}
