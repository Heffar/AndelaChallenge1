package dz.salimi.salim.andelachallenge1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileBtn.setOnClickListener {
            val profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }

        aboutBtn.setOnClickListener {
            val aboutIntent = Intent(this, AboutActivity::class.java)
            startActivity(aboutIntent)
        }
    }
}
