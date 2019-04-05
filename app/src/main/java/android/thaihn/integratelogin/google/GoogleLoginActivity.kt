package android.thaihn.integratelogin.google

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.thaihn.integratelogin.R
import android.thaihn.integratelogin.databinding.ActivityGoogleLoginBinding
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton

class GoogleLoginActivity : AppCompatActivity() {

    companion object {
        private val TAG = GoogleLoginActivity::class.java.name

        private const val REQUEST_CODE_SIGN_IN = 1
    }

    private lateinit var mGoogleLoginBinding: ActivityGoogleLoginBinding

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGoogleLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_google_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val account = GoogleSignIn.getLastSignedInAccount(this)

        Log.d(TAG, "Account: ${account.toString()}")

        mGoogleLoginBinding.signInButton.setSize(SignInButton.SIZE_STANDARD)

        mGoogleLoginBinding.signInButton.setOnClickListener {
            val intent = mGoogleSignInClient.signInIntent
            startActivityForResult(intent, REQUEST_CODE_SIGN_IN)
        }
    }
}
