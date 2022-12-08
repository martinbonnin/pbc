plugins {
  id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
  jvm()

  sourceSets {
    val commonMain by getting {
    }
    val jvmMain by getting {
      dependencies {
        implementation(libs.google.cloud.datastore)
      }
    }
  }
}