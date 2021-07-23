package com.example.firebasenotificationapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity onCreate")

        FirebaseMessaging.getInstance().subscribeToTopic("/topics/NEWS")
    }

    companion object {
        var TAG = MainActivity::class.java.simpleName
    }
}