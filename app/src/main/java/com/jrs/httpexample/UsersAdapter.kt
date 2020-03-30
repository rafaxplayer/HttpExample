package com.jrs.httpexample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrs.httpexample.models.User
import com.jrs.httpexample.ui.CircleTransformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_content.view.*
import java.util.*

class UsersAdapter(private val parentActivity: ItemListActivity,
                   private val values: List<User>,
                   private val twoPane: Boolean) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bindItems(item)
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        fun bindItems(item:User){
            itemView.txtName.text= item.name.first
            itemView.txtEmail.text= item.email
            itemView.txtAge.text= String.format(Locale.getDefault(),"Edad : %s",item.dob.age)
            Picasso.get()
                .load(item.picture.medium)
                .error(R.drawable.error_list_image)
                .placeholder(R.drawable.default_list_image)
                .transform(CircleTransformation())
                .into(itemView.avatar)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            println(v)
            if (twoPane) {
                val fragment = ItemDetailFragment().apply {
                    arguments = Bundle().apply {
                        //putParcelable(ItemDetailFragment.ARG_ITEM_USER, item)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v?.context, ItemDetailActivity::class.java).apply {
                    //putExtra(ItemDetailFragment.ARG_ITEM_USER, item)
                }

                    //v?.context?.startActivity(intent)

            }
        }
    }
}


