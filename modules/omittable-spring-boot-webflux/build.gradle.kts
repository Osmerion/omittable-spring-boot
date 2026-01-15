/*
 * Copyright 2025-2026 Leon Linhart
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    id("com.osmerion.java-base-conventions")
    id("com.osmerion.maven-publish-conventions")
    `java-library`
    `jvm-test-suite`
}

java {
    withSourcesJar()
    withJavadocJar()
}

testing {
    suites {
        register<JvmTestSuite>("integrationTest") {
            useJUnitJupiter()

            dependencies {
                implementation(project())

                implementation(platform(libs.spring.boot.dependencies))
                implementation(libs.spring.boot.starter.test)
                implementation(libs.spring.boot.starter.webflux)
            }
        }
        register<JvmTestSuite>("springDocIntegrationTest") {
            useJUnitJupiter()

            dependencies {
                implementation(project())

                implementation(platform(libs.spring.boot.dependencies))
                implementation(libs.spring.boot.starter.test)
                implementation(libs.spring.boot.starter.webflux)
                implementation(libs.springdoc.openapi.starter.webflux.ui)
            }
        }
    }
}

tasks {
    check {
        dependsOn(testing.suites.named("integrationTest"))
        dependsOn(testing.suites.named("springDocIntegrationTest"))
    }
}

publishing {
    publications.register<MavenPublication>("mavenJava") {
        from(components["java"])

        pom {
            description = "Spring Boot WebFlux support for Omittable types."
        }
    }
}

dependencies {
    api(libs.omittable.spring.webflux)
    api(libs.omittable.swagger.core)

    api(platform(libs.spring.boot.dependencies))
    api(libs.spring.boot.autoconfigure)

    compileOnly(libs.springdoc.openapi.starter.common)
}
