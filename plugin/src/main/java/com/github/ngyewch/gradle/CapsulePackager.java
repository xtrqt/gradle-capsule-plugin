package com.github.ngyewch.gradle;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class CapsulePackager
    implements Closeable {

  private final JarOutputStream jarOutputStream;

  public CapsulePackager(OutputStream outputStream, Manifest manifest)
      throws IOException {
    super();

    jarOutputStream = new JarOutputStream(outputStream, manifest);
  }

  public void addBootJar(File jarFile)
      throws IOException {
    try (final JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarFile))) {
      while (true) {
        final JarEntry jarEntry = jarInputStream.getNextJarEntry();
        if (jarEntry == null) {
          break;
        }
        if (jarEntry.getName().equalsIgnoreCase("META-INF/MANIFEST.MF")) {
          continue;
        }

        final JarEntry newJarEntry = new JarEntry(jarEntry.getName());
        newJarEntry.setTime(jarEntry.getTime());
        jarOutputStream.putNextEntry(newJarEntry);

        if (!jarEntry.isDirectory()) {
          IOUtils.copy(jarInputStream, jarOutputStream);
        }

        jarOutputStream.closeEntry();
      }
    }
  }

  public void addMainJar(File jarFile)
      throws IOException {
    addMainJar(jarFile, jarFile.getName());
  }

  private void addMainJar(File jarFile, String entryName)
      throws IOException {
    addJar("", jarFile, entryName);
  }

  public void addLibJar(File jarFile)
      throws IOException {
    addLibJar(jarFile, jarFile.getName());
  }

  private void addLibJar(File jarFile, String entryName)
      throws IOException {
    addJar("", jarFile, entryName);
  }

  private void addJar(String prefix, File jarFile, String entryName)
      throws IOException {
    final JarEntry jarEntry = new JarEntry(String.format("%s%s", prefix, entryName));
    jarEntry.setTime(jarFile.lastModified());
    jarEntry.setMethod(JarEntry.STORED);
    jarEntry.setSize(jarFile.length());
    jarEntry.setCrc(ZipUtils.getCRC32(jarFile));
    jarOutputStream.putNextEntry(jarEntry);
    try (final InputStream inputStream = new FileInputStream(jarFile)) {
      IOUtils.copy(inputStream, jarOutputStream);
    }
    jarOutputStream.closeEntry();
  }

  @Override
  public void close()
      throws IOException {
    jarOutputStream.close();
  }
}
