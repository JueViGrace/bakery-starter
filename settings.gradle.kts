rootProject.name = "bakery-starter"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":client:composeApp")
include(":server")

// core library
include(":lib")

include(":lib:core:api:client")
include(":lib:core:api:server")

include(":lib:core:database:client")
include(":lib:core:database:server")
include(":lib:core:database:shared")

include(":lib:core:di")
include(":lib:core:presentation")

include(":lib:core:resources")

include(":lib:core:types:client")
include(":lib:core:types:server")
include(":lib:core:types:shared")

// auth library

//  auth data
include(":lib:auth:data:client")
include(":lib:auth:data:server")
include(":lib:auth:data:shared")

// auth domain
include(":lib:auth:domain:client")
include(":lib:auth:domain:server")
include(":lib:auth:domain:shared")

// auth presentation
include(":lib:auth:presentation")
