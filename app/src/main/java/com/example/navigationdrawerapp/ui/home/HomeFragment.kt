package com.example.navigationdrawerapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationdrawerapp.CreatePostActivity
import com.example.navigationdrawerapp.R
import com.example.navigationdrawerapp.adapters.PostAdapter
import com.example.navigationdrawerapp.adapters.PostListeners
import com.example.navigationdrawerapp.databinding.FragmentHomeBinding
import com.example.navigationdrawerapp.model.Post
import com.example.navigationdrawerapp.adapters.PostViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var options: FirebaseRecyclerOptions<Post>
    private lateinit var listeners: PostListeners


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.i("onCreateView", "On create view achanai")
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        database = Firebase.database.reference

        val query = database.child("posts")

        options = FirebaseRecyclerOptions.Builder<Post>()
            .setQuery(query, Post::class.java)
            .setLifecycleOwner(viewLifecycleOwner)
            .build()

        listeners = object : PostListeners {
            override fun navigateToPostDetail(title: String) {
                navigateToPostDetail(title)
            }

            override fun like(email: String) {
                Toast.makeText(context, "Le diste like", Toast.LENGTH_SHORT).show()
            }
        }

        val adapter = PostAdapter(options, listeners)

        binding.recViewPosts.adapter = adapter
        binding.recViewPosts.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            navigateToCreatePost()
        }

    }

    override fun onStop() {
        super.onStop()
        binding.recViewPosts.adapter = null
        binding.recViewPosts.layoutManager = null
    }

    override fun onResume() {
        super.onResume()

        val adapter = PostAdapter(options, listeners)
        binding.recViewPosts.adapter = adapter
        binding.recViewPosts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToCreatePost() {
        val intent = Intent(context, CreatePostActivity::class.java)
        intent.putExtra("userEmail", auth.currentUser!!.email)
        startActivity(intent)
    }

    private fun navigateToPostDetail(info: String) {
        Log.i("PostInfo",  info)

    }

}