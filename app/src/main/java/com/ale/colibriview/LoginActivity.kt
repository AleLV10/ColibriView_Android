package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ingresar.setOnClickListener{
           // val UsuarioNuevo = UsuarioNuevo(name =binding.name,mail = binding.correo.text.toString().trim())
            email=binding.correo.text.toString().trim()
            password=binding.contras.text.toString().trim()
            Log.i("US Mail","${email}")
            Log.i("US Password","${password}")
            if(email!=""&&password!="")
                signIn(email, password)
            else
                Toast.makeText(
                    baseContext,
                    "Faltan datos requeridos",
                    Toast.LENGTH_SHORT,
                ).show()
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }

        binding.olvida.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, restablecer::class.java)
            startActivity(intent)
            finish()
        }
        binding.registrarte.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, RegistrarNuevoActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.anonimo.setOnClickListener{

        }

    }
    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }
    // [END on_start_check_user]


    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    val intent = Intent(this, Inicio::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Revisa que los datos sean correctos",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }


    private fun updateUI(user: FirebaseUser?) {
    }
    private fun reload() {
    }
    companion object {
        private const val TAG = "EmailPassword"
    }
}