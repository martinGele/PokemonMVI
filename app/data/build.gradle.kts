plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.di)
    id("kotlin-kapt")
}

android {
    namespace = "com.martin.data"
    compileSdk = 34
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"https://pokeapi.co\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://pokeapi.co\"")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp)
    implementation(libs.okhttp.tls)
    implementation(libs.okhttp.interceptor)
    implementation(libs.moshi)
    kapt(libs.moshi.codegen)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.slack.eithernet)
}