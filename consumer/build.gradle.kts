dependencies {
    implementation(project(":infrastructure:kafka"))
    implementation(project(":domain"))
    
    //TODO: redis 모듈화
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
}
