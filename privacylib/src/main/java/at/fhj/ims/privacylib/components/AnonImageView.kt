package at.fhj.ims.privacylib.components

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import at.fhj.ims.privacylib.GaussianBlur
import at.fhj.ims.privacylib.R

class AnonImageView: AppCompatImageView {
    private var blurradius = 10.0f

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
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.AnonImageView, defStyleAttr, 0)
            blurradius = typedArray.getFloat(R.styleable.AnonImageView_blurfactor, 5.0f)
            return
        }
        blurradius = 10.0f
    }

    override fun performLongClick(): Boolean {
        val drawable = drawable as BitmapDrawable
        val bitmap = drawable.bitmap
        val blurredBitmap = GaussianBlur.blur(context, bitmap, blurradius)
        setImageBitmap(blurredBitmap)
        return super.performLongClick()
    }
}