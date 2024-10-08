buildscript {
    dependencies {
        classpath(libs.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinNativeCocoapods) apply false
    alias(libs.plugins.composeMultiplatform) apply false

    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlinAndroid) apply false
}