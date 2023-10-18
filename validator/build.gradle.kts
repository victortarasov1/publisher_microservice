dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation(project(":model"))
}
tasks.bootJar {
    enabled = false
}