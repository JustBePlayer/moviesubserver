package com.ado.moviesub.app.entity.movie;

import java.util.Arrays;

public enum FileLocatorProviderType {
  GOOGLE("Super");

  private final String value;

  FileLocatorProviderType(String value) {
    this.value = value;
  }

  public static FileLocatorProviderType fromString(String providerName) throws IllegalArgumentException {
    return Arrays.stream(FileLocatorProviderType.values())
        .filter(v -> v.value.equals(providerName))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown provider: ".concat(providerName)));
  }
}
