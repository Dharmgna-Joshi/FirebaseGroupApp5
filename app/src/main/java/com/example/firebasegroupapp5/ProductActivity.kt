package com.example.firebasegroupapp5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasegroupapp5.databinding.ActivityProductBinding

import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private var adapter: ProductsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val query = FirebaseDatabase.getInstance().reference.child("Products")
        val options =
            FirebaseRecyclerOptions.Builder<Products>().setQuery(query, Products::class.java)
                .build()
        adapter = ProductsAdapter(options)


        val rView: RecyclerView = findViewById(R.id.products_recycler_view)
        rView.layoutManager = GridLayoutManager(this, 2)
        rView.adapter = adapter
        val cartFab: FloatingActionButton = findViewById(R.id.cart_fab)
        cartFab.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }
}