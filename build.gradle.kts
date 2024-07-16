val koinVersion = "3.6.0-wasm-alpha2"

plugins {
    kotlin("jvm") version "2.0.0"
}

group = "com.Card_Game"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    runtimeOnly("io.insert-koin:koin-core:$koinVersion")
    implementation(platform("io.insert-koin:koin-bom:$koinVersion"))
    implementation("io.insert-koin:koin-core")
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}