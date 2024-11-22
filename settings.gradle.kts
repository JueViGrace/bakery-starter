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
// project(":lib:core:api:client").name = "api-client"

include(":lib:core:api:server")
// project(":lib:core:api:server").name = "api-server"

include(":lib:core:database:client")

include(":lib:core:database:server")
// project(":lib:core:database:server").name = "database-server"

include(":lib:core:database:shared")

include(":lib:core:di")
include(":lib:core:presentation")

include(":lib:core:resources")

include(":lib:core:types:shared")

include(":lib:core:types:server")

include(":lib:core:types:client")

// auth library
include(":lib:auth:data")
include(":lib:auth:domain")
include(":lib:auth:presentation")
