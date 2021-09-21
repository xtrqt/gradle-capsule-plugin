package com.github.ngyewch.gradle;

import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.MapProperty;
import org.gradle.api.provider.Property;

public abstract class CapsuleManifest {

  public abstract Property<String> getApplicationId();

  public abstract Property<String> getApplicationVersion();

  public abstract Property<String> getApplicationName();

  public abstract Property<String> getApplicationClass();

  public abstract Property<String> getMinJavaVersion();

  public abstract Property<String> getJavaVersion();

  public abstract Property<Boolean> getJdkRequired();

  public abstract ListProperty<String> getJvmArgs();

  public abstract ListProperty<String> getArgs();

  public abstract MapProperty<String, String> getEnvironmentVariables();

  public abstract MapProperty<String, String> getSystemProperties();
}
