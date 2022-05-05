package com.stone.acecodetest.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.stone.acecodetest.R
import com.stone.acecodetest.databinding.ActivityDetailBinding
import com.stone.acecodetest.model.User

class DetailActivity : AppCompatActivity() {
    companion object{
        fun getInstance(context:Context,user:User):Intent{
            val bundle=Bundle()
            bundle.putSerializable("user",user)
            val intent= Intent(context, DetailActivity::class.java)
            intent.putExtras(bundle)
            return intent
        }
    }
    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_detail)
       // setContentView(R.layout.activity_detail)

        val bundle=intent.extras
        val user=bundle?.getSerializable("user")
        binding.user= user as User?

    }
}