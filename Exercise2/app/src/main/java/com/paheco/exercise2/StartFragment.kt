package com.paheco.exercise2

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StartFragment : Fragment(), CellClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.start_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val personRecview = view.findViewById<RecyclerView>(R.id.startRV)

        personRecview.adapter = RowAdapter(this)
        personRecview.layoutManager = LinearLayoutManager(requireActivity())
    }}
/*
    override fun onCellClickListener() {
        Log.i("Exercise2", "Click")
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.appFragmentContainer, DetailFragment())
                .addToBackStack("myStack")
                .setCustomAnimations(androidx.appcompat.R.anim.abc_fade_in, com.google.android.material.R.anim.abc_fade_out, androidx.appcompat.R.anim.abc_popup_enter, androidx.appcompat.R.anim.abc_popup_exit)
                .commit();
    }
}
interface CellClickListener {
    fun onCellClickListener()
}
 */
