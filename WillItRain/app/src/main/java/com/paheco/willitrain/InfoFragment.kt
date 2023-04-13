package com.paheco.willitrain
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.paheco.willitrain.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private  var _binding: FragmentInfoBinding? = null
    private  val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val file_name = "LICENSE.md"

        val bufferReader = activity?.assets?.open(file_name)?.bufferedReader()
        val data = bufferReader.use {
            it?.readText()
        }
        binding.licenseTV.text = data
        binding.licenseTV.movementMethod = ScrollingMovementMethod()

        binding.licenseTV.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView2,
                SmhiFragment()).addToBackStack(null).commit()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
}