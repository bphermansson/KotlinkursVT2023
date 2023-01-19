package com.paheco.fragmentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class StartFragment : Fragment() {
    var thecounter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.plusButton).setOnClickListener(){
            thecounter++
            if (thecounter == 5)
            {
                thecounter=0
            }
            view.findViewById<TextView>(R.id.startNumberTV).text=thecounter.toString()
        }
        view.findViewById<Button>(R.id.startReadMoreButton).setOnClickListener(){
            var readmorefrag = ReadMoreFragment()
            readmorefrag.currentNr = thecounter
            requireActivity().supportFragmentManager.beginTransaction().add(R.id.mainFragCon, readmorefrag).addToBackStack("mystack").commit()


        }
    }
    override fun onStart() {
        super.onStart()

    }
}