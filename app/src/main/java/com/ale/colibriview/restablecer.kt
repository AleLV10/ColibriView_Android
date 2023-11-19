package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityRestablecerBinding
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class restablecer : AppCompatActivity() {
    private lateinit var binding: ActivityRestablecerBinding
    private var email: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestablecerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.enviar.setOnClickListener {
            email=binding.CorreoRestablecer.text.toString().trim()
            if(email!="")
            {
                Firebase.auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "Email sent.")
                            Toast.makeText(
                                baseContext,
                                "Email enviado",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }

            // Do something in response to button click
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun updateUI(user: FirebaseUser?) {

    }
    private fun reload() {
    }
    companion object {
        private const val TAG = "EmailPassword"
    }
}