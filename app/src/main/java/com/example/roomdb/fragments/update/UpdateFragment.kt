package com.example.roomdb.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdb.R
import com.example.roomdb.model.Chips
import com.example.roomdb.viewmodel.ChipsViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mChipsViewModel: ChipsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mChipsViewModel = ViewModelProvider(this).get(ChipsViewModel::class.java)

        view.updateName_et.setText(args.currentChips.name)
        view.updateTaste_et.setText(args.currentChips.taste)

        view.update_btn.setOnClickListener {
            updateItem()
        }

        //Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val name = updateName_et.text.toString()
        val taste = updateTaste_et.text.toString()

        if (inputCheck(name, taste)){
            //Create Chips object
            val updatetChips = Chips(args.currentChips.id, name, taste)
            //Update current chips
            mChipsViewModel.updateChips(updatetChips)
            Toast.makeText(requireContext(),"Updated successfully", Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill all fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(name: String, taste: String):Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(taste))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete){
            deleteChips()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteChips() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mChipsViewModel.deleteChips(args.currentChips)
            Toast.makeText(requireContext(),"Successfully deleted: ${args.currentChips.name} with ${args.currentChips.taste}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${args.currentChips.name} with ${args.currentChips.taste}?")
        builder.setMessage("Are you really want to delete ${args.currentChips.name} with ${args.currentChips.taste}?")
        builder.create().show()
    }


}