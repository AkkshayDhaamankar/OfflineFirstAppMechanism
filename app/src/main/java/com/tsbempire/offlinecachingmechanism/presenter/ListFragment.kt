package com.tsbempire.offlinecachingmechanism.presenter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsbempire.offlinecachingmechanism.databinding.FragmentListBinding
import com.tsbempire.offlinecachingmechanism.framework.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), ListAction {
    private lateinit var binding: FragmentListBinding
    private val commentListAdapter = CommentListAdapter(arrayListOf(), this)
    private val viewModel: ListViewModel by viewModels()
    private val listener = MyBroadcastReceiver()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvComments.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commentListAdapter
        }
        binding.fbAddComment.setOnClickListener { goToCommentDetails() }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.comments.observe(viewLifecycleOwner) {
            // just playing around with scoped functions
            // normal approach -> binding.pbLoader.visibility = View.GONE
            binding.pbLoader.apply { visibility = View.GONE }
            binding.rvComments.visibility = View.VISIBLE
            commentListAdapter.updateComments(it.sortedByDescending { it.updateTime })
        }
        // uncomment when you dont have to receive updates via local broadcast
//        viewModel.workInfo.observe(viewLifecycleOwner, Observer { workInfoList ->
//            //  check the workInfoList is null ot empty and
//            //  if it empty then simply return
//            if (workInfoList.isNullOrEmpty()) {
//                Log.d("ListFragment", "WorkManager Returned")
//                return@Observer
//            }
//            Log.d("ListFragment", "WorkManager Executed")
//            viewModel.getAllComments()
//        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllComments()
        activity?.let {
            LocalBroadcastManager.getInstance(it.baseContext)
                .registerReceiver(listener, IntentFilter("WORK_MANAGER"))
        }

    }

    override fun onPause() {
        super.onPause()
        activity?.let {
            LocalBroadcastManager.getInstance(it.baseContext).unregisterReceiver(listener)
        }

    }

    private fun goToCommentDetails(id: Long = 0L) {
        val action: NavDirections = ListFragmentDirections.actionGoToComment(id)
        Navigation.findNavController(binding.rvComments).navigate(action)
    }

    override fun onClick(id: Long) {
        goToCommentDetails(id)
    }


    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d("ListFragment", "BroadCast Triggered")
            when (intent.action) {
                "WORK_MANAGER" -> {
                    Log.d("ListFragment", "BroadCast Received")
                    viewModel.getAllComments()
                }
                else -> Log.d("ListFragment", "Action Not Found")
            }

        }
    }
}