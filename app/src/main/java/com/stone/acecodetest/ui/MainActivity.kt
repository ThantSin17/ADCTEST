package com.stone.acecodetest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.stone.acecodetest.MyApplication
import com.stone.acecodetest.R
import com.stone.acecodetest.adapter.UserListAdapter
import com.stone.acecodetest.databinding.ActivityMainBinding
import com.stone.acecodetest.listener.OnClickListener
import com.stone.acecodetest.model.User
import com.stone.acecodetest.utils.Status
import com.stone.acecodetest.viewModel.MainViewModel
import com.stone.acecodetest.viewModel.MainViewModelFactory
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), OnClickListener {
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as MyApplication).repository)
    }
    private val executor= Executors.newSingleThreadExecutor()

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setContentView(R.layout.activity_main)

        val adapter = UserListAdapter(this)
        binding.recycler.adapter = adapter


        viewModel.getUserList().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.addPeople(it.data as List<User>)


                }
                Status.ERROR -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                Status.NETWORK_ERROR -> {
                    Toast.makeText(
                        this,
                        it.message ,
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
        viewModel.allUsers.observe(this) {

            adapter.submitList(it)
        }



    }

    override fun onItemClickListener(user: User) {
        startActivity(DetailActivity.getInstance(this, user))
        //Toast.makeText(this, user.address.city, Toast.LENGTH_SHORT).show()
    }
}

