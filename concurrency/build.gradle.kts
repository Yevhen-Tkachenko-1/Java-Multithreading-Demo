plugins {
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

group = "com.yevhent"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}