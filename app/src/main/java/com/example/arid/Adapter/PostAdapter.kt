package com.example.arid.Adapter

import android.os.Build
import android.view.Gravity
import android.view.Gravity.LEFT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.arid.Model.Post
import com.example.arid.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PostAdapter(options: FirestoreRecyclerOptions<Post>, private val listener:IPostAdapter):
    FirestoreRecyclerAdapter<Post, PostAdapter.PostViewHolder> (
        options
    ){

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val postText: TextView =itemView.findViewById(R.id.postTitle)
        val userText: TextView = itemView.findViewById(R.id.userName)
        val createdAt: TextView = itemView.findViewById(R.id.createdAt)
        val userImage: ImageView = itemView.findViewById(R.id.userImage)
        val seekbar1: SeekBar = itemView.findViewById(R.id.seekbar1)
        val seekbar2: SeekBar = itemView.findViewById(R.id.seekbar2)
        val seekbar3: SeekBar = itemView.findViewById(R.id.seekbar3)
//        val seekbar4: SeekBar = itemView.findViewById(R.id.seekbar4)

        val option1: TextView = itemView.findViewById(R.id.option1)
        val option2: TextView = itemView.findViewById(R.id.option2)
        val option3: TextView = itemView.findViewById(R.id.option3)
//        val option4: TextView = itemView.findViewById(R.id.option4)

        val percent1: TextView = itemView.findViewById(R.id.percent1)
        val percent2: TextView = itemView.findViewById(R.id.percent2)
        val percent3: TextView = itemView.findViewById(R.id.percent3)
//        val percent4: TextView = itemView.findViewById(R.id.percent4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val viewHolder =  PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))
        viewHolder.option1.setOnClickListener{
//              listener.onLikeClicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
            listener.onLikeOneClicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
        }
        viewHolder.option2.setOnClickListener{
            listener.onLikeTwoClicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
        }
        viewHolder.option3.setOnClickListener{
//            listener.onLikeClicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
            listener.onLikeThreeClicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
        }
//        viewHolder.option4.setOnClickListener{
////            listener.onLikeClicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
//            listener.onLikeFourClicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
//        }
        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {
        holder.postText.text = model.question
        holder.userText.text = model.createdBy.displayName
        Glide.with(holder.userImage.context).load(model.createdBy.imageUrl).circleCrop().into(holder.userImage)
//        holder.likeCount.text = model.likedBy.size.toString()
        holder.createdAt.text = TimeAgo.using(model.createdAt)
        holder.option1.text = model.option1
        holder.option2.text = model.option2
        holder.option3.text = model.option3
//        holder.option4.text = model.option4

        val auth = Firebase.auth
        val currentUserId = auth.currentUser!!.uid
        val isPolled = model.polledBy.contains(currentUserId)
        if(isPolled){

            val postone:Int = model.optionOne.size
            val posttwo:Int = model.optionTwo.size
            val postthree:Int = model.optionThree.size
//            val postfour:Int = model.optionFour.size

            val total = postone+posttwo+postthree

            holder.seekbar1.setProgress(postone*100/total)
            holder.seekbar2.setProgress(posttwo*100/total)
            holder.seekbar3.setProgress(postthree*100/total)
//            holder.seekbar4.setProgress(postfour*100/total)
//
            holder.percent1.setText((postone*100/total).toString()+"%\n"+postone.toString()+" Votes")
            holder.percent2.setText((posttwo*100/total).toString()+"%\n"+posttwo.toString()+" Votes")
            holder.percent3.setText((postthree*100/total).toString()+"%\n"+postthree.toString()+" Votes")
           // holder.percent4.setText((postfour*100/total).toString()+"%\n"+postfour.toString()+" Votes")

            holder.option1.gravity = Gravity.START
            holder.option2.gravity = Gravity.START
            holder.option3.gravity = Gravity.START
//            holder.option4.gravity = Gravity.START

            holder.percent1.visibility  = View.VISIBLE
            holder.percent2.visibility  =  View.VISIBLE
            holder.percent3.visibility  = View.VISIBLE
//            holder.percent4.visibility = View.VISIBLE
        }else{
//            holder.seekbar1
        }
    }


}

interface IPostAdapter{
    //    fun onLikeClicked(postId:String)
    fun onLikeOneClicked(postId:String)
    fun onLikeTwoClicked(postId:String)
    fun onLikeThreeClicked(postId:String)
//    fun onLikeFourClicked(postId:String)
}