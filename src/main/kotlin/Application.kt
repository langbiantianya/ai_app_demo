package com.example

import io.ktor.server.application.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun main(args: Array<String>) {
    GlobalScope.launch {
        io.ktor.server.netty.EngineMain.main(args)
    }
    MainFrame.istance

}

fun Application.module() {
    configureSerialization()
    configureFrameworks()
    configureRouting()
}
