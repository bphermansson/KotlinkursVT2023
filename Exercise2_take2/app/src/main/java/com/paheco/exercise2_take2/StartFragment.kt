package com.paheco.exercise2_take2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StartFragment : Fragment() {
    lateinit var listadapter: PersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listadapter = PersonAdapter{
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, FragmentDetail()).addToBackStack("mystack").commit()
        }
        val personRecview = requireActivity().findViewById<RecyclerView>(R.id.personsRV)
        personRecview.adapter = listadapter
        personRecview.layoutManager = LinearLayoutManager(requireContext())
    }
}
