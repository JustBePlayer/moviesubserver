package com.ado.moviesub.app.exception;

public class SubtitleFileNotFoundException extends ApplicationException {
  private static final String MESSAGE = "Subtitle file was not found";

  private String providerId;

  public SubtitleFileNotFoundException(String providerId) {
    super(MESSAGE);
    this.providerId = providerId;
  }

  @Override
  public SubtitleNotFoundApplicationError toApplicationError(){
    return getApplicationErrorBuilder().build();
  }

  @Override
  public SubtitleNotFoundApplicationError.Builder getApplicationErrorBuilder(){
    // @formatter:off
    return new SubtitleNotFoundApplicationError.Builder()
        .setMessage(getMessage())
        .setResourceId(providerId);
    // @formatter:on
  }
}
