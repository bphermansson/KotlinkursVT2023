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
import com.paheco.willitrain.api.SmhiApiInterface
import com.paheco.willitrain.api.SmhiRetrofitClient
import com.paheco.willitrain.models.Smhimaindata

class startFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSmhiData(view)
    }

    fun getSmhiData(view: View) {
        val smhiretrofit = SmhiRetrofitClient.getInstance()
        val apiInterface = smhiretrofit.create(SmhiApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getAllweatherData()
                val tempText = view.findViewById<TextView>(R.id.tempTV)
                if (response.isSuccessful()) {
                    Log.i("willitrain", "Got data")

                    val json = Gson().toJson(response.body())
                    val received_data = Gson().fromJson(json, Smhimaindata::class.java)
                    var tempmain = received_data.timeSeries[0].parameters[10]
                    //var tvalue = tempmain.values[0]
                    val tv = Tempvalues(tvalue = tempmain.unit.toFloat())



                    var tunit = tempmain.unit
                    tempText.text = tv.tvalue.toString() + tunit.uppercase()
                    Log.i("willitrain", tv.tvalue.toString() + tunit)

                }
            } catch (Ex: Exception) {
                Log.i("willitrain", Ex.localizedMessage.orEmpty())
            }
        }
    }
    class Tempvalues (
        var tvalue: Float,
        )
}