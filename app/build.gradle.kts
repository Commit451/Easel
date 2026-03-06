plugins {
    id("com.android.application")
}

android {
    namespace = "com.commit451.easel.sample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.commit451.easel.sample"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        abortOnError = false
    }
}


dependencies {
    implementation("androidx.appcompat:appcompat:1.7.1")

    implementation(project(":easel"))
}
