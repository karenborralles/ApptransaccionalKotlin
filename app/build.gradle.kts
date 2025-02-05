plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Agregamos el plugin de Kotlin Serialization
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
}

android {
    namespace = "com.example.proyectocorte1vf"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.proyectocorte1vf"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
}

dependencies {
    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material:material:1.5.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
    implementation(libs.androidx.material3.android)
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    debugImplementation("androidx.compose.ui:ui-tooling:1.5.0")

    // Activity Compose
    implementation("androidx.activity:activity-compose:1.7.2")

    // Lifecycle and ViewModel for Compose
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    // Retrofit y Gson para Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // Kotlinx Serialization JSON (útil para otros casos o si decides usarlo en Retrofit)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("androidx.compose.ui:ui:1.5.0")

}
