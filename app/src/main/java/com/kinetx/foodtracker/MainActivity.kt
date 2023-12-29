package com.kinetx.foodtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.kinetx.foodtracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout : DrawerLayout
    private lateinit var appBarConfiguration : AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.myNavHostFragment)

        val topLevelFragments = setOf(
            R.id.homeFragment,
            R.id.logFoodFragment,
            R.id.logExerciseFragment,
            R.id.listFoodFragment,
            R.id.listExerciseFragment,
            R.id.statsDailyFragment,
            R.id.statsMonthlyFragment)

        appBarConfiguration = AppBarConfiguration(topLevelFragments,drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navDrawer,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }
}