package com.perelandrax.rxjava.buttonclickcountsample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

class MainActivity : AppCompatActivity() {

  companion object {
    private var TAG = MainActivity::class.java.simpleName
  }

  private val disposables = CompositeDisposable()

  private lateinit var helloButtonClickStream: Observable<Unit>
  private lateinit var helloButtonClickMapTo1Stream: Observable<Int>
  private lateinit var helloButtonClickCountStream: Observable<Int>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onStart() {
    super.onStart()

//    setupClickCountStream1()
    setupClickCountStream2()
  }

  private fun setupClickCountStream1() {
    helloButtonClickStream = findViewById<Button>(R.id.bt_hello).clicks()
    helloButtonClickMapTo1Stream = helloButtonClickStream.map { 1 }
    helloButtonClickCountStream = helloButtonClickMapTo1Stream.scan { t1, t2 -> t1 + t2 }

    helloButtonClickCountStream
      .subscribe { Log.d(TAG, "helloButtonClicked : $it") }
      .addTo(disposables)
  }

  private fun setupClickCountStream2() {
    findViewById<Button>(R.id.bt_hello).clicks()
      .map { 1 }
      .scan { t1, t2 -> t1 + t2 }
      .subscribe { Log.d(TAG, "helloButtonClicked : $it") }
      .addTo(disposables)
  }

  override fun onStop() {
    super.onStop()
    disposables.clear()
  }
}
