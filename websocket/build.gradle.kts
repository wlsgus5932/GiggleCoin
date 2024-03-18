dependencies {
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation(project(":infrastructure:kafka"))
    implementation(project(":domain"))
    implementation("org.springframework.data:spring-data-redis")
}

