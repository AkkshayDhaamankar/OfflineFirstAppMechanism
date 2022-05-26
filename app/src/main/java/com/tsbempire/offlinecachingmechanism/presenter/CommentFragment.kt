package com.tsbempire.offlinecachingmechanism.presenter

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.tsbempire.core.data.Comment
import com.tsbempire.offlinecachingmechanism.R
import com.tsbempire.offlinecachingmechanism.databinding.FragmentCommentBinding
import com.tsbempire.offlinecachingmechanism.framework.CommentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentFragment : Fragment() {
    private lateinit var binding: FragmentCommentBinding
    private val viewModel: CommentViewModel by viewModels()
    private var commentId = 0L
    private var currentComment = Comment("", 0L, 0L, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            commentId = CommentFragmentArgs.fromBundle(it).commentId
        }
        if (commentId != 0L) {
            viewModel.getComment(commentId)
        }
        binding.fbSaveComment.setOnClickListener {
            if (binding.etComment.text.toString() != "") {
                val time: Long = System.currentTimeMillis()
                currentComment.comment = binding.etComment.text.toString()
                currentComment.updateTime = time;
                if (currentComment.id == 0L) {
                    currentComment.creationTime = time
                    currentComment.id = time
                }
                currentComment.isSync = 0
                viewModel.saveComment(currentComment)
            } else
                Navigation.findNavController(it).popBackStack()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(binding.fbSaveComment).popBackStack()
            } else {
                Toast.makeText(
                    context,
                    "Something went wrong, please try again later",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        viewModel.currentComment.observe(viewLifecycleOwner, Observer {
            it?.let {
                currentComment = it
                binding.etComment.setText(it.comment, TextView.BufferType.EDITABLE)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.comment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteComment -> {
                if (context != null && commentId != 0L) {
                    AlertDialog.Builder(requireContext()).apply {
                        setTitle("Delete comment")
                            .setMessage("Are you sure you want to delete this comment")
                            .setPositiveButton("Yes") { dialogInterface, i ->
                                viewModel.deleteComment(
                                    currentComment
                                )
                            }
                            .setNegativeButton("Cancel") { dialogInterface, i ->
                            }
                            .create()
                            .show()
                    }
                }
            }
        }
        return true
    }
}