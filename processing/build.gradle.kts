dependencies {
    implementation(project(":collection"))
    implementation(project(":model"))
    implementation(project(":source"))
    implementation(project(":validator"))
    implementation(project(":security"))
    implementation(project(":queue"))
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.bootJar {
    enabled = true
}