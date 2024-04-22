package com.example.dorstip_app


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import java.sql.DriverManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host = "127.0.0.1"
        val dbname = "dorstip_app"//"DBNAAM"
        val user = "root" //"USER"
        val pass = "root" //"996bW6@jz"

        try {
            Class.forName("com.mysql.jdbc.Driver")
            //Toegang geven om een externe database aan te mogen spreken
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            //Einde
            var conn = DriverManager.getConnection(
                "jdbc:mysql://${host}/${dbname}",
                "${user}",
                "${pass}"
            )

            Toast.makeText(this, "Success!", Toast.LENGTH_LONG).show()
        }catch(ex: Exception){
            println("Error: ${ex.message}")
        }
    }
}