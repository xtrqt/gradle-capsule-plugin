# gradle-capsule-plugin

![GitHub release (latest by date)](https://img.shields.io/github/v/release/ngyewch/gradle-capsule-plugin)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/ngyewch/gradle-capsule-plugin/Java%20CI)

A Gradle plugin for [Capsule](https://github.com/puniverse/capsule).

* Minimum Gradle version: 6.4
* Minimum Java version: 8

## Basic usage

Kotlin DSL (`build.gradle.kts`)
```
plugins {
    application
    id 'com.github.ngyewch.capsule'
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
    mainClass = 'org.gradle.sample.Main'
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
        mainClass.set("Main2")
        archiveBaseName.set("test")
        embedConfiguration.set(configurations.getByName("runtimeClasspath"))
        manifestAttributes.set(mapOf("Test-Attribute" to "Test-Value"))
    }
}
```

Groovy DSL (`build.gradle`)
```
tasks.register("packageFatCapsule2", com.github.ngyewch.gradle.PackageCapsuleTask) {
  dependsOn "jar"
  mainClass = "Main2"
  archiveBaseName = "test"
  embedConfiguration = configurations.getByName("runtimeClasspath")
  manifestAttributes = ["Test-Attribute": "Test-Value"]
}
```

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| `mainClass` | `String` | No | Main class name. | 
| `version` | `String` | No | Capsule version. Default: `1.0.3` |
| `archiveBaseName` | `String` | No | Archive base name. Default: `project.archivesBaseName` |
| `archiveAppendix` | `String` | No | Archive appendix. |
| `archiveVersion` | `String` | No | Archive version. Default: `project.version` |
| `archiveClassifier` | `String` | No | Archive classifier. Default: `"capsule"` |
| `archiveExtension` | `String` | No | Archive extension. Default: `"jar"` |
| `embedConfiguration` | `org.gradle.api.artifacts.Configuration` | No | Embed configuration. Library artifacts to include in the capsule. Default: `configurations.getByName("runtimeClasspath")` |
| `manifestAttributes` | `Map<String, String>` | No | Manifest attributes. |

## Extension

Kotlin DSL (`build.gradle.kts`)
```
# sample configuration, using all properties (including optional ones) 
capsule {
    archiveBaseName.set("myjar")
    archiveClassifier.set("all")
    embedConfiguration.set(configurations.getByName("runtimeClasspath")) 
    manifestAttributes.set(mapOf("Test-Attribute" to "Test-Value"))
}
```

Groovy DSL (`build.gradle`)
```
# sample configuration, using all properties (including optional ones) 
capsule {
    archiveBaseName = "myjar"
    archiveClassifier = "all"
    embedConfiguration = configurations.getByName("runtimeClasspath")
    manifestAttributes = ["Test-Attribute": "Test-Value"]
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