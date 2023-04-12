plugins {
    id("org.jetbrains.kotlin.jvm")
    application
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlinx/")
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:3.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.5")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    applicationName = "cli.app.MainKt"
}
/*
val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "cli.app.MainKt"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
*/