package com.example.myapp.ui

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R


class WebActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_layout)

        webView = findViewById(R.id.web_view)
      //  webView.webViewClient = MyWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        val url = intent.getStringExtra("url")

        if (url != null) {
            webView.loadUrl(url)
        }

    }
}


