package coding.faizal.rakaminchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import coding.faizal.rakaminchallenge.databinding.ActivityMainBinding
import coding.faizal.rakaminchallenge.presentation.ui.HomeActivity

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}