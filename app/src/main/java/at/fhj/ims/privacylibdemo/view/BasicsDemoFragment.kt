package at.fhj.ims.privacylibdemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import at.fhj.ims.privacylibdemo.R
import at.fhj.ims.privacylibdemo.databinding.BasicsDemoFragmentBinding
import at.fhj.ims.privacylibdemo.viewmodel.BasicsDemoViewModel
import kotlinx.android.synthetic.main.basics_demo_fragment.*
import java.util.*

class BasicsDemoFragment : Fragment() {

    companion object {
        fun newInstance() = BasicsDemoFragment()
    }

    private lateinit var viewModel: BasicsDemoViewModel
    private var _binding: BasicsDemoFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.basics_demo_fragment, container, false)
        binding.lifecycleOwner = activity
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(BasicsDemoViewModel::class.java)
        binding.setViewModel(viewModel)
        binding.editTextNumber.setText(viewModel.currentRandomValue.toString())

        binding.editTextNumber.setOnLongClickListener {
            viewModel.changeRandomValue()
            true
        }

        evalCheckbox.setOnClickListener {
            evalResult.text = checkBox.isProbableChecked().toString().toUpperCase(Locale.ROOT)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}