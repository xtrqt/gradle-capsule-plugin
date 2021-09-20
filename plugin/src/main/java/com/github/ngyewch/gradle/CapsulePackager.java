package com.github.ngyewch.gradle;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.jar.*;

public class CapsulePackager
    implements Closeable {

  private static final String APPLICATION_CLASS_MANIFEST_KEY = "Application-Class";
  private static final String APPLICATION_ID_MANIFEST_KEY = "Application-ID";
  private static final String MIN_JAVA_VERSION_MANIFEST_KEY = "Min-Java-Version";
  private static final String PREMAIN_CLASS_MANIFEST_KEY = "Premain-Class";
  private static final String SYSTEM_PROPERTIES_MANIFEST_KEY = "System-Properties";

  private static final String DEFAULT_MAIN_CLASS = "Capsule";
  private static final String DEFAULT_PREMAIN_CLASS = "Capsule";

  private final JarOutputStream jarOutputStream;

  public CapsulePackager(OutputStream outputStream, String mainClassName, Manifest manifest)
      throws IOException {
    super();

    final Manifest adjustedManifest = new Manifest(manifest);
    if (adjustedManifest.getMainAttributes().getValue(Attributes.Name.MANIFEST_VERSION) == null) {
      adjustedManifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
    }
    adjustedManifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, DEFAULT_MAIN_CLASS);
    adjustedManifest.getMainAttributes().putValue(PREMAIN_CLASS_MANIFEST_KEY, DEFAULT_PREMAIN_CLASS);
    adjustedManifest.getMainAttributes().putValue(APPLICATION_CLASS_MANIFEST_KEY, mainClassName);

    jarOutputStream = new JarOutputStream(outputStream, adjustedManifest);
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
