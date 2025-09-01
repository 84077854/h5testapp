package com.example.linklauncher.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.linklauncher.data.AppDatabase
import com.example.linklauncher.data.HistoryEntry
import com.example.linklauncher.databinding.ActivityWebviewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WebViewActivity : ComponentActivity() {
    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra(EXTRA_URL).orEmpty()
        val ua = intent.getStringExtra(EXTRA_UA).orEmpty()

        setupWebView(binding.webView, ua)

        binding.btnBack.setOnClickListener {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                finish()
            }
        }
        binding.btnClose.setOnClickListener { finish() }

        if (url.isNotEmpty()) {
            binding.webView.loadUrl(url)
            saveHistory(url, ua)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(webView: WebView, ua: String) {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        settings.userAgentString = if (ua.isNotBlank()) ua else settings.userAgentString
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
    }

    private fun saveHistory(url: String, ua: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getInstance(applicationContext)
            val dao = db.historyDao()
            dao.insert(HistoryEntry(url = url, userAgent = ua))
            dao.pruneToLimit30()
        }
    }

    companion object {
        const val EXTRA_URL = "extra_url"
        const val EXTRA_UA = "extra_ua"
    }
}


