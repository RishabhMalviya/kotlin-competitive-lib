plugins {
    java
    kotlin("jvm") version "1.3.61"
}

group = "org.legalize-it"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("junit:junit:4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        shouldRunAfter("clean")
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

// Modified
tasks.test {
    description = "Modified Gradle `test` task to run test classes declared privately in each file"

    useJUnit()

    testClassesDirs = sourceSets["main"].output.classesDirs
    classpath = sourceSets["main"].runtimeClasspath
}


tasks.register<Test>("heap-test") {
    description = "test Heap implementation"
    group = "data-structures-test"

    useJUnit()

    include("**/*Heap*Test*.class")

    testClassesDirs = sourceSets["main"].output.classesDirs
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.withType<Test> {
    this.testLogging {
        this.showStandardStreams = true
        this.events("skipped", "passed", "failed")
    }

    this.dependsOn(provider {
        tasks.filter { task -> task.name=="clean" || task.name=="compileKotlin" }
    })
}

