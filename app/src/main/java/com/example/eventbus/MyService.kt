package com.example.eventbus

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.eventbus.event.ResultData
import com.example.eventbus.event.sendData
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MyService : Service() {
    override fun onBind(intent: Intent): IBinder {
        return null!!
    }
    override fun onCreate() {
        super.onCreate()
        EventBus.getDefault().register(this)
    }
    @Subscribe
    fun onEvent(resultData: sendData) {
        val num1=resultData.num1
        val num2=resultData.num2
        val sum:Int= num1+num2
        EventBus.getDefault().post(ResultData(sum))
        stopSelf()
    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}