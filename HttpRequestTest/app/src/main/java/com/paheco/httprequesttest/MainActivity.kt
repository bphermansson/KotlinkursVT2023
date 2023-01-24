// https://developer.android.com/codelabs/basic-android-kotlin-training-getting-data-internet

package com.paheco.httprequesttest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.*
import com.network.MarsApi
import kotlinx.coroutines.launch

class StartFragment : Fragment(R.layout.fragment_start)
class DataFragment : Fragment(R.layout.fragment_data)

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.countText.text = viewModel.getCurrentCount().toString()
        binding.button.setOnClickListener {
            binding.countText.text = viewModel.getUpdatedCount().toString()


        /*
        val viewModel: OverviewViewModel by viewModels()

        lifecycleScope.launch {
            Log.d("Mars Photos","lifecyclescope")

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                /*viewModel.uiState.collect {
                    // Update UI elements
                }

                 */

            }
        }
        */

        findViewById<Button>(R.id.btnNext).setOnClickListener(){
             supportFragmentManager.commit {
              setReorderingAllowed(true)
                 addToBackStack( "tag" )
              add<DataFragment>(R.id.fragmentContainerView)
             }
        }
    }
}


/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        Log.d("Mars Photos","init")
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                Log.d("Mars Photos","Get photos")
                val listResult = MarsApi.retrofitService.getPhotos()
                _status.value = "Success: ${listResult.size} Mars photos retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}

