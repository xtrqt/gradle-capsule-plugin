import org.gradle.api.tasks.testing.logging.*

plugins {
    java
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.apache.commons:commons-lang3:3.12.0")
    testRuntimeOnly("org.slf4j:slf4j-simple:1.7.32")
    testImplementation("org.zeroturnaround:zt-exec:1.12")
}

repositories {
    mavenCentral()
}

tasks {
    register<Exec>("clean-composite-build-subproject1") {
        workingDir = project.file("../composite-build/subproject1")
        commandLine = listOf("./gradlew", "clean")
    }

    register<Exec>("clean-composite-build-subproject2") {
        workingDir = project.file("../composite-build/subproject2")
        commandLine = listOf("./gradlew", "clean")
    }

    register<Exec>("clean-composite-build-subproject3") {
        workingDir = project.file("../composite-build/subproject3")
        commandLine = listOf("./gradlew", "clean")
    }

    register("clean-composite-build") {
        dependsOn("clean-composite-build-subproject1", "clean-composite-build-subproject2", "clean-composite-build-subproject3")
    }

    register<Exec>("build-composite-build") {
        workingDir = project.file("../composite-build/subproject3")
        commandLine = listOf("./gradlew", "packageFatCapsule")
    }

    register<Exec>("clean-groovy-dsl") {
        workingDir = project.file("../groovy-dsl")
        commandLine = listOf("./gradlew", "clean")
    }

    register<Exec>("build-groovy-dsl") {
        workingDir = project.file("../groovy-dsl")
        commandLine = listOf("./gradlew", "packageFatCapsule")
    }

    register<Exec>("clean-kotlin-dsl") {
        workingDir = project.file("../kotlin-dsl")
        commandLine = listOf("./gradlew", "clean")
    }

    register<Exec>("build-kotlin-dsl") {
        workingDir = project.file("../kotlin-dsl")
        commandLine = listOf("./gradlew", "packageFatCapsule")
    }

    register("clean-external") {
        dependsOn("clean-composite-build", "clean-groovy-dsl", "clean-kotlin-dsl")
    }

    register("build-external") {
        dependsOn("build-composite-build", "build-groovy-dsl", "build-kotlin-dsl")
    }

    named<Test>("test") {
        dependsOn("build-external")

        testLogging {
            events = mutableSetOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
        }
    }
}
