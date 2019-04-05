package android.thaihn.integratelogin.facebook

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.thaihn.integratelogin.R
import android.thaihn.integratelogin.databinding.ActivityFacebookLoginBinding
import android.util.Log
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult

class FacebookLoginActivity : AppCompatActivity() {

    companion object {
        private val TAG = FacebookLoginActivity::class.java.simpleName

        private const val PERMISSION_EMAIL = "email"
        private const val PERMISSION_PUBLIC_PROFILE = "public_profile"

    }

    private lateinit var facebookLoginBinding: ActivityFacebookLoginBinding

    private lateinit var mCallbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        facebookLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_facebook_login)

        mCallbackManager = CallbackManager.Factory.create()
        facebookLoginBinding.loginButton.setReadPermissions(createPermission())
        // If you are using in a fragment, call loginButton.setFragment(this)

        facebookLoginBinding.loginButton.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {

            override fun onCancel() {
                Log.d(TAG, "onCancel()")
            }

            override fun onError(error: FacebookException?) {
                Log.d(TAG, "onError: $error")
            }

            override fun onSuccess(result: LoginResult?) {
                Log.d(TAG, "onSuccess: ${result.toString()}")
            }
        })

        facebookLoginBinding.tvStatus.text = "${checkLoginStatus()}"
    }

    private fun checkLoginStatus(): Boolean {
        val accessToken = AccessToken.getCurrentAccessToken()
        return accessToken != null && !accessToken.isExpired
    }

    private fun createPermission(): List<String> {
        return arrayListOf(PERMISSION_EMAIL, PERMISSION_PUBLIC_PROFILE)
    }

}
