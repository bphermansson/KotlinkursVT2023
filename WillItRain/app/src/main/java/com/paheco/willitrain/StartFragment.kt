package com.paheco.willitrain

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.paheco.willitrain.databinding.FragmentStartBinding
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

        // TODO: Use a viewmodel for the logic

        // A view model instance
        var viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        Log.i ("willitrainlog", viewModel.number.toString())

        SmhiAPI().loadSmhidata() { receivedData -> Unit
            //Log.i("willitrainlog", receivedData.approvedTime.toString())
            var smhitemp = removeFixes(receivedData.timeSeries[0].parameters[10].values.toString())
            Log.i("willitrainlog", "Temp: $smhitemp" )
            binding.smhiTempTV.text = smhitemp.plus("C")

            var hummain = receivedData.timeSeries[0].parameters[15]
            var hsvalue = removeFixes(hummain.values.toString())
            binding.smhiHumTV.text = hsvalue.plus("%")

            var smhiPressure = receivedData.timeSeries[0].parameters[11]
            var smhiWindspeed = receivedData.timeSeries[0].parameters[14]
            var smhiWinddir = receivedData.timeSeries[0].parameters[13]
            var smhiWeathersymbol = receivedData.timeSeries[0].parameters[18]

            binding.smhiPressure.text = smhiPressure.values[0].toString().plus("hPa")
            binding.smhiWindspeed.text = smhiWindspeed.values[0].toString().plus("m/S")

            val wType = arrayOf("Clear sky", "Nearly clear sky", "Variable cloudiness", "Halfclear sky", "Cloudy sky", "Overcast", "Fog", "Light rain showers", "Moderate rain showers", "Heavy rain showers", "Thunderstorm", "Light sleet showers", "Moderate sleet showers", "Heavy sleet showers", "Light snow showers", "Moderate snow showers", "Heavy snow showers", "Light rain", "Moderate rain", "Heavy rain", "Thunder", "Light sleet", "Moderate sleet", "Heavy sleet", "Light snowfall", "Moderate snowfall", "Heavy snowfall")
            var wSymbol = wType[smhiWeathersymbol.values[0].toInt()]
            Log.i("willitrainlog", "Weather type: $wSymbol" )
            binding.smhiWeathersymbol.text = wSymbol

            // Convert wind direction number to a direction
            val direction = listOf("N","NNE","NE","ENE","E","ESE","SE","SSE","S","SSW","SW","WSW","W","WNW","NW","NNW","N")
            val dirAsText = direction[((smhiWinddir.values[0]+11.25)/22.5).toInt()]
            Log.i("willitrainlog", "Dir: $dirAsText" )
            binding.smhiWinddir.text = dirAsText


        }

        //getWeatherdata(view)

        // Set update time
        val tInt = LocalTime.now().toString().substringBefore(".")
        binding.txtUpdatedAt.text = "${binding.txtUpdatedAt.text} ${tInt}"

        binding.refreshBTN.setOnClickListener(){

        }

    }

    fun getWeatherdata(view: View){
        //getSmhiData(view, binding)
        //getMetnoData(view, binding)

    }
/*
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

                    var smhiPressure = receivedData.timeSeries[0].parameters[11]
                    var smhiWindspeed = receivedData.timeSeries[0].parameters[14]
                    var smhiWinddir = receivedData.timeSeries[0].parameters[13]
                    var smhiWeathersymbol = receivedData.timeSeries[0].parameters[18]

                    binding.smhiPressure.text = smhiPressure.values[0].toString().plus("hPa")
                    binding.smhiWindspeed.text = smhiWindspeed.values[0].toString().plus("m/S")
                    binding.smhiWinddir.text = smhiWinddir.values[0].toString()
                    binding.smhiWeathersymbol.text = smhiWeathersymbol.values[0].toString() // TODO: Conversion table number -> weather type

                }
                else {
                    // TODO: What if something goes wrong?
                }
            } catch (Ex: Exception) {
                Log.i("willitrainlog", Ex.localizedMessage.orEmpty())
            }
        }
    }
 */
/*
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

                    // Met.no temperature and more
                    var metnoTime = receivedData.metnoProperties!!.timeseries[0].time
                    var metnoTemp = receivedData.metnoProperties!!.timeseries[0].metnoData?.metnoInstant?.metnoDetails?.airTemperature
                    var metnoHum = receivedData.metnoProperties!!.timeseries[0].metnoData?.metnoInstant?.metnoDetails?.relativeHumidity
                    var metnoWindspeed = receivedData.metnoProperties!!.timeseries[0].metnoData?.metnoInstant?.metnoDetails?.windSpeed
                    var metnoWindDir = receivedData.metnoProperties!!.timeseries[0].metnoData?.metnoInstant?.metnoDetails?.windFromDirection
                    var metnoSymbol = receivedData.metnoProperties!!.timeseries[0].metnoData?.metnoNext1Hours?.metnoSummary?.symbolCode

                    /*
                    Log.i("willitrainlog", metnoTime.toString())
                    Log.i("willitrainlog", metnoTemp.toString() + "-" + metnoHum.toString())
                    Log.i("willitrainlog", metnoWindspeed.toString() + "-" + metnoWindDir.toString())
                    Log.i("willitrainlog", metnoSymbol.toString())
                     */

                    binding.metnoTempTV.text = metnoTemp.toString().plus("C")
                    binding.metnoHumTV.text = metnoHum.toString().plus("%")
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

 */
    override fun onDestroyView() {
        super.onDestroyView()
    }
    fun removeFixes(string : String) : String{
        return string.removePrefix("[").removeSuffix("]")
    }
}