package com.example.userloginsample.domain

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class EventBus {
    private val _bus = PublishSubject.create<Conversion>()

    fun post(conversion: Conversion) {
        _bus.onNext(conversion)
    }

    val bus: Observable<Conversion> = _bus
}