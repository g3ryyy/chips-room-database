package com.example.roomdb.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdb.R
import com.example.roomdb.viewmodel.ChipsViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

   private lateinit var mChipsViewModel: ChipsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //RecycleView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ChipsViewModel
        mChipsViewModel = ViewModelProvider(this).get(ChipsViewModel::class.java)
        mChipsViewModel.readAllData.observe(viewLifecycleOwner, Observer { chips ->
            adapter.setData(chips)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllChips()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllChips() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mChipsViewModel.deleteAllChips()
            Toast.makeText(requireContext(),"Successfully deleted all chipses", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete all chipses?")
        builder.setMessage("Are you really want to delete all chipses?")
        builder.create().show()
    }

}