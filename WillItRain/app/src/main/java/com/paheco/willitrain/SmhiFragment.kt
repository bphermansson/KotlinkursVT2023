package com.paheco.willitrain

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.paheco.willitrain.databinding.FragmentSmhiBinding
import java.text.DecimalFormat


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

        var model: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val spinnerHours = binding.spinnerHoursAhead
        val adapter = ArrayAdapter.createFromResource(
            requireActivity()!!.getBaseContext(),
            R.array.hours,
            android.R.layout.simple_spinner_item
        )
        //adapter.setDropDownViewResource(android.R.layout.my_spinner)
        spinnerHours.setAdapter(adapter);

        spinnerHours?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing Selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                println(position)
                model.fetchSmhiWeather(position+1)
            }
        }

        // Create the observers which updates the UI.
        val tempObserver = Observer<String> { temp ->
            binding.smhiTempTV.text = temp
        }
        val humidityObserver = Observer<String> { hum ->
            binding.smhiHumTV.text = hum
        }
        val windspeedObserver = Observer<String> { ws ->
            binding.smhiWindspeedTV.text = ws
        }
        val winddirectionObserver = Observer<String> { wd ->
            binding.smhiWinddirTV.text = wd
        }
        val airpressureObserver = Observer<String> { airpressure ->
            binding.smhiPressureTV.text = airpressure
        }
        val weathertypeObserver = Observer<String> { weathertype ->
            // Check if weather type contains 'rain'
            val willitrain : Boolean = "rain" in weathertype.lowercase()
            val oldWillitrainText = getString(R.string.willitrainquestion)
            var ans = "No"
            if (willitrain == true) {
                ans = "Yes"
            }
            binding.smhiWillItRainAnswer.text = oldWillitrainText.plus(" ").plus(ans)
            binding.smhiWeathersymbol.text = weathertype
        }
        val timeObserver = Observer<String> { ltime ->
            val oldUpdatedText = getString(R.string.updated_at)
            val newText = oldUpdatedText.plus(" ").plus(ltime)
            binding.smhitxtUpdatedAtTV.text = newText
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.tempValue.observe(viewLifecycleOwner, tempObserver)
        model.humidityValue.observe(viewLifecycleOwner, humidityObserver)
        model.windSpeedValue.observe(viewLifecycleOwner, windspeedObserver)
        model.windDirectionValue.observe(viewLifecycleOwner,winddirectionObserver)
        model.airPressureValue.observe(viewLifecycleOwner, airpressureObserver)
        model.weatherTypeValue.observe(viewLifecycleOwner, weathertypeObserver)
        model.timeValue.observe(viewLifecycleOwner, timeObserver)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    Log.i(logTag, "Permission fine")
                    if (ActivityCompat.checkSelfPermission(
                            requireActivity()!!.getBaseContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            requireActivity()!!.getBaseContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return@registerForActivityResult
                    }
                    fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null).addOnSuccessListener { location ->

                        // We have permission to get the current location. let's use this.
                        Log.i(logTag, "Got current position")
                        Log.i(logTag, location.latitude.toString() + "-" + location.longitude.toString())

                        // In url call, we should have five decimals, but the Android system gives us seven.
                        val long = location.longitude
                        val lat = location.latitude
                        val df = DecimalFormat("#.#####")
                        val shortLong = df.format(long).replace(',', '.')   // format creates a comma, we need a dot
                        val shortLat = df.format(lat).replace(',', '.')
                        val latLong = "Long: " + shortLong.toString() + " - Lat: " + shortLat.toString()
                        binding.smhiLocationTV.text = latLong

                        MainActivity.smhiLon = shortLong.toString()
                        MainActivity.smhiLat = shortLat.toString()
                    }
                }
                permissions.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // If we have Fine permission we must also have course, so no need to do anything here.
                } else -> {
                    Log.i(logTag, "Permission denied")
                    Snackbar.make(view, "No permission to get location, using default value ", Snackbar.LENGTH_LONG).show();
                    // If the user has denied access the app won't ask again, no mather what.
                    // https://developer.android.com/training/permissions/requesting#handle-denial
                }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION)
        )

        var hoursAhead : Int = binding.spinnerHoursAhead.selectedItem.toString().toInt()
        model.fetchSmhiWeather(hoursAhead)
    /*
            // TODO: Implement weather from met.no
            Log.i(MainActivity.logTag, "Met.no button clicked")
            // Load Met.no fragment
        }
    */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
