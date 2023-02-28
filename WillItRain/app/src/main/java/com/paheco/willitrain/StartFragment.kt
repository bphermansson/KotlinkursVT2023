package com.paheco.willitrain

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.paheco.willitrain.api.MetnoApiInterface
import com.paheco.willitrain.api.MetnoRetrofitClient
import com.paheco.willitrain.api.SmhiApiInterface
import com.paheco.willitrain.api.SmhiRetrofitClient
import com.paheco.willitrain.databinding.FragmentStartBinding
import com.paheco.willitrain.models.Smhimaindata
import java.time.LocalTime

class StartFragment : Fragment() {
    private  var _binding: FragmentStartBinding? = null
    private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSmhiData(view, binding)
        getMetnoData(view, binding)

        // Set update time
        val tInt = LocalTime.now().toString().substringBefore(".")
        binding.txtUpdatedAt.text = "${binding.txtUpdatedAt.text} ${tInt}"
    }

    fun getSmhiData(view: View, binding: FragmentStartBinding) {
        val smhiretrofit = SmhiRetrofitClient.getInstance()
        val smhiApiInterface = smhiretrofit.create(SmhiApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = smhiApiInterface.getAllweatherData()
                if (response.isSuccessful()) {
                    Log.i("willitrainlog", "Got Smhi data")
                    val json = Gson().toJson(response.body())
                    val receivedData = Gson().fromJson(json, Smhimaindata::class.java)

                    // SMHI temperature
                    var tempmain = receivedData.timeSeries[0].parameters[10]
                    //Log.i("willitrainlog", tempmain.values.toString())
                    var cleantsvalue = removeFixes(tempmain.values.toString())
                    binding.smhiTempTV.text = cleantsvalue.plus("C")

                    // SMHI Humidity
                    var hummain = receivedData.timeSeries[0].parameters[15]
                    var hsvalue = removeFixes(hummain.values.toString())
                    binding.smhiHumTV.text = hsvalue.plus("%")
                }
                else {
                    // TODO: What if something goes wrong?
                }
            } catch (Ex: Exception) {
                Log.i("willitrainlog", Ex.localizedMessage.orEmpty())
            }
        }
    }

    fun getMetnoData(view: View, binding: FragmentStartBinding) {
        val metnoretrofit = MetnoRetrofitClient.getInstance()
        val metnoApiInterface = metnoretrofit.create(MetnoApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = metnoApiInterface.getAllweatherData()
                if (response.isSuccessful()) {
                    Log.i("willitrainlog", "Got Met.no data")
                    val json = Gson().toJson(response.body())
                    val receivedData = Gson().fromJson(json, Metnomaindata::class.java)

                    // Met.no temperature
                    var tempmain = receivedData.properties!!.timeseries[0].time

                    Log.i("willitrainlog", tempmain.toString())
                    //var cleantsvalue = removeFixes(tempmain.values.toString())
                    //binding.metnoTempTV.text = cleantsvalue.plus("C")

                    // Met.no Humidity
                    //var hummain = receivedData.timeSeries[0].parameters[15]
                    //var hsvalue = removeFixes(hummain.values.toString())
                    //binding.metnoHumTV.text = hsvalue.plus("%")
                }
                else {
                    // TODO: What if something goes wrong?
                    Log.i("willitrainlog", "Get Met.no, error! ")
                }
            } catch (Ex: Exception) {
                Log.i("willitrainlog", Ex.localizedMessage.orEmpty())
            }
        }
    }

    class Tempvalues (
        var tvalue: Float,
        var tunit: String
        )
    override fun onDestroyView() {
        super.onDestroyView()
    }
    fun removeFixes(string : String) : String{
        return string.removePrefix("[").removeSuffix("]")
    }
}