package com.project.foodrecipes.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.project.foodrecipes.databinding.LogInActivityBinding

class ActivityLogIn : AppCompatActivity() {
    private var binding: LogInActivityBinding? = null
    private var auth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null == binding) binding = LogInActivityBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        if (auth?.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        setUpUi()
    }

    private fun setUpUi() {
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        binding?.signUpTV?.setOnClickListener {
            binding?.signInTV?.alpha = .5f
            binding?.signUpTV?.alpha = 1f
            binding?.userName?.visibility = View.VISIBLE
            binding?.enterName?.visibility = View.VISIBLE
            binding?.idBtnRegister?.visibility = View.VISIBLE
            binding?.idBtnSignIn?.visibility = View.GONE
        }
        binding?.signInTV?.setOnClickListener {
            binding?.signUpTV?.alpha = .5f
            binding?.signInTV?.alpha = 1f
            binding?.userName?.visibility = View.GONE
            binding?.enterName?.visibility = View.GONE
            binding?.idBtnRegister?.visibility = View.GONE
            binding?.idBtnSignIn?.visibility = View.VISIBLE
        }

        binding?.idBtnRegister?.setOnClickListener(View.OnClickListener {
            val email = binding?.enterEmail?.text?.toString()?.trim()
            val password = binding?.enterPassword?.text?.toString()?.trim()
            if (TextUtils.isEmpty(email)) {
                binding?.enterEmail?.error = "Email is Required."
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                binding?.enterPassword?.error = "Password is Required."
                return@OnClickListener
            }
            if (password != null) {
                if (password.length < 6) {
                    binding?.enterPassword?.error = "Password Must be >= 6 Characters"
                    return@OnClickListener
                }
            }

            binding?.progressBar?.visibility = View.VISIBLE
            if (email != null && password != null) {
                auth?.createUserWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            binding?.progressBar?.visibility = View.GONE
                        } else {
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding?.progressBar?.visibility = View.GONE
                        }
                    }
            }
        })
        binding?.idBtnSignIn?.setOnClickListener(View.OnClickListener {
            val email = binding?.enterEmail?.text?.toString()?.trim()
            val password = binding?.enterPassword?.text?.toString()?.trim()
            if (TextUtils.isEmpty(email)) {
                binding?.enterEmail?.error = "Email is Required."
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                binding?.enterPassword?.error = "Password is Required."
                return@OnClickListener
            }
            if (password != null) {
                if (password.length < 6) {
                    binding?.enterPassword?.error = "Password Must be >= 6 Characters"
                    return@OnClickListener
                }
            }
            if (email != null && password != null) {
                auth?.signInWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        })

        binding?.progressBar?.visibility = View.GONE

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}