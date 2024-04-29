package com.example.madness

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.madness.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private var email: EditText? = null
    private var username: EditText? = null
    private var password: EditText? = null
    private var repeat: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val login : TextView = findViewById(R.id.LogTV)

        login.setOnClickListener{ goToLogIn()}

        binding.button.setOnClickListener{
            username = binding.usernameEV
            email = binding.emailEV
            password = binding.passwordEV
            repeat = binding.repeatEV

            if (validate())register()
        }
    }
    private fun validate(): Boolean {

        if(username?.text.toString().isNotEmpty() && email?.text.toString().isNotEmpty() && password?.text.toString().isNotEmpty() && repeat?.text.toString().isNotEmpty()){
            if(password?.text.toString()==repeat?.text.toString()){
                if (password!!.length() < 6) {
                    Toast.makeText(this, "Your password has to be at least 6 characters long!",Toast.LENGTH_SHORT).show()
                    return false
                }
                var capital = false
                var special = false
                var number = false
                val specialCharacters = setOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '`',
                    '~', '{', '}', ':', ';', '\"','\'', '<', '>', '.', '?', '/')
                val numbers = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

                for (char in password?.text.toString()) {
                    if (numbers.contains(char)) {
                        number = true
                        break
                    }
                }
                for (char in password?.text.toString()) {
                    if (char.isUpperCase()) {
                        capital = true
                        break
                    }
                }
                for (char in password?.text.toString()) {
                    if (specialCharacters.contains(char)) {
                        special = true
                        break
                    }
                }
                if (!capital) {
                    Toast.makeText(this, "Your password should have a capital letter!",Toast.LENGTH_LONG).show()
                    return false
                } else if (!special) {
                    Toast.makeText(this, "Password should have a special sign!",Toast.LENGTH_LONG).show()
                    return false
                } else if (!number) {
                    Toast.makeText(this, "Password should have a number!",Toast.LENGTH_LONG).show()
                    return false
                }
            }else{
                Toast.makeText(this,"the passwords aren't the same",Toast.LENGTH_SHORT).show()
                return false

            }

        }else{
            Toast.makeText(this, "please fill out all your information", Toast.LENGTH_SHORT).show()
            return false
        }

        return true}

    private fun register(){

        firebaseAuth.createUserWithEmailAndPassword(email?.text.toString(), password?.text.toString()).addOnCompleteListener {
            if(it.isSuccessful){
                goToLogIn()
                Toast.makeText(this,"registration succeeded", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "registration failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun goToLogIn() {
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)
        finish()
    }
}