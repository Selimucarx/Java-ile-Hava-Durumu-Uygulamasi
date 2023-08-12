plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    implementation("com.google.guava:guava:31.1-jre")
    implementation ("org.apache.httpcomponents:httpclient:4.5.13")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.12.5")
    
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}

application {
    mainClass.set("havadurumuuygulamasi.App")
}
