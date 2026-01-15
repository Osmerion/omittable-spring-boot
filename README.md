# Omittable - Spring Boot Integration

[![License](https://img.shields.io/badge/license-Apache%202.0-yellowgreen.svg?style=for-the-badge&label=License)](https://github.com/Osmerion/omittable-spring-boot/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.osmerion.omittable/omittable-spring-boot-webmvc.svg?style=for-the-badge&label=Maven%20Central)](https://maven-badges.herokuapp.com/maven-central/com.osmerion.omittable/omittable-spring-boot-webmvc)
[![JavaDoc](https://img.shields.io/maven-central/v/com.osmerion.omittable/omittable-spring-boot-webmvc.svg?style=for-the-badge&label=JavaDoc&color=blue)](https://javadoc.io/doc/com.osmerion.omittable/omittable-spring-boot-webmvc)
![Java](https://img.shields.io/badge/Java-17-green.svg?style=for-the-badge&color=b07219&logo=Java)

A collection of support libraries providing Spring Boot integration for the [Omittable](https://github.com/Osmerion/Omittable)
library. The libraries add autoconfiguration on top of the [Spring Framework integration for omittable types](https://github.com/Osmerion/omittable-spring).


## Building from source

### Setup

This project uses [Gradle's toolchain support](https://docs.gradle.org/current/userguide/toolchains.html)
to detect and select the JDKs required to run the build. Please refer to the
build scripts to find out which toolchains are requested.

An installed JDK 17 (or later) is required to use Gradle.

### Building

Once the setup is complete, invoke the respective Gradle tasks using the
following command on Unix/macOS:

    ./gradlew <tasks>

or the following command on Windows:

    gradlew <tasks>

Important Gradle tasks to remember are:
- `clean`                   - clean build results
- `build`                   - assemble and test the project
- `publishToMavenLocal`     - build and install all public artifacts to the
                              local maven repository

Additionally `tasks` may be used to print a list of all available tasks.


## License

```
Copyright 2025-2026 Leon Linhart

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
