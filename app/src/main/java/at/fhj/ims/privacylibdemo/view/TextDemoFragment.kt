package at.fhj.ims.privacylibdemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import at.fhj.ims.privacylibdemo.R
import at.fhj.ims.privacylibdemo.viewmodel.TextDemoViewModel
import kotlinx.android.synthetic.main.text_demo_fragment.*

class TextDemoFragment : Fragment() {

    companion object {
        fun newInstance() = TextDemoFragment()
    }

    private lateinit var viewModel: TextDemoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.text_demo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TextDemoViewModel::class.java)

        button2.setOnClickListener {
            viewModel.setAnonymizedText(original_text_input.text.toString())
        }

        viewModel.anonymizedText.observe(
            viewLifecycleOwner,
            { stemmedResult -> stemmed_result.text = stemmedResult })
    }

}