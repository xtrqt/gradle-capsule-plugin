plugins {
    application
    id("com.github.ngyewch.capsule")
}

dependencies {
    implementation("junit:junit:4.13.2")
    implementation("org.apache.commons:commons-lang3:3.12.0")
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("mypackage.Test")
}

capsule {
    capsuleManifest {
        applicationId.set("gradle-capsule-plugin.tests.module1")
        applicationClass.set("mypackage.Test1")
        args.set(listOf("zimbu", "the", "monkey"))
        environmentVariables.set(mapOf("ENV1" to "hello", "ENV2" to "world"))
        systemProperties.set(mapOf("prop1" to "boo", "prop2" to "hoo"))
    }
}