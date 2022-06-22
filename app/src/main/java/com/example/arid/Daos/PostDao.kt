package com.example.arid.Daos

import com.example.arid.Model.Post
import com.example.arid.Model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PostDao {
    val db = FirebaseFirestore.getInstance()
    val postCollection = db.collection("posts")
    val auth = Firebase.auth

    fun addPost(question:String,option1:String,option2:String,option3:String,option4:String){
        GlobalScope.launch {

            val currentUserId = auth.currentUser!!.uid
            val userDao = UserDao()
            val user = userDao.getUserById(currentUserId).await().toObject(User::class.java)!!
            val currentTime = System.currentTimeMillis()
            val post = Post(question,option1,option2,option3,option4,user,currentTime)


//            val postt = Post()
            postCollection.document().set(post)
        }
    }

    private fun getPostById(postId: String): Task<DocumentSnapshot> {
        return postCollection.document(postId).get()

    }
    fun updateOneOption(postId:String){
        GlobalScope.launch {
            val currentUserId  = auth.currentUser!!.uid
            val post = getPostById(postId).await().toObject(Post::class.java)!!
            val isPolled = post.polledBy.contains(currentUserId)

            if(isPolled){
//                post.polledBy.remove(currentUserId)

            }else{
                post.polledBy.add(currentUserId)
                post.optionOne.add(currentUserId)

            }
            postCollection.document(postId).set(post)
        }
    }

    fun updateTwoOption(postId:String){
        GlobalScope.launch {
            val currentUserId  = auth.currentUser!!.uid
            val post = getPostById(postId).await().toObject(Post::class.java)!!
            val isPolled = post.polledBy.contains(currentUserId)

            if(isPolled){
//                post.polledBy.remove(currentUserId)

            }else{
                post.polledBy.add(currentUserId)
                post.optionTwo.add(currentUserId)
//                val washingtonRef = db.collection("posts").document(postId)
//                washingtonRef.update("option2Count", FieldValue.increment(50))
//                postCollection.document(currentUserId).update("option2Count",FieldValue.increment(1))
//             post.option2Count.plus(1)
//                db.collection("posts").document(currentUserId).
//                post.option2Count = post.option2Count+1
//                db.collection("posts").document(currentUserId).set()
//                postCollection.document(postId).set(post)

            }
            postCollection.document(postId).set(post)
        }
    }
    fun updateThreeOption(postId:String){
        GlobalScope.launch {
            val currentUserId  = auth.currentUser!!.uid
            val post = getPostById(postId).await().toObject(Post::class.java)!!
            val isPolled = post.polledBy.contains(currentUserId)

            if(isPolled){
//                post.polledBy.remove(currentUserId)

            }else{
                post.polledBy.add(currentUserId)
                post.optionThree.add(currentUserId)
            }
            postCollection.document(postId).set(post)
        }
    }
    fun updateFourOption(postId:String){
        GlobalScope.launch {
            val currentUserId  = auth.currentUser!!.uid
            val post = getPostById(postId).await().toObject(Post::class.java)!!
            val isPolled = post.polledBy.contains(currentUserId)

            if(isPolled){
//                post.polledBy.remove(currentUserId)

            }else{
                post.polledBy.add(currentUserId)
                post.optionFour.add(currentUserId)
            }
            postCollection.document(postId).set(post)
        }
    }
}