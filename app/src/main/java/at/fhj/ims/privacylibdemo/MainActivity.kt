package at.fhj.ims.privacylibdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import at.fhj.ims.privacylibdemo.view.BasicsDemoFragment
import at.fhj.ims.privacylibdemo.view.ImageDemoFragment
import at.fhj.ims.privacylibdemo.view.RadioGroupDemoFragment
import at.fhj.ims.privacylibdemo.view.TextDemoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val basicsDemoFragment = BasicsDemoFragment.newInstance()
        val imageDemoFragment = ImageDemoFragment.newInstance()
        val textDemoFragment = TextDemoFragment.newInstance()
        val radioGroupDemoFragment = RadioGroupDemoFragment.newInstance()

        setCurrentFragment(basicsDemoFragment)

        val bottomNavController = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavController.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item_basics -> setCurrentFragment(basicsDemoFragment)
                R.id.menu_item_image -> setCurrentFragment(imageDemoFragment)
                R.id.menu_item_text -> setCurrentFragment(textDemoFragment)
                R.id.menu_item_radiogroup -> setCurrentFragment(radioGroupDemoFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(newFragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.placeholder_fragment, newFragment)
            commit()
        }
    }
}