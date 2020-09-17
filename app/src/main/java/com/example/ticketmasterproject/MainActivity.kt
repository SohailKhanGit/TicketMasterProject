package com.example.ticketmasterproject

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {


    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

////firebase

    private fun checkGooglePlayServices(): Boolean {
        val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        return if (status != ConnectionResult.SUCCESS) {
            Log.e(TAG, "Error")
            // ask user to update google play services.
            false
        } else {
            Log.i(TAG, "Google play services updated")
            true
        }
    }

    /**
     * Here’s what this code does, step-by-step:

    You get the instance of the app that’s tied to the Firebase back end and add a complete listener to it so you know when the task finishes, whether with an error or success.
    You check if the task, which is the result of this function, isn’t successful and return an error to the console.
    If the task is successful, you get the result from it.
    After getting the token, you set it as a string and output it for both the terminal and a toast message.
     */
    private fun initiateFireBase(){
        if(checkGooglePlayServices()){
            FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
                if(!task.isSuccessful){
                    Log.i(TAG,"Error")
                    return@addOnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                val msg = getString(R.string.token_prefix, token)
                Log.d(TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
            }
        }else{
            //You won't be able to send notifications to this device
            Log.w(TAG, "Device doesn't have google play services")
        }
    }

    override fun onResume() {
        super.onResume()
        initiateFireBase()
    }
}