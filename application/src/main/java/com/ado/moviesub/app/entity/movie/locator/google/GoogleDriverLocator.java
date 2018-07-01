package com.ado.moviesub.app.entity.movie.locator.google;

import com.ado.moviesub.app.entity.movie.Subtitle;
import com.ado.moviesub.app.entity.movie.SubtitleParser;
import com.ado.moviesub.app.entity.movie.locator.SubtitleLocator;
import com.ado.moviesub.app.entity.movie.locator.google.query.AttributeQuery;
import com.ado.moviesub.app.entity.movie.locator.google.query.GoogleDriveFileQuery;
import com.ado.moviesub.app.entity.movie.locator.google.query.NameQueryFactory;
import com.ado.moviesub.app.entity.movie.locator.google.query.ParentAttributeQueryFactory;
import com.ado.moviesub.app.exception.InternalServiceException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class GoogleDriverLocator implements SubtitleLocator {
  private static final String ROOT_DIRECTORY_NAME = "Subtitles";

  private Drive driveService;
  private String rootDirectoryId;

  private GoogleDriverLocator(Drive driveService){
    this.driveService = driveService;
  }


  @Override
  public Subtitle locate(String fileName) {
    return null;
  }

  @Override
  public List<Subtitle> getAllSubtitles() {
   try {
     Drive.Files.List request = createGetAllSubtitlesRequest();

     return getSubtitles(request);
   } catch (GoogleDriveLocatorException | IOException e) {
     throw new InternalServiceException("Something went wrong when fetching subtitles form server");
   }
  }

  @Override
  public List<Subtitle> getSubtitlesByMovieName(String movieName) {
    try {
      Drive.Files.List request = createGetAllSubtitlesRequest();

      AttributeQuery attributeNameQuery = new NameQueryFactory(normalizeMovieNameForSearching(movieName)).contains();
      AttributeQuery IdInParentsQuery = new ParentAttributeQueryFactory(getRootDirectoryId()).in();
      GoogleDriveFileQuery generalQuery = new GoogleDriveFileQuery(IdInParentsQuery).and(attributeNameQuery);

      request.setQ(generalQuery.toString());

      return getSubtitles(request);

    } catch (GoogleDriveLocatorException | IOException e) {
      throw new InternalServiceException("Something went wrong when fetching subtitles form server");
    }
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

  private List<Subtitle> getSubtitles(Drive.Files.List request) throws IOException {
    List<Subtitle> subtitles = new ArrayList<>();
    do {
      FileList result = request.execute();;

      for(File file : result.getFiles()){
        subtitles.add(convertSubtitle(file));
      }
      request.setPageToken(result.getNextPageToken());

    } while (request.getPageToken() != null && request.getPageToken().length() > 0);
    return subtitles;
  }

  private String getRootDirectoryId() throws IOException, GoogleDriveLocatorException {
    if(rootDirectoryId != null){
      return rootDirectoryId;
    }

    AttributeQuery attributeQuery = new NameQueryFactory(ROOT_DIRECTORY_NAME).equal();
    GoogleDriveFileQuery query = new GoogleDriveFileQuery(attributeQuery);

    FileList fileList = driveService.files()
        .list()
        .setQ(query.toString())
        .execute();

    if(fileList.isEmpty()){
      throw new GoogleDriveLocatorException("Root Directory Could not be retrieved");
    }

    rootDirectoryId = fileList.getFiles().get(0).getId();
    return rootDirectoryId;
  }

  private Drive.Files.List createGetAllSubtitlesRequest() throws IOException, GoogleDriveLocatorException {
    String pageToken = null;

    AttributeQuery attributeQuery = new ParentAttributeQueryFactory(getRootDirectoryId()).in();
    GoogleDriveFileQuery query = new GoogleDriveFileQuery(attributeQuery);

    // @formatter:ff
    return driveService.files().list()
        .setQ(query.toString())
        .setFields("nextPageToken, files(id, name)")
        .setPageToken(pageToken);

    // @formatter:on
  }

  private Subtitle convertSubtitle(File file) throws IOException {
    try(SubtitleParser parser = new SubtitleParser(downloadFile(file), file.getName())){
      return parser.parse();
    }
  }

  private BufferedReader downloadFile(File file) throws IOException {
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
      driveService.files().get(file.getId()).executeMediaAndDownloadTo(out);

      return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(out.toByteArray())));
    }
  }

  private String normalizeMovieNameForSearching(String movieName){
    // @formatter:off
    return new StringBuilder()
        .append(FILE_NAME_DELIMITER)
        .append(movieName)
        .append(FILE_NAME_DELIMITER)
        .toString();
    // @formatter:on
  }

}
