package com.example.dorstip_app.LoginScreen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.R
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.dorstip_app.R.layout.login_screen)

        var url = "https://hetwapen.projects.adainforma.tk/api.json"
        var queue : RequestQueue = Volley.newRequestQueue(this.applicationContext)
        var request : StringRequest = StringRequest(Request.Method.GET, url,
            {response->
              //Wanneer server reageert,  wordt er data opgehald
                println(response.toString())

            },
            {error->
                //Melding geven aan gebruiker.
                Toast.makeText(this.applicationContext, "Niet gevonden", Toast.LENGTH_LONG).show()
            })

        queue.add(request)
    }

}