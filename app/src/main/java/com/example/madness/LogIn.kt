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
import com.example.madness.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    private lateinit var binding:  ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private var email: EditText? = null
    private var password: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signUp : TextView = findViewById(R.id.SignUpTV)

        signUp.setOnClickListener{goToSignUp()}

        binding.button.setOnClickListener{
            email = binding.emailEV
            password = binding.passwordEV

            if (validate())register()

        }
    }
    private fun validate(): Boolean {

        if (email?.text.toString().isEmpty() || password?.text.toString().isEmpty()) {
            Toast.makeText(this, "please fill out all your information", Toast.LENGTH_SHORT).show()
            return false
        }
        return true}


    private fun register(){

        firebaseAuth.signInWithEmailAndPassword(email?.text.toString(), password?.text.toString()).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this, "log in succeeded", Toast.LENGTH_SHORT).show()
                goToMain()
            }else{
                Toast.makeText(this, "log in failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun goToSignUp() {
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToMain() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }

}
