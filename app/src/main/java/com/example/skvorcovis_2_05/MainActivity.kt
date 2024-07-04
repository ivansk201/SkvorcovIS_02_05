package com.example.skvorcovis_2_05



import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences= getSharedPreferences("MyPrefs", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        val etlogin = findViewById<EditText>(R.id.editText)
        val etPass = findViewById<EditText>(R.id.editText2)
        val btnsignin = findViewById<Button>(R.id.butt1)
        btnsignin.setOnClickListener {
            val  login = etlogin.text.toString()
            val  password = etPass.text.toString()
            if (login.isEmpty() || password.isEmpty())
            {
                Snackbar.make(it, "Введите логин и пароль", Snackbar.LENGTH_LONG)
                    .setAction("OK") {}
                    .show()
            }
            else
            {
                val savedlogin = sharedPreferences.getString("LOGIN", "")
                val savedpass = sharedPreferences.getString("PASSWORD", "")
                if (savedlogin.isNullOrEmpty() && savedpass.isNullOrEmpty())
                {
                    editor.putString("LOGIN", login)
                    editor.putString("PASSWORD", password)
                    editor.apply()
                    navigateToMainScreen()
                }
                else if (login == savedlogin && password == savedpass)
                {
                    navigateToMainScreen()
                }
                else
                {
                    Toast.makeText(this, "Неправильный логин или пароль!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity3::class.java)
        startActivity(intent)
        finish()
    }
}
