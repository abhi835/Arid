package com.example.arid.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.arid.Daos.PostDao
import com.example.arid.R
import com.example.arid.databinding.FragmentAddPostBinding


class AddPostFragment : Fragment() {
    private lateinit var postDao: PostDao
    private lateinit var binding:FragmentAddPostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentAddPostBinding.inflate(inflater, container, false)
        postDao = PostDao()
        binding.post.setOnClickListener{
            val question = binding.question.text.toString().trim()
            val option1 = binding.option1.text.toString().trim()
            val option2 = binding.option2.text.toString().trim()
            val option3 = binding.option3.text.toString().trim()
            val option4 = binding.option4.text.toString().trim()
            if(question.isNotEmpty()&&option1.isNotEmpty()&&option2.isNotEmpty()&&option3.isNotEmpty()&&option4.isNotEmpty()){
                postDao.addPost(question, option1, option2, option3,option4)
                binding.question.text.clear()
                binding.option1.text.clear()
                binding.option2.text.clear()
                binding.option3.text.clear()
                                binding.option4.text.clear()
                Toast.makeText(activity,"Post Created!",Toast.LENGTH_SHORT).show()
            }

//            val fragment: Fragment =HomeFragment()
//            val addpostfragment:Fragment = AddPostFragment()
//            addpostfragment.
//            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
//            transaction.replace(R.id.linearlayout, fragment)
//            transaction.addToBackStack(null)
//            transaction.commit()

        }





        return binding.root
    }


}