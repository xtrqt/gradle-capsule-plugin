package com.github.ngyewch.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CapsulePlugin
    implements Plugin<Project> {

  @Override
  public void apply(Project project) {
    project.getExtensions().create("capsule", CapsuleExtension.class);

    project.getTasks().register("packageFatCapsule", PackageCapsuleTask.class,
        task -> task.dependsOn("jar"));
  }
}
