package com.ado.moviesub.app.entity.movie.locator.google.query;

import com.ado.moviesub.app.entity.movie.locator.google.query.AttributeQuery;
import com.ado.moviesub.app.entity.movie.locator.google.query.AttributeQueryFactory;

public class IdQueryFactory extends AttributeQueryFactory {

  public IdQueryFactory(Object value) {
    super("id", value);
  }

  @Override
  public AttributeQuery equal(){
    return AttributeQuery.getInstance(this, AttributeQuery.Operator.EQUAL);
  }

}
