package com.pourkazemi.mahdi.maktab_hw_18_1.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.pourkazemi.mahdi.maktab_hw_18_1.R
import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.SendPerson
import com.pourkazemi.mahdi.maktab_hw_18_1.databinding.FragmentOnlineListBinding
import com.pourkazemi.mahdi.maktab_hw_18_1.ui.adapter.ItemListAdapter
import com.pourkazemi.mahdi.maktab_hw_18_1.util.ResultWrapper
import com.pourkazemi.mahdi.maktab_hw_18_1.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnlineListFragment : Fragment(R.layout.fragment_online_list) {
    private val binding: FragmentOnlineListBinding by viewBinding(
        FragmentOnlineListBinding::bind
    )
    private val onlineViewModel: OnlineViewModel by activityViewModels()
    private lateinit var adapter: ItemListAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ItemListAdapter()
        binding.onlineList.adapter = adapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                onlineViewModel.personListOnline.collect {
                    when (it) {
                        is ResultWrapper.Loading -> Log.d(
                            "mahdiTest",
                            "Loading"
                        )//binding.stateView.onLoading()
                        is ResultWrapper.Success -> {
                            //val emptyList= listOf<PictureItem>() //#for check empty list worked
                            adapter.submitList(it.value)
                            /*if (it.value.isNotEmpty()) {
                                binding.stateView.onSuccess()
                            } else {
                                binding.stateView.onEmpty()
                            }*/
                        }
                        is ResultWrapper.Error -> {
                            /*binding.stateView.onFail()
                            binding.stateView.clickRequest {
                                viewModel.getPictureList()
                            }*/
                            Toast.makeText(
                                requireActivity(),
                                it.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        binding.onlineButton.setOnClickListener {
            createUserDialog()
        }

        val itemTouchHelper = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                if (direction == ItemTouchHelper.LEFT) {
//                    showDetail(position)
                } else {
                   /* recyclerView.adapter?.notifyItemChanged(position)
                    addUserOnDataBase(position)*/
                }
            }
        }

        /*val itemTouch = ItemTouchHelper(itemTouchHelper)
        itemTouch.attachToRecyclerView(recyclerView)*/
    }

    private fun createUserDialog() {
        val dialog = AppDialog { firstName, lastName, nationalCode, list, image ->
            val sendPerson = SendPerson(firstName, lastName, nationalCode, list)
            image?.let { onlineViewModel.createPersonOnServer(sendPerson, image) }

            Log.d("mahdiTest", "person")
        }
        dialog.show(childFragmentManager, "action button dialog")
    }


    override fun onResume() {
        super.onResume()
        onlineViewModel.getPersonList()
    }
}