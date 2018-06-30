package com.ado.moviesub.app.entity.movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubtitleParser implements AutoCloseable{

  public static final String DEFAULT_VERSION = "00000000-0000-0000-0000-000000000000";

  private static final int MOVIE_NAME_INDEX = 0;
  private static final int LANGUAGE_INDEX = 1;
  private static final int VERSION_INDEX = 2;

  private static final String NAME_TOKENS_DELIMITER = "-";

  private BufferedReader fileContentReader;
  private SubtitleLineParser lineParser;
  private String name;

  public SubtitleParser(BufferedReader reader, String name) {
    this.fileContentReader = reader;
    this.name = name;
    this.lineParser = new SubtitleLineParser();
  }

  public Subtitle parse() throws IOException {
    String[] nameTokens = name.split(NAME_TOKENS_DELIMITER);

    String name = nameTokens[MOVIE_NAME_INDEX];
    String language = nameTokens[LANGUAGE_INDEX];
    String version = nameTokens.length > VERSION_INDEX ? nameTokens[VERSION_INDEX] : DEFAULT_VERSION;

    // @formatter:off
    return new Subtitle.Builder()
        .setMovie(new Movie.Builder().setName(name).build())
        .setLanguage(Language.fromString(language))
        .setVersion(version)
        .setSubtitleLines(getSubtitleLines())
        .build();
    // @formatter:on
  }

  private List<SubtitleLine> getSubtitleLines() throws IOException {
    SubtitleLineParser parser = new SubtitleLineParser();
    List<SubtitleLine> subtitleLines = new ArrayList<>();

      String line;
      List<String> lineChunks = new ArrayList<>();

      while ((line = fileContentReader.readLine()) != null){
        String normalizedLine = line.trim();

        if(!normalizedLine.isEmpty()){
          subtitleLines.add(parser.parse(lineChunks));
          lineChunks.clear();
          continue;
        }

        lineChunks.add(normalizedLine);
      }

      return subtitleLines;
  }

  @Override
  public void close() throws IOException {
    if(fileContentReader != null){
      fileContentReader.close();
    }
  }
}
