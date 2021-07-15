package com.example.calculatorwithfragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class ButtonAdapter(private val parentContext: Context, private val actionListener: ItemActionListener): RecyclerView.Adapter<ButtonAdapter.ButtonHolder>() {

    private var buttonName = arrayOf("ADDITION", "SUB", "MULTIPLY", "DIVIDE")

    inner class ButtonHolder(private val view: View): RecyclerView.ViewHolder(view) {
        var itemButton: Button
        init {
            itemButton = view.findViewById(R.id.buttonItem)
            itemButton.setOnClickListener {
                actionListener.onItemClicked(buttonName[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonHolder {
        val itemView = LayoutInflater.from(parentContext).inflate(R.layout.button_view,parent,false)
        return ButtonHolder(itemView)
    }

    override fun onBindViewHolder(holder: ButtonHolder, position: Int) {
        holder.itemButton.text = buttonName[position]
    }

    override fun getItemCount(): Int {
        return buttonName.size
    }
}