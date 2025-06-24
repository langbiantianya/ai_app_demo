package com.example.router

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.*
import io.ktor.http.headers
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
//        需要使用ollama4j
//        route("/ollama") {
//            val client = HttpClient(CIO)
////            代理所有请求到127.0.0.1“11433
//            handle {
//                // 构建目标URL
//                val targetUrl = "http://127.0.0.1:11435" + call.request.uri.removePrefix("/ollama")
//
//                // 转发请求并获取响应
//                val response = client.request(targetUrl) {
//                    method = call.request.httpMethod
//                    headers {
//                        appendAll(call.request.headers)
//                    }
//                    setBody(call.request.receiveChannel())
//                }
//
//                // 返回代理响应
//                call.respond(response.status, response.readRawBytes())
//            }
//        }
        staticResources("/", "static") {

        }
    }
}

