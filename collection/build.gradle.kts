dependencies {
    implementation(project(":model"))
    implementation(project(":logger"))
}

tasks.bootJar {
    enabled = false
}