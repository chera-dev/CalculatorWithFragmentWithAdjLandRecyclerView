package com.example.calculatorwithfragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ButtonAdapter(private val parentContext: Context, private val actionListener: ItemActionListener,
                    private var list: Array<Data>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun changeListData(newData: Array<Data>){
        list = newData
        this.notifyDataSetChanged()
    }

    inner class ButtonHolder(view: View): RecyclerView.ViewHolder(view) {
        var itemButton: Button
        init {
            itemButton = view.findViewById(R.id.buttonItem)
            itemButton.setOnClickListener {
                actionListener.onItemClicked(list[position].data)
            }
        }
    }

    inner class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var itemTextView: TextView
        init {
            itemTextView = view.findViewById(R.id.textView2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_ONE)
            ButtonHolder(LayoutInflater.from(parentContext).inflate(R.layout.button_view,
                parent, false))
        else
            TextViewHolder(LayoutInflater.from(parentContext).inflate(R.layout.text_view,
                parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType == VIEW_TYPE_ONE) {
            (holder as ButtonHolder).itemButton.text = list[position].data
        }
        else{
            (holder as TextViewHolder).itemTextView.text = list[position].data
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}