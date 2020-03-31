package com.jrs.httpexample

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.jrs.httpexample.models.User
import com.jrs.httpexample.ui.CircleTransformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {

            val user =intent.getParcelableExtra(ItemDetailFragment.ARG_ITEM_USER) as User
            Picasso.get().load(user.picture.large).transform(CircleTransformation()).into(imageView2)
            title=user.name.first
            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ItemDetailFragment.ARG_ITEM_USER, user)
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit()
        }
    }

    override fun onStart() {
        super.onStart()

        AnimationUtils.loadAnimation(this, R.anim.scale_anim).also {
                hyperspaceJumpAnimation ->
            imageView2.startAnimation(hyperspaceJumpAnimation)
        }
        AnimationUtils.loadAnimation(this, R.anim.scale_anim).also {
                hyperspaceJumpAnimation ->
            fab.startAnimation(hyperspaceJumpAnimation)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {

                    navigateUpTo(Intent(this, ItemListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
