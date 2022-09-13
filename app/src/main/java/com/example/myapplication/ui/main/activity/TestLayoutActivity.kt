package com.example.myapplication.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.widget.ViewServer

class TestLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_layout)
        ViewServer.get(this).addWindow(this);
    }

    override fun onResume() {
        super.onResume()
        ViewServer.get(this).setFocusedWindow(this);
    }

    override fun onDestroy() {
        super.onDestroy()
        ViewServer.get(this).removeWindow(this);
    }
}