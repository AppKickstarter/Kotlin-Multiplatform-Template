plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation("androidx.appcompat:appcompat:1.6.1")

                implementation(platform("androidx.compose:compose-bom:2023.01.00"))
                implementation("androidx.compose.material:material")
                implementation("androidx.compose.material3:material3")
                implementation("androidx.compose.ui:ui")
                implementation("androidx.compose.ui:ui-tooling")
                implementation("androidx.compose.foundation:foundation")
                implementation("androidx.compose.animation:animation")
                implementation("androidx.compose.material:material-icons-core")
                implementation("androidx.compose.material:material-icons-extended")
                implementation("androidx.activity:activity-compose:1.7.0")

                implementation("com.google.accompanist:accompanist-systemuicontroller:0.29.2-rc")
                implementation("com.google.accompanist:accompanist-permissions:0.29.1-alpha")
            }
        }
    }
}

val properties = org.jetbrains.kotlin.konan.properties.Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.lduboscq.appkickstarter.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        release {
            //isDebuggable = true
            isMinifyEnabled = true
            //  R8
            // proguard = false
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
