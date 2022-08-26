package com.helvetica.bizmanager

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helvetica.bizmanager.databinding.ActivitySecondBinding
import com.helvetica.bizmanager.dialog.LoadingDialog
import com.helvetica.bizmanager.model.Worker
import com.helvetica.bizmanager.repository.Repository
import retrofit2.Response

class SecondActivity : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: SecondViewModel
    private val myAdapter by lazy { RvEmployeesAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        setupRecyclerView()
        val repository = Repository()
        val viewModelFactory = SecondViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[SecondViewModel::class.java]
        var loadingDialog = LoadingDialog(this)
        var firstTime = viewModel.getFirstTime()
        if (firstTime) {
            loadingDialog.startLoadingDialog()
        }
        viewModel.getWorkers()
        viewModel.getWorkersImg()
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it) }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.myResponseImg.observe(this) { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setDataImg(it) }
            } else {
                Log.d("IMGERR", response.code().toString())
            }
            if (firstTime) {
                Handler().postDelayed({
                    loadingDialog.dismissDialog()
                }, 1000)
            } else if (loadingDialog != null) {
                loadingDialog.dismissDialog()

            }
            viewModel.setFirstTime(false)
        }
        binding.btnScdBack.setOnClickListener {
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? androidx.appcompat.widget.SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        filterList(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        filterList(newText)
        return true
    }

    fun filterList(newText: String) {
        val myFullListOfWorkers: List<Worker> = myAdapter.myList
        val myFilteredList = mutableListOf<Worker>()
        for (worker in myFullListOfWorkers) {
            if (newText.uppercase() in worker.naam) {
                myFilteredList.add(worker)
            }
        }
        if (myFilteredList.isEmpty()) {
            Toast.makeText(
                this,
                "You haven't hired anyone with that name yet :(",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            myAdapter.setData(myFilteredList)
        }
    }


    private fun setupRecyclerView() {
        binding.rvEmployees.adapter = myAdapter
        binding.rvEmployees.layoutManager = LinearLayoutManager(this)
    }
}

