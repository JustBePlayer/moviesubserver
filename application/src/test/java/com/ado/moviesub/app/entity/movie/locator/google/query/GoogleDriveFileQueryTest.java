package com.ado.moviesub.app.entity.movie.locator.google.query;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class GoogleDriveFileQueryTest {

  private static String VALUE = "value";

  private static AttributeQuery nameQuery;
  private static AttributeQuery idQuery;

  @BeforeClass
  public static void setup(){
    nameQuery = new NameQueryFactory(VALUE).equal();
    idQuery = new IdQueryFactory(VALUE).equal();
  }

  @Test
  public void testCreatingQueryWithAndOperatorOnce(){
    String expectedQuery = String.format("(%s) and (%s)", idQuery, nameQuery);

    GoogleDriveFileQuery query = new GoogleDriveFileQuery(idQuery).and(nameQuery);
    assertEquals(expectedQuery, query.toString());
  }

  @Test
  public void testCreatingQueryWithAndOperatorMultipleTimes(){
    AttributeQuery parentsQuery = new ParentAttributeQueryFactory(VALUE).in();
    GoogleDriveFileQuery twoAttributesQuery = new GoogleDriveFileQuery(idQuery).and(nameQuery);

    String expectedString = String.format("(%s) and (%s)", twoAttributesQuery.toString(), parentsQuery.toString());

    GoogleDriveFileQuery threeAttributesQuery = twoAttributesQuery.and(parentsQuery);
    assertEquals(expectedString, threeAttributesQuery.toString());
  }
}
