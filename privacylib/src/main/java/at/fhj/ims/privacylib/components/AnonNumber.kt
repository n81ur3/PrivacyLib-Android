package at.fhj.ims.privacylib.components

import android.content.Context
import android.content.res.TypedArray
import android.text.InputType
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import at.fhj.ims.privacylib.R
import at.fhj.ims.privacylib.RandomDPNoise
import java.math.BigDecimal

class AnonNumber: AppCompatEditText {
    var sensitivity = 1.0
    var epsilon = 1.0
    var precision: Int = 2

    constructor(context: Context) : super(context) {
        setStyledAttributes(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setStyledAttributes(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setStyledAttributes(context, attrs, defStyleAttr)
    }

    private fun setStyledAttributes(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        inputType = InputType.TYPE_CLASS_NUMBER
        attrs?.let {
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.AnonNumber, defStyleAttr, 0)
            precision = typedArray.getInt(R.styleable.AnonNumber_precision, 2)
            sensitivity = typedArray.getFloat(R.styleable.AnonNumber_sensitivity, 1.0f).toDouble()
            epsilon = typedArray.getFloat(R.styleable.AnonNumber_epsilon, 1.0f).toDouble()
            return
        }
        sensitivity = 1.0
        epsilon = 1.0
        precision = 2
    }

    fun getAnonymizedNumber(): Double {
        if (text.isNullOrEmpty()) return 0.0
        val currentValue = text.toString().toDouble()
        val noisedValue = RandomDPNoise.addNoise(currentValue, sensitivity, epsilon)
        val roundedValue = BigDecimal(noisedValue).setScale(precision, BigDecimal.ROUND_HALF_EVEN).toDouble()
        return roundedValue

    }
}