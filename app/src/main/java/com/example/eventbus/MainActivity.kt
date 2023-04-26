package com.example.eventbus

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eventbus.databinding.ActivityMainBinding
import com.example.eventbus.event.ResultData
import com.example.eventbus.event.sendData
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
    val binding :ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var disposable: Disposable
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val intent=Intent(this,MyService::class.java)
            DataHolder.send(sendData(binding.editTextNumber.text.toString().toInt(),binding.editTextNumber2.text.toString().toInt()))
            startService(intent)
        }
         disposable= DataHolder.listen(ResultData::class.java).subscribe {
            binding.text.text=it.sum.toString()
        }
    }
    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }
}