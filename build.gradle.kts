plugins {
    alias(libs.plugins.spotless)
}

repositories {
    mavenCentral()
}

group = "de.nplay.levelbot"
version = "0.0.1"

allprojects {
    group = rootProject.group
    version = rootProject.version
}

subprojects {
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.isIncremental = true
        options.compilerArgs.addAll(listOf("-parameters", "--enable-preview"))
        sourceCompatibility = "25"
    }
}

spotless {
    encoding("UTF-8")

    format("misc") {
        target("*.gradle.kts", ".gitattributes", ".gitignore")

        trimTrailingWhitespace()
        endWithNewline()
    }

    java {
        target("**/*.java")
        targetExclude(".github/workflows/**", "build/**")

        importOrder("", "java", "javax", "\\#")
        forbidModuleImports()
        formatAnnotations()
        trimTrailingWhitespace()
        endWithNewline()
    }
}

// separate task for potential additional formatting tasks in the future
tasks.register("format") {
    group = "verification"
    dependsOn(tasks.named("spotlessApply"))
}

tasks.named("check").configure {
    dependsOn(tasks.named("spotlessCheck"))
}

tasks.named("check").configure {
    dependsOn(tasks.named("spotlessCheck"))
}
