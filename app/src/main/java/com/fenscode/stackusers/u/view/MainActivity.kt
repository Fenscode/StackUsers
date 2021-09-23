package com.fenscode.stackusers.u.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fenscode.stackusers.R
import com.fenscode.stackusers.data.api.ApiHelper
import com.fenscode.stackusers.data.api.RetrofitBuilder
import com.fenscode.stackusers.data.model.Item
import com.fenscode.stackusers.data.model.Users
import com.fenscode.stackusers.databinding.ActivityMainBinding
import com.fenscode.stackusers.ui.base.ViewModelFactory
import com.fenscode.stackusers.ui.main.adapter.UsersAdapter
import com.fenscode.stackusers.ui.main.viewmodel.MainViewModel
import com.fenscode.stackusers.utils.ItemDecoration
import com.fenscode.stackusers.utils.Status
import java.util.*
import kotlin.collections.ArrayList
import java.util.Comparator

import java.util.Collections

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UsersAdapter
    private lateinit var usersList: List<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UsersAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            ItemDecoration(resources.getDimensionPixelSize(R.dimen.margin))
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getStackUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { users -> it.data?.let { it1 -> retrieveList(it1.items) } }
                        usersList = ArrayList()
                        usersList = it.data?.items!!
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                    else -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun retrieveList(users: List<Item>) {

        Collections.sort<Item>(
            users
        ) { o1, o2 -> o1.displayName.compareTo(o2.displayName) }

        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.actionSearch)
        val searchView = searchItem!!.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        if (usersList.isEmpty()) return
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<Item> = ArrayList()

        // running a for loop to compare elements.
        for (item: Item in usersList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.displayName.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            retrieveList(filteredlist)
        }
    }

}