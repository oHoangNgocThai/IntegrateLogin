package android.thaihn.integratelogin

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Enable AppEventLogger
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}
