package at.fhj.ims.privacylibdemo.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import at.fhj.ims.privacylib.RandomDPNoise

class DpEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //val newEdit = edit?.replace(0, 10, "testing", 0, 6)
        //text = newEdit
        val randValue = RandomDPNoise.addNoise(8.8, 1.0, 0.5)
        setText(randValue.toString(), BufferType.EDITABLE)
        return super.onTouchEvent(event)
    }

}