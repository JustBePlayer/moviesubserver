package com.ado.moviesub.server.app.entity.movie;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class GoogleDriverLocator implements FileLocator {

  private GoogleAuthorizationClient authClient;
  private JsonFactory jsonFactory;

  public GoogleDriverLocator(){
    this.jsonFactory = JacksonFactory.getDefaultInstance();
    this.authClient = new GoogleAuthorizationClient(jsonFactory);
  }

  @Override
  public File locate(String fileName) {
    return null;
  }

  @Override
  public List<File> getFiles() throws GeneralSecurityException, IOException {
    NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

    Credential credentials = authClient.getCredentials(httpTransport);
    Drive driveService = new Drive.Builder(httpTransport, jsonFactory, credentials)
        .setApplicationName("MovieSub")
        .build();

    FileList result = driveService.files().list()
        .setPageSize(10)
        .setFields("nextPageToken, files(id, name)")
        .execute();

    List<File> results = new ArrayList<>();

//    File file = driveService.files()

    return new ArrayList<>();
  }
}
