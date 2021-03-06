package at.fhj.ims.privacylibdemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import at.fhj.ims.privacylibdemo.viewmodel.ImageDemoViewModel
import at.fhj.ims.privacylibdemo.R
import kotlinx.android.synthetic.main.image_demo_fragment.*

class ImageDemoFragment : Fragment() {

    companion object {
        fun newInstance() = ImageDemoFragment()
    }

    private lateinit var viewModel: ImageDemoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_demo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ImageDemoViewModel::class.java)

        image_view_portrait.setOnLongClickListener { true }

        evalDPNumber.setOnClickListener {
            val anonNumber = anonDPNumber.getAnonymizedNumber()
            Toast.makeText(context, anonNumber.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}