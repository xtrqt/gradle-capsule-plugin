package com.github.ngyewch.gradle;

import org.gradle.api.artifacts.Configuration;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.MapProperty;
import org.gradle.api.provider.Property;

import javax.inject.Inject;

public abstract class CapsuleExtension {

  private final CapsuleManifest capsuleManifest;

  @Inject
  public CapsuleExtension(ObjectFactory objectFactory) {
    super();

    capsuleManifest = objectFactory.newInstance(CapsuleManifest.class);
  }

  public abstract Property<String> getVersion();

  public abstract Property<String> getArchiveBaseName();

  public abstract Property<String> getArchiveAppendix();

  public abstract Property<String> getArchiveVersion();

  public abstract Property<String> getArchiveClassifier();

  public abstract Property<String> getArchiveExtension();

  public abstract Property<Configuration> getEmbedConfiguration();

  public abstract MapProperty<String, String> getManifestAttributes();

  public CapsuleManifest getCapsuleManifest() {
    return capsuleManifest;
  }
}
