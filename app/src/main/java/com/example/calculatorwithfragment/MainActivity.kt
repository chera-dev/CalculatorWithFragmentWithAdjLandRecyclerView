package com.example.calculatorwithfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragmentOne = FragmentOne()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.my_fragmentholder, fragmentOne)
            transaction.commit()
        }
    }
}