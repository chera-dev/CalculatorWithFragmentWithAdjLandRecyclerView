package com.example.calculatorwithfragment

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("main","main")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            Log.e("zzzzzzzzzzz","main not saved")
            val fragmentOne = FragmentOne()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.my_fragmentholder, fragmentOne)
            transaction.commit()
        }
        else
            Log.e("aaaaaaaaa","main saved")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("qqqqqqqqq","main destroyed")
    }
}