package com.ado.moviesub.app.entity.movie.locator.google;

public class ParentAttributeQueryFactory extends ReverseAttributeQueryFactory {
  public ParentAttributeQueryFactory(Object value) {
    super("parents", value);
  }

  @Override
  public AttributeQuery in() {
    return super.in();
  }
}
