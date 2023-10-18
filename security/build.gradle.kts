dependencies {
    implementation("com.auth0:java-jwt:4.4.0")
    implementation(project(":model"))
    implementation("org.springframework.boot:spring-boot-starter-security")
}
tasks.bootJar {
    enabled = false
}