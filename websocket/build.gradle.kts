dependencies {
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation(project(":infrastructure:redis"))

}