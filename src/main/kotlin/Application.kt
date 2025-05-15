package com.example

import com.example.ioc.configureFrameworks
import com.example.jcref.MainFrame
import com.example.plugin.configureSerialization
import com.example.router.configureRouting
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
