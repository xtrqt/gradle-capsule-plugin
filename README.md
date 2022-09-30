# gradle-capsule-plugin

![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/ngyewch/gradle-capsule-plugin)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/ngyewch/gradle-capsule-plugin/Java%20CI)

A Gradle plugin for [Capsule](https://github.com/puniverse/capsule).

* Minimum Gradle version: 6.4
* Latest tested Gradle version: 7.2
* Minimum Java version: 8

## Installation

See https://plugins.gradle.org/plugin/com.github.ngyewch.capsule

## Basic usage

Kotlin DSL (`build.gradle.kts`)
```
plugins {
    application
    id("com.github.ngyewch.capsule")
}

application {
    mainClass.set("org.gradle.sample.Main")
}
```

Groovy DSL (`build.gradle`)
```
plugins {
    id("application")
    id("com.github.ngyewch.capsule")
}

application {
    mainClass = "org.gradle.sample.Main"
}
```

Invocation
```
./gradlew packageFatCapsule
```

The output file can be found at `${project.buildDir}/${project.libsDirName}/[archiveBaseName]-[archiveAppendix]-[archiveVersion]-[archiveClassifier].[archiveExtension]`

With default settings: `build/libs/${project.archivesBaseName}-${project.version}-capsule.jar`

Assuming your project name is `myproject`, the output file can be found at `build/libs/myproject-capsule.jar`

## Tasks

### Added tasks

The plugin adds the following tasks to the project.

#### `packageFatCapsule` - `com.github.ngyewch.gradle.PackageCapsuleTask`

* Depends on: `jar`
* Packages a fat capsule.

### Task definitions

#### `com.github.ngyewch.gradle.PackageCapsuleTask`

Packages a fat capsule.

Properties specified at the task level overrides properties specified in the `capsule` and `application` extensions.

Main artifacts are taken from the output of the direct dependencies (of type `Jar`) of this task.

Kotlin DSL (`build.gradle.kts`)
```
tasks {
    register<com.github.ngyewch.gradle.PackageCapsuleTask>("packageFatCapsule2") {
        dependsOn("jar")
        archiveBaseName.set("test")
        embedConfiguration.set(configurations.getByName("runtimeClasspath"))
        manifestAttributes.set(mapOf("Test-Attribute" to "Test-Value"))
        capsuleManifest {
            applicationId.set("myjar")
        }
    }
}
```

Groovy DSL (`build.gradle`)
```
tasks.register("packageFatCapsule2", com.github.ngyewch.gradle.PackageCapsuleTask) {
    dependsOn "jar"
    archiveBaseName = "test"
    embedConfiguration = configurations.getByName("runtimeClasspath")
    manifestAttributes = ["Test-Attribute": "Test-Value"]
    capsuleManifest {
        applicationId = "myjar"
    }
}
```

| Name                 | Type | Required | Description                                                                                                               |
|----------------------| --- | --- |---------------------------------------------------------------------------------------------------------------------------|
| `group`              | `String` | No | Capsule group. Default: `io.nextflow`                                                                                     |
| `version`            | `String` | No | Capsule version. Default: `1.1.1`                                                                                         |
| `archiveBaseName`    | `String` | No | Archive base name. Default: `project.archivesBaseName`                                                                    |
| `archiveAppendix`    | `String` | No | Archive appendix.                                                                                                         |
| `archiveVersion`     | `String` | No | Archive version. Default: `project.version`                                                                               |
| `archiveClassifier`  | `String` | No | Archive classifier. Default: `"capsule"`                                                                                  |
| `archiveExtension`   | `String` | No | Archive extension. Default: `"jar"`                                                                                       |
| `embedConfiguration` | `org.gradle.api.artifacts.Configuration` | No | Embed configuration. Library artifacts to include in the capsule. Default: `configurations.getByName("runtimeClasspath")` |
| `manifestAttributes` | `Map<String, String>` | No | Manifest attributes.                                                                                                      |
| `capsuleManifest`    | `com.github.ngyewch.gradle.CapsuleManifest` | No | Capsule manifest.                                                                                                         |

## Extension

Kotlin DSL (`build.gradle.kts`)
```
# sample configuration, using some properties (including optional ones) 
capsule {
    archiveBaseName.set("myjar")
    archiveClassifier.set("all")
    embedConfiguration.set(configurations.getByName("runtimeClasspath")) 
    manifestAttributes.set(mapOf("Test-Attribute" to "Test-Value"))
    capsuleManifest {
        applicationId.set("myjar")
    }
}
```

Groovy DSL (`build.gradle`)
```
# sample configuration, using some properties (including optional ones) 
capsule {
    archiveBaseName = "myjar"
    archiveClassifier = "all"
    embedConfiguration = configurations.getByName("runtimeClasspath")
    manifestAttributes = ["Test-Attribute": "Test-Value"]
    capsuleManifest {
        applicationId = "myjar"
    }
}
```

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| `version` | `String` | No | Capsule version. Default: `1.0.3` |
| `archiveBaseName` | `String` | No | Archive base name. Default: `project.archivesBaseName` |
| `archiveAppendix` | `String` | No | Archive appendix. |
| `archiveVersion` | `String` | No | Archive version. Default: `project.version` |
| `archiveClassifier` | `String` | No | Archive classifier. Default: `"capsule"` |
| `archiveExtension` | `String` | No | Archive extension. Default: `"jar"` |
| `embedConfiguration` | `org.gradle.api.artifacts.Configuration` | No | Embed configuration. Library artifacts to include in the capsule. Default: `configurations.getByName("runtimeClasspath")` |
| `manifestAttributes` | `Map<String, String>` | No | Manifest attributes. |
| `capsuleManifest` | `com.github.ngyewch.gradle.CapsuleManifest` | No | Capsule manifest. |

## CapsuleManifest

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| `applicationId` | `String` | No | Application ID. |
| `applicationVersion` | `String` | No | Application version. Default: `project.version` |
| `applicationName` | `String` | No | Application name. |
| `applicationClass` | `String` | No | Application class. |
| `minJavaVersion` | `String` | No | Min Java version. |
| `javaVersion` | `String` | No | Java version. |
| `jdkRequired` | `Boolean` | No | JDK required. |
| `jvmArgs` | `List<String>` | No | JVM args. |
| `args` | `List<String>` | No | Args. |
| `environmentVariables` | `Map<String, String>` | No | Environment variables. |
| `systemProperties` | `Map<String, String>` | No | System properties. |
