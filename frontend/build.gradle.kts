plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.apollographql.apollo3")
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.apollo.runtime)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.web.core)
                implementation(libs.routing.compose)
            }
        }
    }
}

// workaround for https://youtrack.jetbrains.com/issue/KT-48273
afterEvaluate {
    rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
        versions.webpackDevServer.version = "4.0.0"
        versions.webpackCli.version = "4.10.0"
    }
}

apollo {
    service("service") {
        packageName.set("pbc.graphql")
        srcDir(file("graphql"))
    }
}

tasks.register("stage") {
    dependsOn("jsBrowserDistribution")
    doLast {
        val stageDir = file("build/stage")

        stageDir.deleteRecursively()
        stageDir.mkdirs()

        file("app.yaml").copyTo(stageDir.resolve("app.yaml"))
        file("build/distributions/frontend.js").copyTo(stageDir.resolve("www/frontend.js"))
        file("src/jsMain/resources/index.html").copyTo(stageDir.resolve("www/index.html"))
    }
}