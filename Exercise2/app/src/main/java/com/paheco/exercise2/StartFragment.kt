package com.paheco.exercise2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StartFragment(private val items: List<ContentItem>) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.start_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val personRecview = view.findViewById<RecyclerView>(R.id.startRV)

        //(activity as MainActivity?)!!.fragmentMethod()
        //personRecview.adapter = MyRowAdapter()

        personRecview.adapter = MyRowAdapter(items) { item ->
            //toast(item)
            Log.d("ExerciseLog", "Info")

        };

        /*
        recycler.adapter = ContentAdapter(items) { item ->
            toast(item.title)
        };
        */
        personRecview.layoutManager = LinearLayoutManager(requireActivity())

        //radapter.myFruits = MyRowAdapter().myFruits
        //radapter.notifyDataSetChanged()

            //  (activity as MainActivity?)!!.fragmentMethod()

    }
}



