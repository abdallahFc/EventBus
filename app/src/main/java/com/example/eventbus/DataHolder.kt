package com.example.eventbus

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject

object DataHolder {

    private var bus = ReplaySubject .create<Any>()

    fun send(event: Any) {
        bus.onNext(event)
    }
    fun <T : Any> listen(eventType: Class<T>): Observable<T> = bus.ofType(eventType)


}
