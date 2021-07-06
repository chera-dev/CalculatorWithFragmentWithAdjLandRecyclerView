package com.example.calculatorwithfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController

class FragmentTwo : Fragment() {
    private lateinit var result: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate= inflater.inflate(R.layout.fragment_two, container, false)
        val argsFromOne = FragmentTwoArgs.fromBundle(requireArguments())
        val buttonResult:Button = inflate.findViewById(R.id.buttonResult)
        val num1:EditText = inflate.findViewById(R.id.editTextNumber)
        val num2:EditText = inflate.findViewById(R.id.editTextNumber2)
        buttonResult.text=argsFromOne.action
        buttonResult.setOnClickListener {
            val actionResult = operation(num1.text.toString().toDoubleOrNull(), num2.text.toString().toDoubleOrNull(),argsFromOne.action)
            result = "Action :  ${argsFromOne.action}\nInput1 :  ${num1.text}\nInput2 :  ${num2.text}\nResult :  $actionResult"
            if (actionResult != null)
                navigateToPreviousFragment()
        }
        return inflate
    }

    private fun operation(num1: Double?, num2: Double?, action : String): Double?{
        return if(num1 != null && num2 != null){
            when(action){
                "ADDITION" -> num1 + num2
                "SUB" -> num1 - num2
                "MULTIPLY" -> num1 * num2
                else -> num1 / num2
            }
        }
        else{
            Toast.makeText(activity, "enter valid number and try again", Toast.LENGTH_LONG).show()
            null
        }
    }

    private fun navigateToPreviousFragment(){
        setFragmentResult(
                FragmentOne.MY_REQUEST_KEY, bundleOf(FragmentOne.MY_STRING_KEY to result)
        )
        findNavController().popBackStack()
    }
}