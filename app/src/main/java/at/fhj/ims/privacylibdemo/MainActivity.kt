package at.fhj.ims.privacylibdemo

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import at.fhj.ims.privacylib.GaussianBlur
import at.fhj.ims.privacylibdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {
            setLifecycleOwner(this@MainActivity)
            setViewModel(viewModel)
            editTextNumber.setOnLongClickListener {
                viewModel.changeRandomValue()
                true
            }
//            checkBox.setOnClickListener {
//                repeat(10) {
//                    Log.i(TAG, "CheckBox is checked ${checkBox.isChecked()}")
//                }
//            }
        }

        with(binding.imageView) {
            setOnLongClickListener {
                val drawable = drawable as BitmapDrawable
                val bitmap = drawable.bitmap
                val blurredImage = GaussianBlur.blur(this@MainActivity, bitmap, 5.0f)
                setImageBitmap(blurredImage)
                true
            }

            setOnClickListener {
                Log.i(TAG, "CheckBox is checked ${binding.checkBox.isChecked()}")
            }
        }
    }
}