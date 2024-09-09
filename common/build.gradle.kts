plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.8.21"
}
@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:mvvm-core:0.16.1")
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../toshBusIosApp/Podfile")
        framework {
            baseName = "common"
            isStatic = true
        }

//        pod("GoogleMaps") {
//            version = "9.1.0" // Podfile bilan mos versiya
//            extraOpts += listOf("-compiler-option", "-fmodules")
//        }

        pod("GoogleMaps")

    }

    sourceSets {
        val commonMain by getting {

            dependencies {

                implementation(libs.kotlinx.coroutines.core)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // Navigator
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.tab.navigator)
                implementation(libs.voyager.transitions)

                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                // Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.json)
                implementation(libs.ktor.client.serialization)


                implementation(libs.runtime)
                implementation(libs.coroutines.extensions)
                implementation(libs.kotlinx.datetime)

                implementation(libs.kamel.image)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.android.driver)
                implementation(libs.appcompat)
                implementation(libs.activity.compose)
                implementation(libs.ktor.client.cio)

                implementation(libs.koin.android)
                implementation(libs.datastore.preferences)

                // OSMDroid va Accompanist Permissions kutubxonalarini qo'shish
                implementation(libs.osmdroid)
                implementation(libs.accompanist.permissions)

                // Maps SDK for Android
                implementation(libs.play.services.maps)
                implementation(libs.android.maps.utils)
                implementation("com.google.android.gms:play-services-location:18.0.0" )
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")
                implementation ("com.google.maps.android:maps-compose:2.4.0") // or the latest version

            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.native.driver)
                implementation(libs.ktor.client.darwin)
               // implementation("platform.google.maps:GMSMapView:3.10.0")
            }
        }
    }
}

android {
    namespace = "uz.toshshahartransxizmat.toshbustravel"
    compileSdk = 34
    defaultConfig {
        minSdk = 22
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/versions/**"
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.core)
    implementation(libs.activity.ktx)
    implementation(libs.androidx.ui.android)
    commonMainApi(libs.mvvm.core)
    commonMainApi(libs.mvvm.compose)
    commonMainApi(libs.mvvm.flow)
    commonMainApi(libs.mvvm.flow.compose)
}