package com.paheco.willitrain

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
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
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    Log.i(logTag, "Permission fine")
                    fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null).addOnSuccessListener { location ->
                        // We have permission to get the current location. let's use this.
                        Log.i(logTag, "Got current position")
                        Log.i(logTag, location.latitude.toString() + "-" + location.longitude.toString())
                        MainActivity.smhiLon = location.longitude.toString()
                        MainActivity.smhiLat = location.latitude.toString()
                        var latLong = "Long: " + location.longitude.toString() + " - Lat: " + location.latitude.toString()
                        binding.smhiLocationTV.text = latLong
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

        fetchSmhiWeather()
/*
        binding.smhiLocationTV.setOnClickListener(){
            // TODO: Implement weather from met.no
            Log.i(MainActivity.logTag, "Met.no button clicked")
            // Load Met.no fragment
        }
*/
        binding.smhirefreshBTN.setOnClickListener(){
            binding.smhirefreshBTN.setBackgroundColor(Color.RED)
            fetchSmhiWeather()
            binding.smhirefreshBTN.setBackgroundColor(Color.BLUE)
        }
        binding.smhiLocationTV.setOnClickListener(){
            // get perm. No this ain't possible.
        }
    }

    fun fetchSmhiWeather() {
        // A view model instance
        var viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        SmhiAPI().loadSmhidata() { smhidata ->
            viewModel.getSmhiTemp(smhidata) { smhitemp -> Unit
                binding.smhiTempTV.text = smhitemp.toString()
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
            binding.smhitxtUpdatedAtTV.text = getString(R.string.updated_at).plus(" ").plus(ltime)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
