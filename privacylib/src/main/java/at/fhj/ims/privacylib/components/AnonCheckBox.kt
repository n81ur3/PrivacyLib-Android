package at.fhj.ims.privacylib.components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import at.fhj.ims.privacylib.BooleanNoise
import at.fhj.ims.privacylib.R

class AnonCheckBox: AppCompatCheckBox {
    private var accuracy = 0.5

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
        attrs?.let {
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.AnonCheckBox, defStyleAttr, 0)
            accuracy = typedArray.getFloat(R.styleable.AnonCheckBox_accuracy, 0.5f).toDouble()
            return
        }
        accuracy = 0.5
    }

    fun isProbableChecked(): Boolean {
        return BooleanNoise.addNoise(isChecked(), accuracy)
    }
}