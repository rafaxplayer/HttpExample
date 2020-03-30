package com.jrs.httpexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jrs.httpexample.io.ApiAdapter
import com.jrs.httpexample.models.ResponseUsers
import com.jrs.httpexample.models.User
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemListActivity : AppCompatActivity(), Callback<ResponseUsers> {

    private var twoPane: Boolean = false

    private var users : List<User> = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (item_detail_container != null) {
            twoPane = true
        }

        var  call = ApiAdapter.getApiService()?.getUsers()
        call?.enqueue(this)

        setupRecyclerView(item_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = UsersAdapter(this, users, twoPane)
    }

    override fun onFailure(call: Call<ResponseUsers>, t: Throwable) {
        t.printStackTrace()
    }

    override fun onResponse(call: Call<ResponseUsers>, response: Response<ResponseUsers>) {

        if(response.isSuccessful){
            val list= response.body()
            list?.let {
                users = it.responseUsers;
                println(it.responseUsers)
                setupRecyclerView(item_list) }
        }else{
            println(response.errorBody())
        }

    }



}
