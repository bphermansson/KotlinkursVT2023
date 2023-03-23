package com.paheco.willitrain

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.paheco.willitrain.databinding.FragmentSmhiBinding


class SmhiFragment : Fragment() {
    private  var _binding: FragmentSmhiBinding? = null
    private  val binding get() = _binding!!

    var logTag = MainActivity.logTag
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSmhiBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if(isGranted) {
                Log.i(MainActivity.logTag, "granted")
            }
            else{
                Log.i(MainActivity.logTag, "Denied")
            }
        }
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

 */

        fetchSmhiWeather()

        binding.smhirefreshBTN.setOnClickListener(){
            binding.smhirefreshBTN.setBackgroundColor(Color.RED)
            fetchSmhiWeather()
            binding.smhirefreshBTN.setBackgroundColor(Color.BLUE)
        }
    }

    fun fetchSmhiWeather() {
        // A view model instance
        var viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        SmhiAPI().loadSmhidata() { smhidata ->
            viewModel.getSmhiTemp(smhidata) { smhitemp -> Unit
                binding.smhiTempTV.text = smhitemp
            }
            viewModel.getSmhiHum(smhidata) { smhihum ->
                binding.smhiHumTV.text = smhihum
            }
            viewModel.getSmhiPressure(smhidata) { smhipres ->
                binding.smhiPressureTV.text = smhipres
            }
            viewModel.getSmhiWindspeed(smhidata) { smhiws ->
                binding.smhiWindspeedTV.text = smhiws
            }
            viewModel.getSmhiWinddir(smhidata) { smhiwd ->
                binding.smhiWinddirTV.text = smhiwd
            }
            viewModel.getSmhiWeathersymbol(smhidata) { smhiwsym ->
                binding.smhiWeathersymbol.text = smhiwsym
            }
        }
        viewModel.getLocalTime { ltime ->
            binding.smhitxtUpdatedAt.text = getString(R.string.updated_at).plus(" ").plus(ltime)
        }
    }


    fun getPosition() {


        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    //findme()
                    Log.i(MainActivity.logTag, "FINE")
                }
                permissions.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    Log.i(MainActivity.logTag, "Coarse")
                } else -> {
                Log.i(MainActivity.logTag, "Permission error")
            }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION)
        )

        ActivityCompat.requestPermissions(
            Activity(),
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1
        )

        //val gotPerm = checkSelfPermission(ACCESS_FINE_LOCATION)
        //Log.i(logTag, "Got perm = $gotPerm")    // 0 = got permission. -1 = No permissions, ask.

        //if (gotPerm == -1) {
        //    Snackbar.make(findViewById<View>(R.id.fragmentContainerView2), "Position permission denied", Snackbar.LENGTH_SHORT).show()


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

 */

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
