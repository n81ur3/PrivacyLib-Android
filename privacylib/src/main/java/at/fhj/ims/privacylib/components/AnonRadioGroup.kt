package at.fhj.ims.privacylib.components

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.RadioButton
import android.widget.RadioGroup
import at.fhj.ims.privacylib.BoundedProbableSelection
import at.fhj.ims.privacylib.R

class AnonRadioGroup : RadioGroup {
    private var accuracy = 0.5

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        setStyledAttributes(context, attrs)
    }

    private fun setStyledAttributes(context: Context, attrs: AttributeSet) {
        val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.AnonRadioGroup, 0, 0)
        accuracy = typedArray.getFloat(R.styleable.AnonRadioGroup_accuracy, 0.5f).toDouble()
    }

    fun getSelectedAnonymized(activity: Activity): Int {
        val currentSelection = checkedRadioButtonId
        val radioButton = activity.findViewById<RadioButton>(currentSelection)
        val selectedIndex = indexOfChild(radioButton)
        return BoundedProbableSelection.getInteger(selectedIndex, accuracy, 0, childCount - 1)
    }
}