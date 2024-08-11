plugins {
    id("com.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "uz.toshshahartransxizmat.toshbustravel.resource.strings"
    compileSdk = 34
    defaultConfig {
        minSdk = 22
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.annotation)
}
