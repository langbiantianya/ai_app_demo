package com.example

import com.jetbrains.cef.JCefAppConfig
import org.cef.CefApp
import org.cef.CefClient
import org.cef.browser.CefBrowser
import org.cef.browser.CefRendering
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Toolkit
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import kotlin.concurrent.Volatile
import kotlin.jvm.java


class MainFrame : JFrame() {
    private val url = "http://127.0.0.1:8080"

    private var app: CefApp? = null
    private var client: CefClient? = null
    private var browser: CefBrowser? = null

    init {
        val args = JCefAppConfig.getInstance().getAppArgs()
        val settings = JCefAppConfig.getInstance().getCefSettings()
        // 获取CefApp实例
        CefApp.startup(args)
        app = CefApp.getInstance(args, settings)
        // 创建客户端实例
        client = app!!.createClient()
        // 创建浏览器实例
        browser = client!!.createBrowser(url, CefRendering.DEFAULT, true)

        this.getContentPane().setLayout(BorderLayout(0, 0))
        this.getContentPane().add(browser!!.getUIComponent(), BorderLayout.CENTER)

        this.setTitle("JetBrains Runtime - JCEF")
        this.setSize(Dimension(1280, 720))
        this.setMinimumSize(Dimension(1150, 650))
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE)
        this.setLocation(
            (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2,
            (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2
        )
        this.setVisible(true)
        this.setResizable(true)
        this.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                app!!.dispose()
                System.exit(0)
            }
        })
    }

    companion object {
        private const val serialVersionUID = 2541887783679247552L

        @Volatile
        private var instance: MainFrame? = null

        val istance: MainFrame?
            get() {
                if (instance == null) {
                    synchronized(MainFrame::class.java) {
                        if (instance == null) {
                            instance = MainFrame()
                        }
                    }
                }
                return instance
            }
    }
}