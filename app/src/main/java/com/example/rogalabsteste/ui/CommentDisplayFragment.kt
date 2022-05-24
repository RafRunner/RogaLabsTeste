package com.example.rogalabsteste.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rogalabsteste.R
import com.example.rogalabsteste.model.Post
import com.example.rogalabsteste.services.commentService

class CommentDisplayFragment : Fragment() {

    private val commentDisplayViewModel by viewModels<CommentDisplayViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CommentDisplayViewModel(commentService) as T
            }
        }
    }

    private val commentDisplayAdapter = CommentDisplayAdapter()

    companion object {
        const val PARENT_POST_KEY = "parent_post"

        fun newInstance(parentPost: Post): CommentDisplayFragment {
            val fragment = CommentDisplayFragment()
            val bundle = Bundle()
            bundle.putSerializable(PARENT_POST_KEY, parentPost)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.comment_display_fragment, container, false)
        val parentPost = requireArguments().getSerializable(PARENT_POST_KEY) as Post
        val activity = requireActivity()

        view.findViewById<TextView>(R.id.tvCommentViewTitle).apply {
            text = text.replace(Regex("\\\$postTitle"), parentPost.title)
        }

        view.findViewById<RecyclerView>(R.id.rvComments).apply {
            val linearLayoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = commentDisplayAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        commentDisplayViewModel.listComments.observe(activity) {
            commentDisplayAdapter.addToComments(it)
        }

        commentDisplayViewModel.fetchAllPostComments(parentPost)

        return view
    }
}