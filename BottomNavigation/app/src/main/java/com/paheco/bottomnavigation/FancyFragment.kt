package com.paheco.bottomnavigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar
import androidx.navigation.fragment.findNavController

class FancyFragment : Fragment() {
   var theCounter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fancy, container, false)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fancy_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.banan_item) {
            requireView().findViewById<TextView>(R.id.fancyNumberTV).text = "Banan"
            return true
        }
        if (item.itemId == R.id.apelsin_item) {
            requireView().findViewById<TextView>(R.id.fancyNumberTV).text = "Apelsin"
            return true
        }
        return super.onOptionsItemSelected(item)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.fancyToolbar).inflateMenu(R.menu.fancy_menu)

        view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.fancyToolbar).setOnMenuItemClickListener(){
            when (it.itemId) {
                R.id.banan_item -> {
                    // Navigate to settings screen
                    true
                }
                R.id.apelsin_item -> {
                    // Save profile changes
                    true
                }
                else -> false
            }

        }
        view.findViewById<TextView>(R.id.fancyNumberTV).text = theCounter.toString()

        view.findViewById<Button>(R.id.fancyBTN).setOnClickListener(){
            theCounter++
            view.findViewById<TextView>(R.id.fancyNumberTV).text = theCounter.toString()
        }
        view.findViewById<Button>(R.id.fancyDetailButton).setOnClickListener() {
            findNavController().navigate((R.id.action_navigation_fancy_to_fancyDetailFragment))

        }
    }
}