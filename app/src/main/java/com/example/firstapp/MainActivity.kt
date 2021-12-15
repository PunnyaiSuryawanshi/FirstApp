package com.example.firstapp

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.firebase.ui.auth.AuthUI
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity() : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goto_profile_button = findViewById<Button>(R.id.main_profile_Button)
        val textview1 = findViewById<TextView>(R.id.textview)
        goto_profile_button.setOnClickListener {
            startActivity(Intent(this, ProfileActivity2::class.java))
        }
        AndroidNetworking.initialize(applicationContext)

        AndroidNetworking.get("https://doubtconnect.in:3001/")
            .build()
            .getAsString(object : StringRequestListener{
                override fun onResponse(response: String?) {
                   Log.d("MainActivity","Response from server: $response")
                    textview1.text = response
                }

                override fun onError(anError: ANError?) {
                   Log.d("MainActivity","Error: ${anError.toString()}")
                }
            })



      val name = listOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,26, 27,
          28, 29, 30, 31, 32, 33, 34, 35, 36, 37,38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57,
          58, 59, 60, 61, 62,63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87,
          88, 89, 90, 91,92, 93, 94, 95, 96, 97, 98, 99,100)

        val recycler_view = findViewById<RecyclerView>(R.id.recyclerview)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = Myadapter(name)


        val signout_button = findViewById<Button>(R.id.signout_button)
        signout_button.setOnClickListener {
            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    Toast.makeText(this,"User signed out successfully",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, loginActivity::class.java))
                }
        }

val button = findViewById<Button>(R.id.signout_button)
        button.setOnClickListener {
            val intent = Intent(this, ProfileActivity2::class.java)
            startActivity(intent)

        }

    }


    suspend fun db(){
        val colorDao = ColorDataBase.getInstance(applicationContext).colorDao()
        val new_color = Color(hex = "#FFFFFFF", name = "White", _id = 1)


        colorDao.insert(new_color)
        Log.d("MainActivity", colorDao.getAll()[0].toString())
    }


    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity","OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity","OnStop")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity","OnDestroy")
    }
}










