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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onStart() {
    super.onStart()

    helloButtonClickStream = findViewById<Button>(R.id.bt_hello).clicks()
    helloButtonClickStream
      .subscribe { Log.d(TAG, "helloButtonClicked") }
      .addTo(disposables)
  }

  override fun onStop() {
    super.onStop()
    disposables.clear()
  }
}
