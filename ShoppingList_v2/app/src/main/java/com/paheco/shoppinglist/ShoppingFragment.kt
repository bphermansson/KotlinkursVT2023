package se.magictechnology.pia11shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.paheco.shoppinglist.R
import com.paheco.shoppinglist.ShoplistViewModel
import com.paheco.shoppinglist.ShoppingAdapter
import com.paheco.shoppinglist.ShoppingItem
import com.paheco.shoppinglist.databinding.FragmentShoppingBinding

class ShoppingFragment : Fragment() {

    var _binding : FragmentShoppingBinding? = null
    val binding get() = _binding!!

    var shopadapter = ShoppingAdapter()

    val model by viewModels<ShoplistViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_shopping, container, false)

        shopadapter.frag = this

        _binding = FragmentShoppingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.shoppingRV.adapter = shopadapter
        binding.shoppingRV.layoutManager = LinearLayoutManager(requireContext())

        val shopObserver = Observer<List<ShoppingItem>>{
            shopadapter.notifyDataSetChanged()
        }
        model.currentName.observe(viewLifecycleOwner, shopObserver)

        val errorObserver = Observer<String>{
            if(it==""){
                binding.errorMessTV.visibility = View.GONE
            }
            binding.errorMessTV.text = it
            binding.errorMessTV.visibility = View.VISIBLE
        }
        model.currentName.observe(viewLifecycleOwner, errorObserver)


        /*
        view.findViewById<Button>(R.id.logoutButton).setOnClickListener {
            Firebase.auth.signOut()
        }
        */

        binding.logoutButton.setOnClickListener {
            Firebase.auth.signOut()
        }

        binding.itemAddBTN.setOnClickListener {
            val addshopname = binding.itemET.text.toString()
            val addshopamount = binding.shoppingAmountET.text.toString()

            model.addShopping(addshopname, addshopamount)
            binding.itemET.setText("")
            binding.shoppingAmountET.setText("")
        }
        model.loadShopping()
    }


}

