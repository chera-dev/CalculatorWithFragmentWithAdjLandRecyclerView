package com.example.calculatorwithfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.clearFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.setFragmentResultListener


class FragmentOne : Fragment() {
    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var textView: TextView
    private lateinit var buttonReset: Button
    private var state = 0
    private var operationResult:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate= inflater.inflate(R.layout.fragment_one, container, false)
        buttonAdd = inflate.findViewById(R.id.buttonAdd)
        buttonSub = inflate.findViewById(R.id.buttonSub)
        buttonMultiply = inflate.findViewById(R.id.buttonMultiply)
        buttonDivide = inflate.findViewById(R.id.buttonDivide)
        textView = inflate.findViewById(R.id.textViewresult)
        buttonReset = inflate.findViewById(R.id.buttonReset)
        if (savedInstanceState != null) {
            state = savedInstanceState.getInt("state")
            operationResult = savedInstanceState.getString("operationResult")
        }
        buttonAdd.setOnClickListener { navigateToFragmentTwo(buttonAdd.text.toString())}
        buttonSub.setOnClickListener { navigateToFragmentTwo(buttonSub.text.toString())}
        buttonMultiply.setOnClickListener { navigateToFragmentTwo(buttonMultiply.text.toString()) }
        buttonDivide.setOnClickListener { navigateToFragmentTwo(buttonDivide.text.toString()) }
        buttonReset.setOnClickListener {
            clearFragmentResult(MY_REQUEST_KEY)
            operationResult = null
            onReset()
        }
        if(state == 0)
            onReset()
        else {
            textView.text = operationResult
            onResult()
        }
        return inflate
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("state", state)
        outState.putString("operationResult", operationResult)
    }

    private fun navigateToFragmentTwo(operationName: String){
        findNavController().navigate(FragmentOneDirections.actionFragmentOneToFragmentTwo(operationName))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(MY_REQUEST_KEY){ _, result ->
            result.getString(MY_STRING_KEY)?.let { stringMine ->
                textView.text = stringMine
                operationResult = stringMine
                onResult()
            }
        }
    }

    private fun onResult(){
        state = 1
        textView.visibility = View.VISIBLE
        buttonReset.visibility = View.VISIBLE
        buttonAdd.visibility = View.GONE
        buttonSub.visibility = View.GONE
        buttonMultiply.visibility = View.GONE
        buttonDivide.visibility = View.GONE
    }

    private fun onReset(){
        state = 0
        textView.visibility = View.GONE
        buttonReset.visibility = View.GONE
        buttonAdd.visibility = View.VISIBLE
        buttonSub.visibility = View.VISIBLE
        buttonMultiply.visibility = View.VISIBLE
        buttonDivide.visibility = View.VISIBLE
    }

    companion object{
        const val MY_REQUEST_KEY = "myRequestKey"
        const val MY_STRING_KEY = "myStringKey"
    }

}