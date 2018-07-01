package com.ado.moviesub.app.entity.movie.locator.google.query;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParentAttributeQueryFactoryTest {

  @Test
  public void testCreateAttributeQueryWithEqualOperation(){
    String value = "something";
    String expectedQuery = String.format("'%s' in parents", value);

    AttributeQuery attributeQuery = new ParentAttributeQueryFactory(value).in();
    assertEquals(expectedQuery, attributeQuery.toString());
  }
}
