plugins {
    id("io.franzbecker.gradle-lombok") version ("1.14")
    java
}

lombok {
    version = "1.18.4"
    sha256 = ""
}

group = "com.sir4ur0n"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    // Functional control and immutable data types for Java
    implementation("io.vavr", "vavr", "0.9.2")
    // Stop spamming when I run my tests, SLF4J, please, pretty please
    implementation("org.slf4j", "slf4j-nop", "1.7.25")
    // JUnit 5
    val junitJupiterVersion = "5.3.2"
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitJupiterVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-engine", junitJupiterVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-params", junitJupiterVersion)
    val junitPlatformVersion = "1.3.2"
    testImplementation("org.junit.platform", "junit-platform-launcher", junitPlatformVersion)
    // Fluent test assertion framework
    testImplementation("org.assertj", "assertj-core", "3.11.1")
    // Property Based Test framework
    val junitQuickcheck = "0.8.1"
    testImplementation("com.pholser", "junit-quickcheck-core", junitQuickcheck)
    testImplementation("com.pholser", "junit-quickcheck-generators", junitQuickcheck)
    testImplementation("com.github.sir4ur0n", "junit-quickcheck-vavr", "1.0")
    // Avoid maintaining the explicit resource file with all Quickcheck generators
    testImplementation("com.google.auto.service", "auto-service", "1.0-rc4")
    testAnnotationProcessor("com.google.auto.service", "auto-service", "1.0-rc4")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
