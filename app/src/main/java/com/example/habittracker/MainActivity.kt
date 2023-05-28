package com.example.habittracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Первый раз?
        loadData()

        if (userFirstTime) {
            userFirstTime = false
            saveData()

            val i = Intent(this, IntroActivity::class.java)
            startActivity(i)
            finish()
        }

        setupActionBarWithNavController(findNavController(R.id.navHostFragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun saveData() {
        val sp = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
        sp.edit().apply {
            putBoolean("BOOLEAN_FIRST_TIME", userFirstTime)
            apply()
        }
    }

    private fun loadData() {
        val sp = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
        userFirstTime = sp.getBoolean("BOOLEAN_FIRST_TIME", true)

    }

}
}