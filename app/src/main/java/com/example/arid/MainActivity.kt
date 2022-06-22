package com.example.arid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.arid.Fragment.*
import com.example.arid.databinding.ActivityMainBinding
import com.iammert.library.readablebottombar.ReadableBottomBar

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, HomeFragment())
        transaction.commit()

        binding!!.redableBottomBar.setOnItemSelectListener(object :
            ReadableBottomBar.ItemSelectListener {
            override fun onItemSelected(index: Int) {
                val transaction = supportFragmentManager.beginTransaction()
                when (index) {
                    0 -> {

                        transaction.replace(R.id.container, HomeFragment())
                    }
                    1 -> {

                        transaction.replace(R.id.container, NotificationFragment())
                    }
                    2 -> {

                        transaction.replace(R.id.container, AddPostFragment())
                    }
                    3 -> {

                        transaction.replace(R.id.container, SearchFragment())
                    }
                    4 -> {

                        transaction.replace(R.id.container, ProfileFragment())
                    }
                }
                transaction.commit()
            }
        })

    }
}