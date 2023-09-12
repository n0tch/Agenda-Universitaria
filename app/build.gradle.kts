import java.io.FileInputStream
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.detekt)
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

val properties = Properties().apply {
    load(FileInputStream("${project.rootDir}/version.properties"))
    forEach { prop ->
        project.ext.set(prop.key?.toString() ?: "", prop.value)
    }
}

android {
    namespace = "com.example.agendauniversitaria"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.agendauniversitaria"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = getVersionCode()
        versionName = getVersionName()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }

        getByName("debug"){

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
    packaging {
        resources {
            excludes.addAll(
                    listOf(
                            "/META-INF/{AL2.0,LGPL2.1}",
                            "META-INF/gradle/incremental.annotation.processors",
                            "META-INF/DEPENDENCIES"
                    )
            )
        }
    }
}

dependencies {

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    implementation(libs.androidx.lifecycle.runTime.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.bundles.compose)

    implementation(libs.coroutine.play.service)
    implementation(libs.coil.compose)

    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.firebase.auth)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.databse)

    implementation(libs.data.store)
    implementation(libs.gson)

    implementation(project(":features:navigation"))
    implementation(project(":core:designsystem"))
    //TODO: remove this to another package when remove onborading from app
    implementation(project(":core:domain"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    debugImplementation(libs.leakcanary)
}

kapt {
    correctErrorTypes = true
}

fun getVersionCode(): Int {
    val major = ext.get("majorVersion").toString().toInt()
    val minor = ext.get("minorVersion").toString().toInt()
    val patch = ext.get("patchVersion").toString().toInt()

    return (major * 10000) + (minor * 100) + patch
}

fun getVersionName(): String {
    return "${ext.get("majorVersion")}.${ext.get("minorVersion")}.${ext.get("patchVersion")}"
}