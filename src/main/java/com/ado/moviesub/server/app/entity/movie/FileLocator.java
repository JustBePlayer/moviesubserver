package com.ado.moviesub.server.app.entity.movie;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface FileLocator {
  File locate(String fileName);

  List<File> getFiles() throws GeneralSecurityException, IOException;

  List<String> getFileNames() throws IOException, GoogleDriveLocatorException;
}
