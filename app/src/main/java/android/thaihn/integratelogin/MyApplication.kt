package android.thaihn.integratelogin

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Enable AppEventLogger
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)


        // Google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        GoogleSignIn.getClient(this, gso).silentSignIn()
    }
}
