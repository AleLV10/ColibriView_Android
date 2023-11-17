package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityRegistrarNuevoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrarNuevoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarNuevoBinding
    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    //VARIABLES DE LOS DATOS QUE VAMOS A REGISTRAR
    private var name: String = ""
    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = Firebase.auth
        // [END initialize_auth]


        binding = ActivityRegistrarNuevoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.iniciar.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.crear.setOnClickListener {
            // Do something in response to button click
            email=binding.email1.text.toString().trim()
            name=binding.name1.text.toString().trim()
            password=binding.password1.text.toString().trim()


                val UsuarioNuevo = UsuarioNuevo(name =name,mail = email, contrasena = password)
            Log.i("US Name","${name}")
            Log.i("US Mail","${email}")
            Log.i("US Password","${password}")
            if(email!=""&&name!=""&&password!="")
                createAccount(email,password)
            else
                Toast.makeText(
                    baseContext,
                    "Faltan datos requeridos",
                    Toast.LENGTH_SHORT,
                ).show()

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

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Asegurese que los datos \ncumplan las normas.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }


    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                // Email Verification sent
            }
        // [END send_email_verification]
    }

    private fun updateUI(user: FirebaseUser?) {
    }

    private fun reload() {
    }
    companion object {
        private const val TAG = "EmailPassword"
    }
}