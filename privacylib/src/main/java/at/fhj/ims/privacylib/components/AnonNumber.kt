package at.fhj.ims.privacylib.components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import at.fhj.ims.privacylib.R
import at.fhj.ims.privacylib.RandomDPNoise
import kotlinx.android.synthetic.main.anon_number_layout.view.*


class AnonNumber: LinearLayoutCompat {
    private val TAG = "AnonNumber"
    private var sensitivity = 1.2
    private var epsilon = 0.8

    constructor(context: Context) : super(context) {
        setStyledAttributes(context, null, 0)
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setStyledAttributes(context, attrs, 0)
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setStyledAttributes(context, attrs, defStyleAttr)
        init()
    }

    private fun setStyledAttributes(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        attrs?.let {
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.AnonNumber, defStyleAttr, 0)
            sensitivity = typedArray.getFloat(R.styleable.AnonNumber_sensitivity, 1.0f).toDouble()
            epsilon = typedArray.getFloat(R.styleable.AnonNumber_epsilon, 0.8f).toDouble()
            return
        }
        sensitivity = 1.2
        epsilon = 0.8
    }

    private fun init() {
        inflate(context, R.layout.anon_number_layout, this)
        anon_button.setOnClickListener {
            Log.i(TAG, "current sensitivity: $sensitivity, current epsilon: $epsilon")
            val currentValue: Double = java.lang.Double.parseDouble(anon_number.text.toString())
            anon_number.setText(RandomDPNoise.addNoise(currentValue, sensitivity, epsilon).toString())
        }


    }
}