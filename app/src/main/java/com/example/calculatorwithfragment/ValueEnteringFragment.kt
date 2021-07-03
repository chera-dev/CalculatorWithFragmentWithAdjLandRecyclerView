package com.example.calculatorwithfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController


class ValueEnteringFragment : Fragment() {
    private lateinit var resultButton:Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_value_entering, container, false)
        resultButton = inflate.findViewById(R.id.buttonResult)
        val args = ValueEnteringFragmentArgs.fromBundle(requireArguments())
        resultButton.text = args.operationName
        resultButton.setOnClickListener { view:View ->
            view.findNavController().navigate(ValueEnteringFragmentDirections.actionValueEnteringFragmentToOptionsAndResultFragment())
        }
        return inflate
    }

}