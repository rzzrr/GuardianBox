plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.rzatha.guardianbox"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rzatha.guardianbox"
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

    buildFeatures{
        viewBinding = true
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.rxandroid)

    //RxJava3
    implementation (libs.rxjava)

    //Room
    implementation(libs.room.runtime)
    //Java annotationProcessor
    annotationProcessor(libs.room.compiler)
    // RxJava3 support for Room
    implementation(libs.room.rxjava3)

}