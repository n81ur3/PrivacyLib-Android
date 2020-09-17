package at.fhj.ims.privacylib

import android.content.Context
import android.graphics.Bitmap
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur

object GaussianBlur {
    private val scale = 1.0f

    fun blur(context: Context, image: Bitmap, radius: Float): Bitmap {
        val width = Math.round(image.width * scale).toInt()
        val height = Math.round(image.height * scale).toInt()

        val blur_radius = when {
            radius <= 0 -> 1f
            radius > 25 -> 25f
            else -> radius
        }

        val input = Bitmap.createScaledBitmap(image, width, height, false)

        val output = Bitmap.createBitmap(input)

        val rs = RenderScript.create(context)
        val blurAlgo = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))

        val alocIn = Allocation.createFromBitmap(rs, input)
        val alocOut = Allocation.createFromBitmap(rs, output)

        blurAlgo.setRadius(blur_radius)
        blurAlgo.setInput(alocIn)
        blurAlgo.forEach(alocOut)

        alocOut.copyTo(output)

        return output
    }


}