package mobile.buildsrc

object Versions {
    const val ktlint = "0.29.0"
}

object GradlePlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:3.5.3"
}

object Libs {

    const val cameraIntegrator = "com.github.Him-khati:camera_intergrator:0.2.3"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val junit = "junit:junit:4.13.2"

    const val progressButton = "com.github.razir.progressbutton:progressbutton:2.1.0"

    const val mockitoCore = "org.mockito:mockito-inline:3.0.0"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"
    const val mockitoInline = "org.mockito:mockito-inline:3.0.0"

    const val mpChart = "com.github.PhilJay:MPAndroidChart:v3.1.0"
    const val circularImageView = "de.hdodenhof:circleimageview:3.1.0"
    const val appIntro = "com.github.AppIntro:AppIntro:5.1.0"
    const val zoomableImageView = "com.jsibbold:zoomage:1.3.1"
    const val leakCanary2 = "com.squareup.leakcanary:leakcanary-android:2.3"
    const val coil = "io.coil-kt:coil:0.11.0"
    const val coilCompose = "io.coil-kt:coil-compose:2.2.2"


    object Testing {
        const val junit = "junit:junit:4.13.2"
        const val turbine = "app.cash.turbine:turbine:0.8.0"
        const val mockk = "io.mockk:mockk:1.12.4"
    }

    object Vinners {
        const val logger = "com.github.vinnersafterwork:core:1.0"
    }

    object Firebase {

        const val firebaseCore = "com.google.firebase:firebase-core:17.4.1"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics:17.5.0"
        const val firebaseMessaging = "com.google.firebase:firebase-messaging:20.1.7"
        const val dynamicLinks = "com.google.firebase:firebase-dynamic-links:19.1.0"
        const val inAppMessaging = "com.google.firebase:firebase-inappmessaging-display:19.0.6"
        const val crashlytics = "com.google.firebase:firebase-crashlytics:17.2.1"
        const val crashlyticsGradlePlugin = "com.google.firebase:firebase-crashlytics-gradle:2.3.0"
        const val auth = "com.google.firebase:firebase-auth:19.3.1"
    }

    object Google {

        const val material = "com.google.android.material:material:1.5.0"
        const val gmsGoogleServicesGradlePlugin = "com.google.gms:google-services:4.3.3"
        const val placesLibrary = "com.google.android.libraries.places:places:1.0.0"
        const val maps = "com.google.android.gms:play-services-maps:16.1.0"

        const val playCore = "com.google.android.play:core:1.7.2"
        const val playLocation = "com.google.android.gms:play-services-location:16.0.0"
        const val playRefer = "com.android.installreferrer:installreferrer:1.1.2"
        const val playServicesBase = "com.google.android.gms:play-services-base:17.0.0"
        const val authApiPhone = "com.google.android.gms:play-services-auth-api-phone:17.0.0"
        const val auth = "com.google.android.gms:play-services-auth:18.0.0"
        const val smsRetriver = "com.google.android.gms:play-services-gcm:17.0.0"
    }

    object Kotlin {
        private const val version = "1.4.0"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.6.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val coreJvm = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$version"
        const val rx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Android {
       const val desugarJdk =  "com.android.tools:desugar_jdk_libs:1.1.5"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        const val browser = "androidx.browser:browser:1.0.0"
        const val palette = "androidx.palette:palette:1.0.0"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
        const val emoji = "androidx.emoji:emoji:1.0.0"
        const val fragment = "androidx.fragment:fragment:1.0.0"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.5"
        const val activityKtx = "androidx.activity:activity-ktx:1.3.1"
        const val multiDex = "androidx.multidex:multidex:2.0.1"
        const val vectorDrawables = "androidx.vectordrawable:vectordrawable:1.1.0"
        const val preference = "androidx.preference:preference:1.1.0-alpha02"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val coreKtx = "androidx.core:core-ktx:1.2.0"
        const val archCoreTesting = "androidx.arch.core:core-testing:2.1.0"
        const val dataStore = "androidx.datastore:datastore-preferences:1.0.0"
        const val splashScreen = "androidx.core:core-splashscreen:1.0.0"

        object Test {
            const val core = "androidx.test:core:1.1.0"
            const val runner = "androidx.test:runner:1.1.1"
            const val rules = "androidx.test:rules:1.1.1"
            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
        }

        object Navigation {
            private const val version = "2.5.3"
            const val navigationFragment = "androidx.navigation:navigation-fragment:$version"
            const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val navigationUi = "androidx.navigation:navigation-ui:$version"
            const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val composeNavigation = "androidx.navigation:navigation-compose:$version"
        }

        object Paging {
            private const val version = "2.1.0"
            const val common = "androidx.paging:paging-common:$version"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
            const val rxjava2 = "androidx.paging:paging-rxjava2-ktx:$version"
        }

        object Lifecycle {
            private const val version = "2.4.0"
            const val extensions = "androidx.lifecycle:lfifecycle-extensions:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val lifeCyleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val lifeCyleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val lifeCycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object Room {
            private const val version = "2.5.0-beta01"
            const val roomKtx = "androidx.room:room-ktx:$version"
            const val common = "androidx.room:room-common:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val rxjava2 = "androidx.room:room-rxjava2:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }

        object Work {
            private const val version = "2.2.0"
            const val workManager = "androidx.work:work-runtime-ktx:$version"
            const val workManageRxJavaSupport = "androidx.work:work-rxjava2:$version"
        }

        object Security{
            const val securityCrypto =  "androidx.security:security-crypto:1.0.0-rc02"
        }

        object Compose {

            const val composeVersion = "1.4.0-rc01"
            const val composeCompilerVersion = "1.4.1"

            const val activityCompose = "androidx.activity:activity-compose:$composeVersion"

            const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:$composeVersion"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"

            const val ui = "androidx.compose.ui:ui:$composeVersion"
            const val material = "androidx.compose.material:material:$composeVersion"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"

            const val material3 = "androidx.compose.material3:material3:1.1.0"
            const val material3WindowSizeClass = "androidx.compose.material3:material3-window-size-class:1.1.0"

            const val lifeCycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-beta01"
        }
    }

    object AirBnb {
        const val lottie = "com.airbnb.android:lottie:3.4.0"
    }

    object Facebook {
        const val stetho = "com.facebook.stetho:stetho:1.5.1"
    }

    object RxJava {
        const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.12"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.3.0"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    }

    object Dagger {
        private const val version = "2.27"
        const val dagger = "com.google.dagger:dagger:$version"
        const val androidSupport = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:$version"

        const val hiltVersion = "2.44.2"
        const val hiltGradlePugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"

        const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object Dropbox{
        const val store =  "com.dropbox.mobile.store:store4:4.0.4-KT15"
    }

    object Glide {
        private const val version = "4.9.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }


    object Square {

        object OkHttp {
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.7.2"
        }

        object Okio {
            const val okIo = "com.squareup.okio:okio:2.2.2"
        }

        object Retrofit {
            private const val version = "2.9.0"
            const val retrofit = "com.squareup.retrofit2:retrofit:$version"
            const val retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:$version"
            const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
            const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
        }
    }

    object Gson {
        const val gson = "com.google.code.gson:gson:2.8.5"
    }

    object AssistedInject {
        private const val version = "0.4.0"
        const val annotationDagger2 = "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
        const val processorDagger2 = "com.squareup.inject:assisted-inject-processor-dagger2:$version"
    }
}
