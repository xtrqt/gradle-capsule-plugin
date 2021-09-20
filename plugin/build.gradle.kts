plugins {
    `java-gradle-plugin`
    id("ca.cutterslade.analyze") version "1.8.1"
    id("com.asarkar.gradle.build-time-tracker") version "3.0.1"
    id("com.github.ben-manes.versions") version "0.39.0"
    id("me.qoomon.git-versioning") version "5.1.0"
    id("se.ascp.gradle.gradle-versions-filter") version "0.1.10"
}

group = "com.github.ngyewch.gradle"
version = "0.0.0-SNAPSHOT"
gitVersioning.apply {
    refs {
        tag("v(?<version>.*)") {
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
            implementationClass = "com.github.ngyewch.gradle.CapsulePlugin"
        }
    }
}
