package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityPerfilUsuarioBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class perfil_usuario : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilUsuarioBinding
    private lateinit var auth: FirebaseAuth
    private val TAG = "MainActivity"
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var photoUrl: String

    val pickmedia=registerForActivityResult(PickVisualMedia()){ uri->
        if(uri!=null)
        {
            binding.imgOjo.setImageURI(uri)
            //Imagen seleccionada
           // Log.i("aris","seleccionado")
            val user = Firebase.auth.currentUser

            val profileUpdates = userProfileChangeRequest {
                photoUri = Uri.parse(uri.toString())
            }

            user!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Imagen Actualizada")
                        Toast.makeText(
                            baseContext,
                            "Imagen Actualizada",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }
            // [END update_profile]
        }
        else
        {
            Log.i("aris","NO seleccionado")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = Firebase.auth
        binding.mnuBarraUsuario.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraUsuario.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Cards_Test::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraUsuario.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, ResultadosActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraUsuario.usuario.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this,perfil_usuario::class.java)
            startActivity(intent)
            finish()
        }
        binding.cerrarSesion.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        //return intent
        createIntent()
        parseResult(Activity.RESULT_OK,createIntent())
        getUserProfile()

        if(name==null||name=="")
        {
            binding.perfilName.setText(email)
            binding.txtNameUser.text=email

        }
        else {
            binding.perfilName.setText(name)
            binding.txtNameUser.text=name

        }
       // if(photoUrl==null||photoUrl=="")
        binding.imgOjo.setImageResource(R.drawable.usuario)
     //   else
     //       binding.imgOjo.setImageURI(Uri.parse(photoUrl))
        binding.perfilName.isEnabled = false
        binding.perfilCorreo.setText(email+"")
        binding.perfilCorreo.isEnabled=false

        binding.guardarCambios.isEnabled=false

        binding.editarNombre.setOnClickListener{
            binding.perfilName.isEnabled = true
            binding.guardarCambios.isEnabled=true

        }
        binding.editarCorreo.setOnClickListener{
            binding.perfilCorreo.isEnabled = true
            binding.guardarCambios.isEnabled=true

        }
        binding.imgOjo.setOnClickListener{
            pickmedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))

        }

        binding.guardarCambios.setOnClickListener {
            if(binding.perfilName.isEnabled==true)
                updateProfile()
            if(binding.perfilCorreo.isEnabled==true)
                updateEmail()
            if(binding.imgOjo.isEnabled==true)
                updateProfile()
            binding.perfilName.isEnabled = false
            binding.perfilCorreo.isEnabled=false
            binding.guardarCambios.isEnabled=false
        }
        binding.EliminarCuenta.setOnClickListener {
            val user = Firebase.auth.currentUser!!

            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User account deleted.")
                    }
                }
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun createIntent(): Intent {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        return intent
    }
    fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        if (resultCode == ComponentActivity.RESULT_OK) {
            val data = intent?.data
            return data
        }

        return null
    }
    private fun getUserProfile() {
        // [START get_user_profile]
        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            name = it.displayName.toString()
            email = it.email.toString()
            photoUrl = it.photoUrl.toString()
            // Check if user's email is verified
            val emailVerified = it.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            val uid = it.uid
            //Toast.makeText(this,"$uid", Toast.LENGTH_SHORT).show()
        }
        // [END get_user_profile]
    }
    private fun updateProfile() {
        // [START update_profile]
        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = binding.perfilName.text.toString().trim()
            //photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg")
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated.")
                    Toast.makeText(
                        baseContext,
                        "Usuario Actualizado",
                        Toast.LENGTH_SHORT,
                    ).show()


                }
            }
        // [END update_profile]
    }

    private fun updateEmail() {
        // [START update_email]
        val user = Firebase.auth.currentUser

        user!!.updateEmail(binding.perfilCorreo.text.toString().trim())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User email address updated.")
                    Toast.makeText(
                        baseContext,
                        "Correo electronico actualizado",
                        Toast.LENGTH_SHORT,
                    ).show()
                }

            }

        // [END update_email]
    }

    private fun updatePassword() {
        // [START update_password]
        val user = Firebase.auth.currentUser
        val newPassword = "SOME-SECURE-PASSWORD"

        user!!.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User password updated.")
                }
            }
        // [END update_password]
    }
}