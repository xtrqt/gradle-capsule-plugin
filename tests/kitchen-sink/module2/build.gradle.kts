plugins {
    application
    id("com.github.ngyewch.capsule")
}

dependencies {
    implementation("junit:junit:4.13.2")
    implementation("org.apache.commons:commons-lang3:3.12.0")
}

application {
    mainClass.set("mypackage.Test")
}

capsule {
    capsuleManifest {
        applicationId.set("gradle-capsule-plugin.tests.module2")
        applicationClass.set("mypackage.Test1")
        args.set(listOf("arg1", "arg2"))
        environmentVariables.set(mapOf("ENV1" to "emv1", "ENV2" to "env2"))
        systemProperties.set(mapOf("prop1" to "prop1", "prop2" to "prop2"))
    }
}

tasks {
    register<com.github.ngyewch.gradle.PackageCapsuleTask>("packageFatCapsule2") {
        dependsOn("jar")
        archiveClassifier.set("all")
        capsuleManifest {
            applicationClass.set("mypackage.Test2")
            args.set(listOf("zimbu", "the", "monkey"))
            environmentVariables.set(mapOf("ENV1" to "hello", "ENV2" to "world"))
            systemProperties.set(mapOf("prop1" to "boo", "prop2" to "hoo"))
        }
    }
}
