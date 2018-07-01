package com.ado.moviesub.app.entity.movie.locator.google;

public class AttributeQueryFactory extends AbstractAttributeQueryFactory{

  public AttributeQueryFactory(String attributeName, Object value) {
    super(attributeName, normalizeValue(value));
  }

  protected AttributeQuery equal(){
    return AttributeQuery.getInstance(this, AttributeQuery.Operator.EQUAL);
  }

  protected AttributeQuery notEqual(){
    return AttributeQuery.getInstance(this, AttributeQuery.Operator.NOT_EQUAL);
  }
  protected AttributeQuery contains(){
    return AttributeQuery.getInstance(this, AttributeQuery.Operator.CONTAINS);
  }

}
