plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

tasks {
    jar {
        manifest {
            attributes(
                mapOf(
                    "Main-Class" to "rip.shukaaa.Main"
                )
            )
        }
    }
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("com.formdev:flatlaf:3.3")
}