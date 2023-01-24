package com.paheco.navigationintro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController

class FirstPageFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("pia11debug", "Fragment1")

        view?.findViewById<Button>(R.id.goTosecondBtn)?.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_firstPageFragment_to_thirdPageFragment3)
        })

        /*
        view.findViewById<Button>(R.id.goTosecondBtn).setOnClickListener{
            requireActivity().supportFragmentManager.commit {
                var whereTo = secondPageFragment()
                add(R.id.fragmentContainerView, whereTo)
                addToBackStack(null)
            }
        }
        view.findViewById<Button>(R.id.goToThirdButton).setOnClickListener{
            requireActivity().supportFragmentManager.commit {
                var whereTo = thirdPageFragment()
                add(R.id.fragmentContainerView, whereTo)
                addToBackStack(null)
            }
        }
        */
    }
}