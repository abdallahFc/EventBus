package com.example.eventbus

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.eventbus.event.ResultData
import com.example.eventbus.event.sendData
import io.reactivex.rxjava3.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MyService : Service() {
    lateinit var disposable: Disposable
    override fun onBind(intent: Intent): IBinder {
        return null!!
    }
    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()
       disposable= DataHolder.listen(sendData::class.java).subscribe {
           val sum=it.num2+it.num1
           DataHolder.send(ResultData(sum))
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}