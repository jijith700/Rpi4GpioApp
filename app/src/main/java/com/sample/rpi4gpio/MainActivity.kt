package com.sample.rpi4gpio

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val TAG = "LedControl"
    private var ledNative: Rpi4Native? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate")
        val btnLed = findViewById<Button>(R.id.btnLed)
        ledNative = Rpi4Native()
        ledNative!!.openDev()

        btnLed.setOnClickListener(View.OnClickListener {
            if (btnLed!!.text == "OFF") {
                Log.d(TAG, "turn on led")
                btnLed!!.text = "ON"
                ledNative!!.devOn()
            } else {
                Log.d(TAG, "turn off led")
                btnLed!!.text = "OFF"
                ledNative!!.devOff()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        ledNative!!.closeDev()
        Log.d(TAG, "onDestroy")
    }
}