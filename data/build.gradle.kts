plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.google.devtools.ksp)
    id("com.google.dagger.hilt.android") apply false
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.retrofit.gson.convertor)
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    // Network
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.retrofit.adapters.result)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.javax.inject)
    implementation(libs.kotlin.stdlib)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}
