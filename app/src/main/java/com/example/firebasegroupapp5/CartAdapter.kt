package com.example.firebasegroupapp5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cartItems: List<CartItem>, private val onItemRemove: (String) -> Unit) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View, val onItemRemove: (String) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tvProductName)
        private val priceTextView: TextView = itemView.findViewById(R.id.tvProductPrice)
        private val removeButton: Button = itemView.findViewById(R.id.btnRemove)

        fun bind(cartItem: CartItem) {
            nameTextView.text = cartItem.name
            priceTextView.text = String.format("$%.2f", cartItem.prize)
            removeButton.setOnClickListener { onItemRemove(cartItem.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_layout, parent, false)
        return CartViewHolder(view, onItemRemove)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    override fun getItemCount() = cartItems.size
}
