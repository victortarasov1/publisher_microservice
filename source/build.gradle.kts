dependencies {
    implementation(project(":collection"))
    implementation(project(":model"))
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
}
tasks.bootJar {
    enabled = false
}