package com.example.arid.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arid.Adapter.IPostAdapter
import com.example.arid.Adapter.PostAdapter
import com.example.arid.Daos.PostDao
import com.example.arid.Model.Post
import com.example.arid.databinding.FragmentHomeBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.common.collect.Collections2.filter
import com.google.common.collect.Iterables.filter
import com.google.firebase.firestore.Query
import java.util.Locale.filter


class HomeFragment : Fragment() ,IPostAdapter{
    private lateinit var postDao: PostDao
    private lateinit var adapter: PostAdapter
    private lateinit var binding:FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
//            setUpRecyclerView()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setUpRecyclerView()
        postDao = PostDao()
//        val postCollections = postDao.postCollections
        val postCollections = postDao.postCollection
        val query = postCollections.orderBy("createdAt", Query.Direction.DESCENDING)      //Here we are getting Query from PostDao and we will feed the data into adapter we have also sorted out posts on the basis of "created AT" which is time and it will show newest posts at first in recyclerView
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Post>().setQuery(query,Post::class.java).build()

        binding.recyclerView.layoutManager = LinearLayoutManager(getContext())
        adapter = PostAdapter(recyclerViewOptions,this)
        binding.recyclerView.adapter = adapter

        ////


//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // on below line we are checking
//                // if query exist or not.
//                if (postCollections.equals(query)) {
//                    // if query exist within list we
//                    // are filtering our list adapter.
//                    PostAdapter.filter.filter(query)
//                } else {
//                    // if query is not present we are displaying
//                    // a toast message as no  data found..
////                    Toast.makeText(this@MainActivity, "No Language found..", Toast.LENGTH_LONG)
////                        .show()
//                }
//                return false
//            }
//            override fun onQueryTextChange(p0: String?): Boolean {
//                TODO("Not yet implemented")
//            }
//        })
    }
private fun setUpRecyclerView(){
        postDao = PostDao()
//        val postCollections = postDao.postCollections
        val postCollections = postDao.postCollection
        val query = postCollections.orderBy("createdAt", Query.Direction.DESCENDING)      //Here we are getting Query from PostDao and we will feed the data into adapter we have also sorted out posts on the basis of "created AT" which is time and it will show newest posts at first in recyclerView
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Post>().setQuery(query,Post::class.java).build()

    adapter = PostAdapter(recyclerViewOptions,this)

    binding.recyclerView.adapter = adapter
    binding.recyclerView.layoutManager = LinearLayoutManager(activity)

///////////////////
//    val linearLayoutManager =LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//    adapter = PostAdapter(recyclerViewOptions,this)
//
//    binding.recyclerView.layoutManager = linearLayoutManager
//    binding.recyclerView.adapter = adapter
    /////////////

    }

    override fun onStart() {    //We want adapter to listen the changes made in database so we have created onstart function which will start listening once the app start
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {    //and we Adapter to stop listen if app closes
        super.onStop()
        adapter.stopListening()
    }

    override fun onLikeOneClicked(postId: String) {
       postDao.updateOneOption(postId)
    }
    override fun onLikeTwoClicked(postId: String) {
        postDao.updateTwoOption(postId)
    }

    override fun onLikeThreeClicked(postId: String) {
        postDao.updateThreeOption(postId)
    }
//    override fun onLikeFourClicked(postId: String){
//        postDao.updateFourOption(postId)
//    }
}