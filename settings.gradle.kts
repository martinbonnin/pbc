include(":backend", ":backend:datastore", ":backend:service-graphql", ":frontend")


pluginManagement {
    listOf(repositories, dependencyResolutionManagement.repositories).forEach {
        it.apply {
            mavenCentral()
            maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.google.cloud.tools.appengine" -> useModule("com.google.cloud.tools:appengine-gradle-plugin:${requested.version}")
            }
        }
    }
}