package at.fhj.ims.privacylibdemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import at.fhj.ims.privacylibdemo.R
import at.fhj.ims.privacylibdemo.viewmodel.RadioGroupDemoViewModel
import kotlinx.android.synthetic.main.radio_group_demo_fragment.*

class RadioGroupDemoFragment : Fragment() {

    companion object {
        fun newInstance() = RadioGroupDemoFragment()
    }

    private lateinit var viewModel: RadioGroupDemoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.radio_group_demo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RadioGroupDemoViewModel::class.java)

        evalSelection.setOnClickListener {
            activity?.let {
                val anonIndex = colorGroup.getSelectedAnonymized(activity!!)
                viewModel.setSelection(anonIndex)
            }
        }

        viewModel.currentSelection.observe(
            viewLifecycleOwner,
            Observer { currentSelection ->
                tv_current_selection.text = getString(R.string.your_current_selection, currentSelection)
            })
    }

}