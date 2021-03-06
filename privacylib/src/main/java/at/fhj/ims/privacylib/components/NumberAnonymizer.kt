package at.fhj.ims.privacylib.components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.LinearLayoutCompat
import at.fhj.ims.privacylib.R
import at.fhj.ims.privacylib.RandomDPNoise
import kotlinx.android.synthetic.main.anon_number_layout.view.*


class NumberAnonymizer: LinearLayoutCompat {
    private val TAG = "AnonNumber"
    private var sensitivity = 1.2
    private var epsilon = 0.8
    private var precision = 2

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
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.NumberAnonymizer, defStyleAttr, 0)
            sensitivity = typedArray.getFloat(R.styleable.NumberAnonymizer_sensitivity, 1.0f).toDouble()
            epsilon = typedArray.getFloat(R.styleable.NumberAnonymizer_epsilon, 0.8f).toDouble()
            precision = typedArray.getInteger(R.styleable.NumberAnonymizer_precision, 2)
            return
        }
        sensitivity = 1.2
        epsilon = 0.8
        precision = 2
    }

    private fun init() {
        inflate(context, R.layout.anon_number_layout, this)
        anon_button.setOnClickListener {
            Log.i(TAG, "current sensitivity: $sensitivity, current epsilon: $epsilon")
            val currentValue: Double = java.lang.Double.parseDouble(anon_number.text.toString())
            val result = RandomDPNoise.addNoise(currentValue, sensitivity, epsilon)
            val roundedResult = "%.${precision}f".format(result)
            anon_number.setText(roundedResult)
        }
    }

    fun getAnonNumber(): Double {
        return anon_number.text.toString().toDouble()
    }
}