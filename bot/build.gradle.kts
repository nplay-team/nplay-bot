import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    alias(libs.plugins.shadow)
}

application {
    mainClass = "de.nplay.bot.Bootstrapper"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.logging)
    implementation(libs.jspecify)
}

tasks.withType<JavaExec> {
    jvmArgs("--enable-preview", "--sun-misc-unsafe-memory-access=allow")
}

tasks.withType<ShadowJar> {
    archiveFileName = "levelbot.jar"
}
