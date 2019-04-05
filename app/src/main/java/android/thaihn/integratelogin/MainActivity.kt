package android.thaihn.integratelogin

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.thaihn.integratelogin.databinding.ActivityMainBinding
import android.thaihn.integratelogin.facebook.FacebookLoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBinding.btnFacebook.setOnClickListener {
            startActivity(Intent(applicationContext, FacebookLoginActivity::class.java))
        }

        mainBinding.btnGoogle.setOnClickListener {

        }

        mainBinding.btnTwitter.setOnClickListener {

        }
    }
}
