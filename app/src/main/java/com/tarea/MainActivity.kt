package com.tarea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tarea.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btLogin.setOnClickListener{ haceLogin() }
        binding.btRegister.setOnClickListener{ haceRegistro() }
    }

    private fun haceRegistro() {
        val correo = binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(correo, clave)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    //si se hizo el registro
                    val user = auth.currentUser
                    refresca(user)
                }
                else{
                    //si no se hizo el registro
                    Toast.makeText(baseContext, getString(R.string.msg_fallo), Toast.LENGTH_SHORT).show()
                    refresca(null)
                }
            }
    }

    private fun refresca(user: FirebaseUser?) {

        if(user!=null){
            //me paso a la pantalla principal
            val intent = Intent(this,Central::class.java)

            startActivity(intent)
        }

    }

    private fun haceLogin() {
        val correo = binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()

        auth.signInWithEmailAndPassword(correo, clave)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    //si se hizo el registro
                    val user = auth.currentUser
                    refresca(user)
                }
                else{
                    //si no se hizo el registro
                    Toast.makeText(baseContext, getString(R.string.msg_fallo), Toast.LENGTH_SHORT).show()
                    refresca(null)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val usuario = auth.currentUser
        refresca(usuario)
    }
}