// Workaround for dockerBuildNative failure on darwin/arm64 - can be removed when https://github.com/micronaut-projects/micronaut-gradle-plugin/issues/363 is fixed
// source: https://github.com/bmuschko/gradle-docker-plugin/issues/1035#issuecomment-1217459991
buildscript {
    dependencies {
        classpath("com.github.docker-java:docker-java:3.2.13")
        classpath("com.github.docker-java:docker-java-transport-httpclient5:3.2.13")
    }
}

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("kapt") version "1.7.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.7.10"

    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.micronaut.application") version "3.5.1"
    id("io.micronaut.aot") version "3.5.1"
}

version = "0.1"
group = "org.devrx.micronaut.example"

repositories {
    mavenCentral()
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("jakarta.annotation:jakarta.annotation-api")
    runtimeOnly("ch.qos.logback:logback-classic")
    compileOnly("org.graalvm.nativeimage:svm")

    implementation("io.micronaut:micronaut-validation")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

}


application {
    mainClass.set("org.devrx.micronaut.example.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
graalvmNative {
    toolchainDetection.set(false)
}
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("org.devrx.micronaut.example.*")
    }

    aot {
        // optimizations configuration
        optimizeServiceLoading.set(true)
        convertYamlToJava.set(true)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        netty {
            enabled.set(true)
        }
    }
}
