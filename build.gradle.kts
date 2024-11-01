// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // This is the plugin that will be used to generate the Hilt code
    id("com.google.dagger.hilt.android") version "2.48" apply false // Use the correct plugin ID and version
    id("androidx.navigation.safeargs.kotlin") version "2.8.3" apply false
}