include(":backend", ":frontend")


pluginManagement {
    listOf(repositories, dependencyResolutionManagement.repositories).forEach {
        it.apply {
            mavenCentral()
            maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }
    }
}