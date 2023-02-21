package com.paheco.willitrain

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.paheco.willitrain.api.SmhiApiInterface
import com.paheco.willitrain.api.SmhiRetrofitClient
import com.paheco.willitrain.databinding.FragmentStartBinding
import com.paheco.willitrain.models.Smhimaindata
import java.time.LocalTime

class startFragment : Fragment() {
    private  var _binding: FragmentStartBinding? = null
    private  val binding get() = _binding!!

    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //_binding = FragmentStartBinding.inflate(inflater, container, false)
        //return binding.root
    }

     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStartBinding.bind(view)
binding.btnSample.text="HI"
        binding.tvSample.text="123"

        // GUI elements. TODO: could use a binding instead...
        var smhiTempTV = requireActivity().findViewById<TextView>(R.id.smhiTempTV)
        var smhiHumTV = requireActivity().findViewById<TextView>(R.id.smhiHumTV)
        var metnoTempTV = requireActivity().findViewById<TextView>(R.id.metnoTempTV)
        var metnoHumTV = requireActivity().findViewById<TextView>(R.id.metnoHumTV)
        var updateTV = requireActivity().findViewById<TextView>(R.id.txtUpdatedAt)
        var updateTVtext = requireActivity().findViewById<TextView>(R.id.txtUpdatedAt).text.toString()

        getSmhiData(view, binding)



        // Set update time
        val tInt = LocalTime.now().toString().substringBefore(".")
        updateTV.text= updateTVtext + " " + tInt
    }

    fun getSmhiData(view: View, binding: FragmentStartBinding) {

        binding.tvSample.text="WAIT"
        binding.btnSample.text="HI"

        val smhiretrofit = SmhiRetrofitClient.getInstance()
        val smhiApiInterface = smhiretrofit.create(SmhiApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = smhiApiInterface.getAllweatherData()
                val tempText = view.findViewById<TextView>(R.id.smhiTV)
                if (response.isSuccessful()) {
                    Log.i("willitrain", "Got data")
                    val json = Gson().toJson(response.body())
                    val receivedData = Gson().fromJson(json, Smhimaindata::class.java)
                    var tempmain = receivedData.timeSeries[0].parameters[10]
                    Log.i("willitrainlog", tempmain.values.toString())
                    var tsvalue = tempmain.values.toString()    // This is the [temp value]
                    var cleantsvalue = tsvalue.removePrefix("[")
                    cleantsvalue = cleantsvalue.removeSuffix("]")     // This works!

                    Log.i("willitrainlog", cleantsvalue)
                    //smhiTempTV.text = cleantsvalue
                    //smhiHumTV.text =

                    binding.tvSample.text="YEAH"


                    //val tv = Tempvalues(tvalue = tempmain.unit.toFloat())

                    //var tunit = tempmain.unit
                    //tempText.text = tv.tvalue.toString() + tunit.uppercase()
                    //Log.i("willitrain", tv.tvalue.toString() + tunit)

                }
                else {
                    // TODO: What if something goes wrong?
                }
            } catch (Ex: Exception) {
                Log.i("willitrain", Ex.localizedMessage.orEmpty())
            }
        }
    }
    class Tempvalues (
        var tvalue: Float,
        var tunit: String
        )
}