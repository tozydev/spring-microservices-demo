plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "spring-microservices-demo"

include("gateway")
include("student-service")
include("assignment-service")
