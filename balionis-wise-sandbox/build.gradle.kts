plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.slf4j:slf4j-api:1.7.21")
    runtimeOnly("ch.qos.logback:logback-core:1.1.7")
    runtimeOnly("ch.qos.logback:logback-classic:1.1.7")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")

    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")
}

tasks.test {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        showStandardStreams = true
        events("passed")
    }
}

var fatJar = task("fatJar", type = Jar::class) {
    manifest {
        attributes["Main-Class"] = "com.balionis.wise.sandbox.Application"
    }
    archiveClassifier = "all"
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}

tasks.build {
    dependsOn(fatJar)
}

