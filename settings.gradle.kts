rootProject.name = "bakery"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":client:composeApp")
include(":server")

// core library
include(":lib:core:api:client")
include(":lib:core:api:server")
include(":lib:core:database")
include(":lib:core:di")
include(":lib:core:presentation")
include(":lib:core:resources")
include(":lib:core:types:shared")
include(":lib:core:types:server")
include(":lib:core:types:client")

// auth library
include(":lib:auth:data")
include(":lib:auth:di")
include(":lib:auth:domain")
include(":lib:auth:presentation")
