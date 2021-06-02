package com.descifrador.rxdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.just(5,6,7).map { ";-)".repeat(it) }
                .subscribe { println(it) }


        val observable = Observable.create<Int> { subscriber->
            println("Log: Create")
            subscriber.onNext(5)
            subscriber.onNext(6)
            subscriber.onNext(7)
            subscriber.onComplete()
            println("Log: Complete")
        }

        observable.subscribe{println("Log: $it")}
        println("Log: Done")

        val observer = object : Observer<Int>{
            override fun onComplete() {
                println("Log: Complete")
            }

            override fun onSubscribe(d: Disposable?) {
                println("Log: On Subscribe")

            }

            override fun onNext(t: Int?) {
                println("Log: next $t")
            }

            override fun onError(e: Throwable?) {
                println("Log: e")
            }
        }
        
        observable.subscribe(observer)



        val consumer = object : Consumer<Int>{
            override fun accept(t: Int?) {
                println("Log Consumer : $t")
            }
        }

        observable.subscribe(consumer)

        //shorter version Consumer

        observable.subscribe{println("Log Consumer Short: $it")}

    }


}
