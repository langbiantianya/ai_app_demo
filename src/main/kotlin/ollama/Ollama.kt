package com.example.ollama

import com.example.utils.logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

object Ollama {
    lateinit var process: Process
    fun start() {
        process = ProcessBuilder()
            .apply {
                environment().apply {
                    put("OLLAMA_HOST", "127.0.0.1:11435")       // 监听地址
                    put("OLLAMA_GPU_LAYER", "cuda")    // 模型存储路径
                    put("OLLAMA_DEBUG", "true")      // 调试模式（按需启用）
//                    put("OLLAMA_INTEL_GPU", "true")
                    put("OLLAMA_MODELS", "./models")

                }
            }
            .command("ollama/ollama.exe", "serve")
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()


        // 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(Thread {
            process.destroy()
            logger().info("Ollama process terminated")
        })
        // 启动协程异步读取输出流
        CoroutineScope(Dispatchers.IO).launch {
            process.inputStream.bufferedReader().use { reader ->
                reader.lineSequence().forEach { line ->
                    logger().info("[Ollama] $line")
                }
            }
        }

        // 启动协程异步读取错误流
        CoroutineScope(Dispatchers.IO).launch {
            process.errorStream.bufferedReader().use { reader ->
                reader.lineSequence().forEach { line ->
                    logger().error("[Ollama] $line")
                }
            }
        }
    }

    fun destroy() {
        process.destroy()
    }
}