package com.example

import com.example.ioc.configureFrameworks
import com.example.jcref.MainFrame
import com.example.ollama.Ollama
import com.example.plugin.configureSerialization
import com.example.router.configureRouting
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain.createServer
import kotlinx.coroutines.DelicateCoroutinesApi

@OptIn(DelicateCoroutinesApi::class)
fun main(args: Array<String>) {
    val server = createServer(args)
    server.start(false)
    Ollama.start()
    MainFrame.istance
}

fun Application.module() {
    configureSerialization()
    configureFrameworks()
    configureRouting()
}
