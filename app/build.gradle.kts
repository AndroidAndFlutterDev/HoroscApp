plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // These are the plugins that will be used to access and charge the Hilt code
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.horoscapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.horoscapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    // Set the binding to true so we can access to the UI more easily
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    val navVersion = "2.7.1"

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}