package com.example.calculatorwithfragment

import android.content.res.Configuration
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

class FragmentTwo : Fragment() {
    private lateinit var result: String
    private lateinit var operationName: String
    private lateinit var num1: EditText
    private lateinit var num2: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate= inflater.inflate(R.layout.fragment_two, container, false)
        val buttonResult: Button = inflate.findViewById(R.id.buttonResult)
        num1 = inflate.findViewById(R.id.editTextNumber)
        num2 = inflate.findViewById(R.id.editTextNumber2)
        operationName = arguments?.getString("operationName").toString()
        if (savedInstanceState != null){
            val fm = activity?.supportFragmentManager
            val previousFragmentTwo = fm?.findFragmentByTag("fragmentTwo")
            if (previousFragmentTwo != null) {
                fm.popBackStack()
                val transaction1 = fm.beginTransaction()
                transaction1.remove(previousFragmentTwo).commit()
                val fragmentTwo = FragmentTwo()
                val bundle = previousFragmentTwo.arguments
                bundle?.putString("num1", savedInstanceState.getString("num1"))
                bundle?.putString("num2", savedInstanceState.getString("num2"))
                fragmentTwo.arguments = bundle
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    transaction.replace(R.id.my_fragmentholder2, fragmentTwo, "fragmentTwo")
                    transaction.addToBackStack("fragmentTwo")
                }
                else if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    transaction.replace(R.id.my_fragmentholder,fragmentTwo, "fragmentTwo")
                    transaction.addToBackStack("fragmentTwo")
                }
                transaction.commit()
            }
        }
        num1.text.append(arguments?.getString("num1").toString())
        num2.text.append(arguments?.getString("num2").toString())
        buttonResult.text = operationName
        buttonResult.setOnClickListener {
            val actionResult = operation(num1.text.toString().toDoubleOrNull(), num2.text.toString().toDoubleOrNull(),buttonResult.text.toString())
            result = "Action :  ${buttonResult.text}\nInput1 :  ${num1.text}\nInput2 :  ${num2.text}\nResult :  $actionResult"
            if (actionResult != null)
                navigateToPreviousFragment()
        }
        return inflate
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("operationName", operationName)
        if(num1.text != null)
            outState.putString("num1",num1.text.toString())
        if(num2.text != null)
            outState.putString("num2",num2.text.toString())
    }

    private fun operation(num1: Double?, num2: Double?, action : String): Double?{
        return if(num1 != null && num2 != null){
            when(action){
                "ADDITION" -> num1 + num2
                "SUB" -> num1 - num2
                "MULTIPLY" -> num1 * num2
                "DIVIDE" -> num1 / num2
                else -> {
                    Toast.makeText(activity,"select operation and try again", Toast.LENGTH_LONG).show()
                    null
                }
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
        fragmentManager?.popBackStackImmediate()
    }
}