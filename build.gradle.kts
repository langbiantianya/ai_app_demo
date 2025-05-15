val koin_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.1.10"
    id("io.ktor.plugin") version "3.1.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
    id("edu.sc.seis.launch4j") version "3.0.6" // 添加 Launch4j 插件
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "com.example.ApplicationKt"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    implementation("io.ktor:ktor-server-netty")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.github.ollama4j:ollama4j:1.0.100")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

// 添加 Launch4j 配置
launch4j {
    mainClassName = application.mainClass.get()
    outfile = "${project.name}.exe"
    outputDir = "./libs"
    bundledJrePath = "./jre" // 指定相对路径的 JRE 目录
    icon = "${projectDir}/ai_demo.ico" // 可选应用图标
}
// 添加JAR打包配置
tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }

//    // 包含所有依赖项（需要额外配置）
//    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
//    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.register<Copy>("copyJre") {
    from("jre")
    into("${getLayout().buildDirectory.get()}/libs/jre")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

// 确保 createExe 任务在 jar 之后执行
tasks.named("createExe") {

    dependsOn(tasks.named("jar"))
    finalizedBy(tasks.named("copyJre"))
}

// 添加 Inno Setup 打包任务
tasks.register<Exec>("buildWindows") {
    dependsOn("createExe")
    doFirst {
        File("${projectDir}/build/windows").mkdirs()
    }
    commandLine(
        "\"C:\\Users\\langb\\IdeaProjects\\test_jcef\\InnoSetup6\\ISCC.exe\"", // 使用绝对路径并包裹在转义引号中
        "/DMyAppName=${project.name}",
        "/DMyAppVersion=${version}",
        "/DMyExeName=${project.name}.exe",
        "${projectDir}/setup.iss"
    )
    doFirst {
        println("执行打包命令: ${commandLine.joinToString(" ")}") // 添加调试日志
    }
}