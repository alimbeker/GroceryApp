package arbuz.android.groceryapp.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import arbuz.android.groceryapp.R
import arbuz.android.groceryapp.presentation.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        timer = object : CountDownTimer(2500, 2000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
