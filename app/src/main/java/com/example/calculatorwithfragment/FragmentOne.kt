package com.example.calculatorwithfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_one.*

//import com.example.demo2.databinding.FragmentOne
//import com.example.android.navigation.databinding.FragmentGameBinding

class FragmentOne : Fragment() {
    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var textView: TextView
    private lateinit var buttonReset: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate= inflater.inflate(R.layout.fragment_one, container, false)
        buttonAdd = inflate.findViewById(R.id.buttonAdd)
        buttonSub = inflate.findViewById(R.id.buttonSub)
        buttonMultiply = inflate.findViewById(R.id.buttonMultiply)
        buttonDivide = inflate.findViewById(R.id.buttonDivide)
        textView = inflate.findViewById(R.id.textViewresult)
        buttonReset = inflate.findViewById(R.id.buttonReset)
        buttonAdd.setOnClickListener { Navigation.findNavController(it).navigate(FragmentOneDirections.actionFragmentOneToFragmentTwo(buttonAdd.text.toString())) }
        buttonSub.setOnClickListener { Navigation.findNavController(it).navigate(FragmentOneDirections.actionFragmentOneToFragmentTwo(buttonSub.text.toString())) }
        buttonMultiply.setOnClickListener { Navigation.findNavController(it).navigate(FragmentOneDirections.actionFragmentOneToFragmentTwo(buttonMultiply.text.toString())) }
        buttonDivide.setOnClickListener { Navigation.findNavController(it).navigate(FragmentOneDirections.actionFragmentOneToFragmentTwo(buttonDivide.text.toString())) }

        val argsfromtwo = FragmentOneArgs.fromBundle(requireArguments())
        if(argsfromtwo.result!="null"){
            onResult()
            textView.text = argsfromtwo.result
        }
        buttonReset.setOnClickListener { onReset() }

        return inflate
    }

    private fun onResult(){
        textView.visibility = View.VISIBLE
        buttonReset.visibility = View.VISIBLE
        buttonAdd.visibility = View.GONE
        buttonSub.visibility = View.GONE
        buttonMultiply.visibility = View.GONE
        buttonDivide.visibility = View.GONE
    }

    private fun onReset(){
        textView.visibility = View.GONE
        buttonReset.visibility = View.GONE
        buttonAdd.visibility = View.VISIBLE
        buttonSub.visibility = View.VISIBLE
        buttonMultiply.visibility = View.VISIBLE
        buttonDivide.visibility = View.VISIBLE
    }

}