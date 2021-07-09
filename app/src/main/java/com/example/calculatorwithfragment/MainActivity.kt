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
            /*if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Log.e("main","main not saved in landscape")
                val fragmentTwo = FragmentTwo()
                transaction.replace(R.id.my_fragmentholder2, fragmentTwo)
            }*/
            transaction.commit()
        }
        else
            Log.e("aaaaaaaaa","main saved")
        /*if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val fragmentTwo = FragmentTwo()
            Toast.makeText(this, "landscape mode in main", Toast.LENGTH_SHORT).show()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.my_fragmentholder2, fragmentTwo)
            transaction.commit()
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("qqqqqqqqq","main destroyed")
    }
}