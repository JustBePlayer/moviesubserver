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
  private static final String ROOT_DIRECTORY_NAME = "Subtitles";

  private Drive driveService;

  private GoogleDriverLocator(Drive driveService){
    this.driveService = driveService;
  }


  @Override
  public File locate(String fileName) {
    return null;
  }

  @Override
  public List<File> getFiles() throws GeneralSecurityException, IOException {
    FileList result = driveService.files().list()
        .setFields("nextPageToken, files(id, name)")
        .execute();

    List<File> results = new ArrayList<>();

//    File file = driveService.files()

    return new ArrayList<>();
  }

  @Override
  public List<String> getFileNames() throws IOException, GoogleDriveLocatorException {

    List<String> fileNames = new ArrayList<>();
    String pageToken = null;
    do{
      FileList result = driveService.files().list()
          .setQ(String.format("'%s' in parents", getRootDirectoryId()))
          .setFields("nextPageToken, files(id, name)")
          .setPageToken(pageToken)
          .execute();

      result.getFiles().forEach(file -> fileNames.add(file.getName()));
    } while (pageToken != null);

    return fileNames;
  }

  public static GoogleDriverLocator connect() throws GeneralSecurityException, IOException {
    JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
    GoogleAuthorizationClient authClient = new GoogleAuthorizationClient(jsonFactory);

    NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    Credential credentials = authClient.getCredentials(httpTransport);

    Drive driveService = new Drive.Builder(httpTransport, jsonFactory, credentials)
        .setApplicationName("movie-sub-server")
        .build();

    return new GoogleDriverLocator(driveService);
  }

  private String getRootDirectoryId() throws IOException, GoogleDriveLocatorException {
    FileList fileList = driveService.files()
        .list()
        .setQ(String.format("title='%'", ROOT_DIRECTORY_NAME))
        .execute();

    if(fileList.isEmpty()){
      throw new GoogleDriveLocatorException("Root Directory Could not be retrieved");
    }

    return fileList.getFiles().get(0).getId();
  }



}
