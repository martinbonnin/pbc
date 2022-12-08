plugins {
  kotlin("jvm")
  id("org.jetbrains.kotlin.plugin.spring")
  id("org.jetbrains.kotlin.plugin.serialization")
  id("org.springframework.boot")
  id("com.google.cloud.tools.appengine")
}

dependencies {
  implementation(libs.graphql.kotlin.spring.server)
  implementation(project(":backend:datastore"))
}

appengine {
  stage {
    setArtifact(tasks.named("bootJar").flatMap { (it as Jar).archiveFile })
  }
  tools {
    setServiceAccountKeyFile(file("../service_account_key.json"))
  }
  deploy {
    projectId = "pbc-app-371010"
    version = "GCLOUD_CONFIG"
  }
}

tasks.named("appengineStage").configure {
  dependsOn("bootJar")
}