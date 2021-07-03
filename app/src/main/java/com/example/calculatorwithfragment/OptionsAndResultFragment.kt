package com.example.calculatorwithfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController

class OptionsAndResultFragment : Fragment() {

    private lateinit var buttonAdd:Button
    private lateinit var resultText:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_options_and_result, container, false)
        buttonAdd = inflate.findViewById(R.id.buttonAdd)
        resultText = inflate.findViewById(R.id.textViewresult)
        buttonAdd.setOnClickListener { view:View ->
            view.findNavController().navigate(OptionsAndResultFragmentDirections.actionOptionsAndResultFragmentToValueEnteringFragment(buttonAdd.text.toString()))
        }
        val args = OptionsAndResultFragmentArgs.fromBundle(requireArguments())
        if(args.result!="null")
            resultText.text = args.result
        return inflate
    }

}