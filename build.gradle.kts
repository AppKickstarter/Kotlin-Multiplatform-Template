@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.jvm) apply false
    alias(libs.plugins.multiplatform) apply false
    alias(libs.plugins.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.compose) apply false
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}