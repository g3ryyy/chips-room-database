package com.example.roomdb.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdb.R
import com.example.roomdb.model.Chips
import com.example.roomdb.viewmodel.ChipsViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var  mChipsViewModel: ChipsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mChipsViewModel = ViewModelProvider(this).get(ChipsViewModel::class.java)

        view.add_btn.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val name = addName_et.text.toString()
        val taste = addTaste_et.text.toString()

        if (inputCheck(name, taste)){
            val chips = Chips(0, name, taste)
            mChipsViewModel.addChips(chips)
            Toast.makeText(requireContext(),"added", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"fill all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, taste: String):Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(taste))
    }
}