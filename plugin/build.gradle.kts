plugins {
    `java-gradle-plugin`
    `maven-publish`
    id("ca.cutterslade.analyze") version "1.9.0"
    id("com.asarkar.gradle.build-time-tracker") version "4.3.0"
    id("com.github.ben-manes.versions") version "0.42.0"
    id("com.gradle.plugin-publish") version "0.21.0"
    id("me.qoomon.git-versioning") version "6.1.4"
    id("se.ascp.gradle.gradle-versions-filter") version "0.1.16"
}

group = "com.github.ngyewch.gradle"
version = "0.0.0-SNAPSHOT"
gitVersioning.apply {
    refs {
        tag("v(?<version>.*)") {
            considerTagsOnBranches = true
            version = "\${ref.version}"
        }
        branch(".+") {
            version = "\${ref}-SNAPSHOT"
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(gradleApi())

    implementation("commons-io:commons-io:2.11.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("gradle-capsule-plugin") {
            id = "com.github.ngyewch.capsule"
            displayName = "Gradle Capsule Plugin"
            description = "Gradle plugin for Capsule."
            implementationClass = "com.github.ngyewch.gradle.CapsulePlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/ngyewch/gradle-capsule-plugin"
    vcsUrl = "https://github.com/ngyewch/gradle-capsule-plugin.git"
    tags = listOf("capsule")
}