@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    androidTarget()
    sourceSets {
        androidMain {
            dependencies {
                implementation(projects.shared)
                implementation(libs.appcompat)
                implementation(libs.activity.compose)
                implementation(libs.revenuecat)
                implementation(libs.kmm.viewmodel)
            }
        }
    }
}

android {
    namespace = "com.lduboscq.appkickstarter.androidapp"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.lduboscq.appkickstarter.android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(11)
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

    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")
}
