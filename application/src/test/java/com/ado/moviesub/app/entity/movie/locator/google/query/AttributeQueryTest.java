package com.ado.moviesub.app.entity.movie.locator.google.query;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AttributeQueryTest {

  private static final String VALUE = "value";
  private static AbstractAttributeQueryFactory queryFactory;

  @BeforeClass
  public static void setup(){
    queryFactory = new NameQueryFactory(VALUE);
  }

  @Test
  public void testCreateQueryWithEqualOperator(){
    String expectedQuery = String.format("%s = '%s'", queryFactory.getAttributeName(), VALUE);

    AttributeQuery attributeQuery = AttributeQuery.getInstance(queryFactory, AttributeQuery.Operator.EQUAL);
    assertEquals(expectedQuery, attributeQuery.toString());
  }

  @Test
  public void testCreateQueryWithNotEqualOperator(){
    String expectedQuery = String.format("%s != '%s'", queryFactory.getAttributeName(), VALUE);

    AttributeQuery attributeQuery = AttributeQuery.getInstance(queryFactory, AttributeQuery.Operator.NOT_EQUAL);
    assertEquals(expectedQuery, attributeQuery.toString());
  }

  @Test
  public void testCreateQueryWithContainsOperator(){
    String expectedQuery = String.format("%s contains '%s'", queryFactory.getAttributeName(), VALUE);

    AttributeQuery attributeQuery = AttributeQuery.getInstance(queryFactory, AttributeQuery.Operator.CONTAINS);
    assertEquals(expectedQuery, attributeQuery.toString());
  }

  @Test
  public void testCreateQueryWithInOperator(){
    AbstractAttributeQueryFactory reverseAttributeQueryFactory = new ParentAttributeQueryFactory(VALUE);
    String expectedQuery = String.format("%s in parents", reverseAttributeQueryFactory.getAttributeName() );
    AttributeQuery attributeQuery = AttributeQuery.getInstance(reverseAttributeQueryFactory, AttributeQuery.Operator.IN);
    assertEquals(expectedQuery, attributeQuery.toString());
  }
}