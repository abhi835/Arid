package com.example.arid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arid.Adapter.IPostAdapter
import com.example.arid.Adapter.PostAdapter
import com.example.arid.Daos.PostDao
import com.example.arid.Model.Post
import com.example.arid.databinding.ActivityDemoBinding
import com.example.arid.databinding.ActivityMainBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query

class DemoActivity : AppCompatActivity(),IPostAdapter {
    private lateinit var binding: ActivityDemoBinding
    private lateinit var adapter: PostAdapter
    private lateinit var postDao: PostDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_demo)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpRecyclerView()
    }
    fun setUpRecyclerView(){
        postDao = PostDao()
//        val postCollections = postDao.postCollections
        val postCollections = postDao.postCollection
        val query = postCollections.orderBy("createdAt", Query.Direction.DESCENDING)      //Here we are getting Query from PostDao and we will feed the data into adapter we have also sorted out posts on the basis of "created AT" which is time and it will show newest posts at first in recyclerView
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Post>().setQuery(query,Post::class.java).build()


        adapter = PostAdapter(recyclerViewOptions,this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


    }
    override fun onStart() {    //We want adapter to listen the changes made in database so we have created onstart function which will start listening once the app start
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {    //and we Adapter to stop listen if app closes
        super.onStop()
        adapter.stopListening()
    }
//    override fun onLikeClicked(postId: String) {
//       postDao.updateOptions(postId)
//    }

   override fun onLikeOneClicked(postId: String) {
        postDao.updateOneOption(postId)
    }

   override fun onLikeTwoClicked(postId: String) {
        postDao.updateTwoOption(postId)
    }

   override fun onLikeThreeClicked(postId: String) {
        postDao.updateThreeOption(postId)
    }
   override fun onLikeFourClicked(postId: String){
        postDao.updateFourOption(postId)
    }
}