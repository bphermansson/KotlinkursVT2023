package com.paheco.diverse

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("pia11debug", "START")
        Log.i("pia11debug", LoginHelper.fancytext)

        view.findViewById<TextView>(R.id.startTV).text = LoginHelper.username.value

        view.findViewById<Button>(R.id.logoutBTN).setOnClickListener {
            LoginHelper.logout(requireContext())
        }

        view.findViewById<Button>(R.id.readmoreBTN).setOnClickListener {
            view.findNavController().navigate(R.id.action_startFragment_to_readmoreFragment)
        }

        view.findViewById<Button>(R.id.addBTN).setOnClickListener{
            val number1 = view.findViewById<EditText>(R.id.startnumberOneET).text.toString()
            val number2 = view.findViewById<EditText>(R.id.startnumberTwoET).text.toString()

            var calchelp = CalcHelper()
            calchelp.loadNumbers()

            /*
            val n1 = calchelp.textToNumber(number1)
            val n2 = calchelp.textToNumber(number2)
            val resultNumber = calchelp.numberToText(n1 + n2)
             */

            view.findViewById<TextView>(R.id.startResult).text = calchelp.calcStrings(number1, number2)
        }
    }


}

