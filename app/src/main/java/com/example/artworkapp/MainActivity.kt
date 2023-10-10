package com.example.artworkapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.artworkapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.appBarMain.toolbar.customToolbar
        setSupportActionBar(toolbar)

        binding.appBarMain.fab.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                getString(R.string.under_development),
                Toast.LENGTH_SHORT
            ).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_favorite, R.id.nav_expo
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Listener untuk menutup drawer saat item dipilih
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Ganti fragmen ke HomeFragment
                    navController.navigate(R.id.nav_home)
                }

                R.id.nav_favorite -> {
                    // Ganti fragmen ke FavoriteFragment
                    navController.navigate(R.id.nav_favorite)
                }

                R.id.nav_expo -> {
                    try {
                        installExpoModule()
                    } catch (e: Exception) {
                        Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            // Tutup drawer setelah item dipilih
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun installExpoModule() {
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val moduleExpo = "expo"
        if (splitInstallManager.installedModules.contains(moduleExpo)) {
            moveToExpoActivity()
            Toast.makeText(this, "Open module", Toast.LENGTH_SHORT).show()
        } else {
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleExpo)
                .build()
            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    Toast.makeText(this, "Success installing module", Toast.LENGTH_SHORT).show()
                    moveToExpoActivity()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error installing module", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun moveToExpoActivity() {
        navController.navigate(R.id.nav_expo)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}