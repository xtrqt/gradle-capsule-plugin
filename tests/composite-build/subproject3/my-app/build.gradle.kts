plugins {
    application
    id("com.github.ngyewch.capsule")
}

dependencies {
    implementation("com.github.ngyewch:my-api")
    implementation("com.github.ngyewch:my-impl")
}

application {
    mainClass.set("mypackage.MyApp")
}
