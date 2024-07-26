#### Kotlin
- Statically typed (type checking done by compiler during compile time) programming language running on JVM (JVM isn’t compulsory for the reason below)
- Can be compiled to JS or native code (ahead-of-time compilation, AOT) using Kotlin/Native
- Kotlin/Native is a technology for compiling Kotlin code to native binaries, which can run directly on hardware without the need for a virtual machine
- Kotlin Code -> Java Bytecode -> JVM

#### Kotlin for Backend
- Technologies that enable Kotlin to be used as backend
- Spring Boot
- Ktor
- Vert.x
- Micronaut
- Exposed (This is more of a database technology)
- The above technology (except exposed) support standalone backend development 

#### Spring & Spring Boot
- Spring is a comprehensive framework that provides a wide range of features for building enterprise-level applications, requiring manual configuration and setup.
- Spring Boot builds on top of Spring, offering auto-configuration, embedded servers, and production-ready features to simplify and accelerate the development and deployment of Spring applications. It reduces the complexity and boilerplate code associated with traditional Spring applications, making it easier to get started and maintain.

#### Creating a new Spring Boot application
- Using Spring Initializer
- Using IDEs eg. Eclipse & IntelliJ IDEA
- Using Spring Boot CLI

#### Maven Vs Gradle
- Both are build automation tools
- Maven is XML based (less flexible)
    -  .pom file for configuration
    -  Convention over configuration
    - Convention over Configuration (CoC) is a software design principle that aims to reduce the number of decisions developers need to make by following a set of predefined conventions. 
- Gradle is Groovy/Kotlin based (more flexible)
    - build.gradle or build.gradle.kts for configuration
- Uses Groovy or Kotlin DSL (Domain Specific Language)

#### Using Spring Initilizr (Web)
<img width="700" alt="Screenshot 2024-07-26 at 12 13 14" src="https://github.com/user-attachments/assets/e79529d5-f0c7-455e-b2ab-4a6c4a0965ed">

#### JAR vs WAR packaging
- Packaging in refers to the process of bundling all the components of a software application into a single file or set of files that can be easily distributed and deployed.
- JAR files are used for general-purpose Java applications and libraries, while WAR files are specifically designed for web applications and include additional structure and configuration needed for deployment on web servers.
- For a typical backend application, especially one built with Spring Boot, use JAR packaging. It offers flexibility, ease of deployment, and aligns well with modern deployment practices.
