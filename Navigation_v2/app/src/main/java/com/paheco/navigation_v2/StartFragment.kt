package com.paheco.navigation_v2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

class StartFragment : Fragment() {
   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.goOtherButton).setOnClickListener{
               val startnameET = view.findViewById<EditText>(R.id.startNameET)
                val startnameText = startnameET.text.toString()

                if (startnameText == "") {
                    /*Toast.makeText(
                        view.context,
                        "No Data ",
                        Toast.LENGTH_LONG
                    ).show()
                     */
                    Snackbar.make(view, "Du måste ange ett namn!", Snackbar.LENGTH_LONG).setAction("Bartil"){
                        view.findViewById<EditText>(R.id.startNameET).setText("Bartil")
                    }.show()
                }
                else {
                    val action = StartFragmentDirections.actionStartFragmentToOtherFragment("Patrik")
                    findNavController().navigate(action)
                }
        }
        view.findViewById<Button>(R.id.goFancyButton).setOnClickListener() {
            val action = StartFragmentDirections.actionStartFragmentToFancyFragment()
            findNavController().navigate(action)
        }

    }
}