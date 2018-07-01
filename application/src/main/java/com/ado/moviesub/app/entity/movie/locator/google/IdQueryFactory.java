package com.ado.moviesub.app.entity.movie.locator.google;

public class IdQueryFactory extends AttributeQueryFactory {

  public IdQueryFactory(Object value) {
    super("id", value);
  }

  @Override
  public AttributeQuery equal(){
    return AttributeQuery.getInstance(this, AttributeQuery.Operator.EQUAL);
  }

}
