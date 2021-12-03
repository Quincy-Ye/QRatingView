package com.yeqingqing.qratingview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val qrv2 = findViewById<QRatingView>(R.id.qrv_main2)
        qrv2.selectedCount = 5
        qrv2.available = false


        val commence1 = findViewById<AppCompatTextView>(R.id.main_tv_commence)
        findViewById<QRatingView>(R.id.qrv_main3).setOnSelectedListener(object :
            QRatingView.OnSelectedListener {
            override fun onSelected(selectedCount: Int) {
                when (selectedCount) {
                    1 -> commence1.text = "很差"
                    2 -> commence1.text = "有待改进"
                    3 -> commence1.text = "还行"
                    4 -> commence1.text = "不错"
                    5 -> commence1.text = "非常可以"
                }
            }

        })
        val commence2 = findViewById<AppCompatTextView>(R.id.main_tv_commence2)
        findViewById<QRatingView>(R.id.qrv_main4).setOnSelectedListener(object :
            QRatingView.OnSelectedListener {
            override fun onSelected(selectedCount: Int) {
                when (selectedCount) {
                    1 -> commence2.text = "很差"
                    2 -> commence2.text = "有待改进"
                    3 -> commence2.text = "还行"
                    4 -> commence2.text = "不错"
                    5 -> commence2.text = "非常可以"
                }
            }

        })

    }
}