package com.example.rogalabsteste.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rogalabsteste.R
import com.example.rogalabsteste.model.Post

class CommentDisplayFragment : Fragment() {
    private val TAG = "CommentDisplayFragment"

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

    private lateinit var viewModel: CommentDisplayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comment_display_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        viewModel = ViewModelProvider(this).get(CommentDisplayViewModel::class.java)

        val parentPost = requireArguments().getSerializable(PARENT_POST_KEY) as Post
        Log.d(TAG, "Parent post id: " + parentPost.id)
    }

}