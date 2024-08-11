enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

apply(pathDir = "gradle/include")

rootProject.name = "ToshBusTravel"
include(":toshBusAndroidApp")
include(":common")

include(":resource:icons")
include(":resource:strings")

fun apply(pathDir: String) {
    val files = File(pathDir).listFiles() ?: return
    for (file in files) {
        if (file.isDirectory) {
            apply(pathDir = file.path)
        }
        if (file.isFile && file.name.endsWith(suffix = ".gradle")) {
            apply(from = file.path)
        }
    }
}
