@file:Suppress("DSL_SCOPE_VIOLATION")

import dev.icerock.gradle.tasks.GenerateMultiplatformResourcesTask

plugins {
    alias(libs.plugins.multiplatform)
    id(libs.plugins.nativecocoapods.get().pluginId)
    alias(libs.plugins.serialization)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.sqldelight)

    id("dev.icerock.mobile.multiplatform-resources").version(libs.versions.moko.resources)
}

version = "1.0-SNAPSHOT"

kotlin {
    androidTarget()

    applyDefaultHierarchyTemplate()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Shared code for the sample"
        homepage = "https://appkickstarter.com"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true

            export("com.mohamedrejeb.calf:calf-ui:0.2.0")
        }
        extraSpecAttributes["resource"] = "'build/cocoapods/framework/shared.framework/*.bundle'"
    }

    sourceSets {
        commonMain {
            dependencies {
                // remote
                implementation(libs.ktor.core)
                implementation(libs.ktor.serialization.json)
                implementation(libs.ktor.content.negociation)
                implementation(libs.kotlinx.serialization.json)

                // local datas
                api(libs.multiplatform.settings)
                implementation(libs.multiplatform.settings.coroutines)

                // logs
                api(libs.napier)

                // ui
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                api(libs.image.loader)
                implementation(libs.moko.resources)
                implementation(libs.moko.resources.compose)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)
                implementation(libs.voyager.koin)

                // dates
                api(libs.kotlinx.datetime)

                api(libs.koin.core)

                implementation(libs.coroutines.extensions)

                // workaround fix youtrack KT-41821
                implementation("org.jetbrains.kotlinx:atomicfu:0.20.2")

                api(libs.calf.ui)
                implementation(libs.calf.filepicker)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.appcompat)
                implementation(libs.core.ktx)
                implementation(libs.ktor.client.okhttp)
                api(libs.koin.android)
                api(libs.koin.workmanager)

                implementation(libs.sqldelight.android.driver)

                // Accompanist
                implementation(libs.accompanist.systemuicontroller)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.ktor.client.ios)
                implementation(libs.sqldelight.native.driver)
            }
        }
        iosTest { dependencies {} }
    }
}

android {
    namespace = "com.lduboscq.appkickstarter.shared"
    compileSdk = libs.versions.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/resources")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    sourceSets["main"].java.srcDirs("build/generated/moko/androidMain/src") // temp fix for https://github.com/icerockdev/moko-resources/issues/531 to be removed when issue is fixed

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(11)
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.appkickstarter.shared"
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.lduboscq.appkickstarter")
            //dialect("app.cash.sqldelight:mysql-dialect:2.0.0-rc01")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/databases"))
        }
    }
}

val generateSourcesTasks = tasks.withType<GenerateMultiplatformResourcesTask>()

tasks.matching { it.name.endsWith("SourcesJar", ignoreCase = true) }
    .configureEach {
        dependsOn(generateSourcesTasks)
    }
