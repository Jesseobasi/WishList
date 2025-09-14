package com.example.wishlist

import WishlistAdapter
import WishlistItem
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.wishlistRv)
        val nameInput: EditText = findViewById(R.id.itemName)
        val linkInput: EditText = findViewById(R.id.itemLink)
        val priceInput: EditText = findViewById(R.id.itemPrice)
        val submitButton: Button = findViewById(R.id.submitButton)

        adapter = WishlistAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        submitButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val link = linkInput.text.toString().trim()
            val price = priceInput.text.toString().toDoubleOrNull() ?: 0.0

            if (name.isNotEmpty()) {
                val newItem = WishlistItem(name, link, price)
                adapter.addItem(newItem)

                nameInput.text.clear()
                linkInput.text.clear()
                priceInput.text.clear()
            } else {
                Toast.makeText(this, "Please enter an item name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}