package com.example.eventbus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eventbus.databinding.ActivityMainBinding
import com.example.eventbus.event.ResultData
import com.example.eventbus.event.sendData
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    val binding :ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        EventBus.getDefault().register(this)
        binding.button.setOnClickListener {
            val intent=Intent(this,MyService::class.java)
            EventBus.getDefault().post(sendData(binding.editTextNumber.text.toString().toInt(),binding.editTextNumber2.text.toString().toInt()))
            startService(intent)
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(resultData: ResultData) {
        binding.text.text=resultData.sum.toString()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}