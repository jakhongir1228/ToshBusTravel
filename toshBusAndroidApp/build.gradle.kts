plugins {
    alias(libs.plugins.androidApplication)
    kotlin("android")
    id("org.jetbrains.compose")
}


android {
    namespace = "uz.toshshahartransxizmat.toshbustravel.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "uz.toshshahartransxizmat.toshbustravel.android"
        minSdk = 22
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(libs.activity.compose)
    implementation(compose.material3)

    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.android)
}