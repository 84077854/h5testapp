package com.example.linklauncher.ui

import android.content.Intent
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebStorage
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.Toast
import android.net.Uri
import java.net.URLEncoder
import com.example.linklauncher.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val pickHistory = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val url = data?.getStringExtra(WebViewActivity.EXTRA_URL).orEmpty()
            val ua = data?.getStringExtra(WebViewActivity.EXTRA_UA).orEmpty()
            // 清空并写入所选记录
            binding.editUrl.setText(url)
            binding.editUa.setText(ua)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpen.setOnClickListener {
            val urlLines = binding.editUrl.text?.toString()?.lines()?.map { it.trim() }?.filter { it.isNotEmpty() }.orEmpty()
            val uaMerged = binding.editUa.text?.toString()?.lines()
                ?.map { it.trim() }
                ?.filter { it.isNotEmpty() }
                ?.joinToString(separator = " ")
                .orEmpty()
            val partnerToken = binding.editPartnerToken.text?.toString()?.trim().orEmpty()

            if (urlLines.isEmpty()) return@setOnClickListener

            urlLines.forEach { rawUrl ->
                val url = appendPartnerToken(rawUrl, partnerToken)
                val ua = uaMerged
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra(WebViewActivity.EXTRA_URL, url)
                intent.putExtra(WebViewActivity.EXTRA_UA, ua)
                startActivity(intent)
            }
        }

        binding.btnHistory.setOnClickListener {
            pickHistory.launch(Intent(this, HistoryActivity::class.java))
        }

        binding.btnClearCache.setOnClickListener {
            // 清除 WebView 缓存与数据
            try {
                WebStorage.getInstance().deleteAllData()
            } catch (_: Throwable) {}
            try {
                CookieManager.getInstance().removeAllCookies(null)
                CookieManager.getInstance().flush()
            } catch (_: Throwable) {}
            try {
                WebView(this).apply {
                    clearCache(true)
                    clearFormData()
                    clearHistory()
                }
            } catch (_: Throwable) {}
            Toast.makeText(this, getString(com.example.linklauncher.R.string.toast_cache_cleared), Toast.LENGTH_SHORT).show()
        }
    }

    private fun appendPartnerToken(inputUrl: String, token: String): String {
        val cleaned = if (inputUrl.startsWith("@")) inputUrl.removePrefix("@") else inputUrl
        if (token.isBlank()) return cleaned
        return try {
            val uri = Uri.parse(cleaned)
            if (uri.isOpaque) {
                val sep = if (cleaned.contains("?")) "&" else "?"
                "$cleaned${sep}partner_token=${URLEncoder.encode(token, "UTF-8")}"
            } else {
                uri.buildUpon().appendQueryParameter("partner_token", token).build().toString()
            }
        } catch (_: Throwable) {
            val sep = if (cleaned.contains("?")) "&" else "?"
            "$cleaned${sep}partner_token=${URLEncoder.encode(token, "UTF-8")}"
        }
    }
}


