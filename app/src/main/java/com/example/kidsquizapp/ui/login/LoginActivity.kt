package com.example.kidsquizapp.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.kidsquizapp.QuestionActivity

import com.example.kidsquizapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

//        val username = findViewById<EditText>(R.id.username)
//        val password = findViewById<EditText>(R.id.password)
//        val login = findViewById<Button>(R.id.login)
//        val loading = findViewById<ProgressBar>(R.id.loading)

// Initialize Firebase Auth
        auth = Firebase.auth

login.setOnClickListener {
    Log.d("login", "signInWithEmail:success")
    auth.signInWithEmailAndPassword(username.text.toString(), password.text.toString())
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("login", "signInWithEmail:success")
                val user = auth.currentUser
                Log.d("login", user.toString())
                startActivity(Intent(this, DashboardActivity::class.java).putExtra("uid",user?.uid))
                //updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w("login", "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
                //  updateUI(null)
                // ...
            }

            // ...
        }
}
//        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
//            .get(LoginViewModel::class.java)
//
//        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
//            val loginState = it ?: return@Observer
//
//            // disable login button unless both username / password is valid
//            login.isEnabled = loginState.isDataValid
//
//            if (loginState.usernameError != null) {
//                username.error = getString(loginState.usernameError)
//            }
//            if (loginState.passwordError != null) {
//                password.error = getString(loginState.passwordError)
//            }
//        })
//
//        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
//            val loginResult = it ?: return@Observer
//
//            loading.visibility = View.GONE
//            if (loginResult.error != null) {
//                showLoginFailed(loginResult.error)
//            }
//            if (loginResult.success != null) {
//                updateUiWithUser(loginResult.success)
//            }
//            setResult(Activity.RESULT_OK)
//
//            //Complete and destroy login activity once successful
//            finish()
//        })
//
//        username.afterTextChanged {
//            loginViewModel.loginDataChanged(
//                username.text.toString(),
//                password.text.toString()
//            )
//        }
//
//        password.apply {
//            afterTextChanged {
//                loginViewModel.loginDataChanged(
//                    username.text.toString(),
//                    password.text.toString()
//                )
//            }
//
//            setOnEditorActionListener { _, actionId, _ ->
//                when (actionId) {
//                    EditorInfo.IME_ACTION_DONE ->
//                        loginViewModel.login(
//                            username.text.toString(),
//                            password.text.toString()
//                        )
//                }
//                false
//            }
//
//            login.setOnClickListener {
//                loading.visibility = View.VISIBLE
//                loginViewModel.login(username.text.toString(), password.text.toString())
//            }
//        }
    }
    private fun updateUI(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        startActivity(Intent(this, DashboardActivity::class.java))
        Toast.makeText(
            applicationContext,
            "$welcome Student",
            Toast.LENGTH_LONG
        ).show()
    }
    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        startActivity(Intent(this, DashboardActivity::class.java))
        Toast.makeText(
            applicationContext,
            "$welcome Student",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}