package com.ado.moviesub.server.app.entity.movie;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleAuthorizationClientTest {

  @Test
  public void testGetCredentials() throws GeneralSecurityException, IOException {
    GoogleAuthorizationClient client = new GoogleAuthorizationClient(JacksonFactory.getDefaultInstance());
    NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

    client.getCredentials(httpTransport);
  }

  @Test
  public void test() throws GeneralSecurityException, IOException {
    GoogleDriverLocator locator = GoogleDriverLocator.connect();
    locator.getFiles();
  }
}