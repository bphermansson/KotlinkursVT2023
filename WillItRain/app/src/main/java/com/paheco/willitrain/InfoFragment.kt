package com.paheco.willitrain
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.paheco.willitrain.databinding.FragmentInfoBinding
import com.paheco.willitrain.databinding.FragmentSmhiBinding
import kotlinx.serialization.json.JsonNull.content


class InfoFragment : Fragment() {
    private  var _binding: FragmentInfoBinding? = null
    private  val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val file_name = "LICENSE.md"

        val bufferReader = activity?.assets?.open(file_name)?.bufferedReader()
        val data = bufferReader.use {
            it?.readText()

        }

        //Log.d(MainActivity.logTag,  data.toString())
        //MainActivity().findViewById<TextView>(R.id.licenseTV).text = data
        binding.licenseTV.text = data
        binding.licenseTV.movementMethod = ScrollingMovementMethod()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
}