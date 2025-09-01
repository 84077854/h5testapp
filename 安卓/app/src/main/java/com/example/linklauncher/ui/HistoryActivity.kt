package com.example.linklauncher.ui

import android.content.Intent
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linklauncher.data.AppDatabase
import com.example.linklauncher.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HistoryActivity : ComponentActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = AppDatabase.getInstance(applicationContext).historyDao()
        adapter = HistoryAdapter(
            onItemClick = { entry ->
                val data = Intent().apply {
                    putExtra(WebViewActivity.EXTRA_URL, entry.url)
                    putExtra(WebViewActivity.EXTRA_UA, entry.userAgent)
                }
                setResult(Activity.RESULT_OK, data)
                finish()
            },
            onDeleteClick = { entry ->
                lifecycleScope.launch {
                    dao.deleteById(entry.id)
                }
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            dao.getAll().collectLatest { list ->
                adapter.submitList(list)
            }
        }
    }
}


