package at.fhj.ims.privacylib.components

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import at.fhj.ims.privacylib.RandomDPNoise

class EditTextDP : AppCompatEditText {

    constructor(context: Context?) : super(context!!)

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr)

    //override fun onTouchEvent(event: MotionEvent?): Boolean {
    //val newEdit = edit?.replace(0, 10, "testing", 0, 6)
    //text = newEdit
//        if (event?.action == MotionEvent.ACTION_DOWN) {
//            val randValue = RandomDPNoise.addNoise(8.8, 1.0, 0.5)
//            setText(randValue.toString(), BufferType.EDITABLE)
//        }
    //return super.onTouchEvent(event)
    //}

    override fun performLongClick(): Boolean {
        setText("Long press performed")
        return super.performLongClick()
    }

}