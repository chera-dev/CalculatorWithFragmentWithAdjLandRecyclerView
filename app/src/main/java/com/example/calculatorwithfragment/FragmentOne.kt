package com.example.calculatorwithfragment

import android.content.res.Configuration
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.clearFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope


class FragmentOne : Fragment() {
    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var textView: TextView
    private lateinit var buttonReset: Button
    private var operationResult : String? = null
    private var stateOfFragmentOne : Int = 0
    private var mode:Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("f1","f1 root")
        val inflate= inflater.inflate(R.layout.fragment_one, container, false)
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            mode = 1
        else if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            mode = 0
        if (savedInstanceState != null) {
            Log.e("aaaaaaaa","f1 saved")
            /*val transaction = requireActivity().supportFragmentManager.beginTransaction()
            if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Log.e("f1","f1 after instance saved landscape")
                transaction.replace(R.id.my_fragmentholder2, FragmentTwo())
            }
            else if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                Log.e("f1","f1 after instance saved portrait")
                val fragmentOne = FragmentOne()
                fragmentOne.arguments = savedInstanceState
                transaction.replace(R.id.my_fragmentholder,fragmentOne)
            }
            transaction.commit()*/
            stateOfFragmentOne = savedInstanceState.getInt("stateOfFragmentOne")
            operationResult = savedInstanceState.getString("operationResult")
        }
        else
            Log.e("zzzzzzzzzzz","f1 not saved")
        buttonAdd = inflate.findViewById(R.id.buttonAdd)
        buttonSub = inflate.findViewById(R.id.buttonSub)
        buttonMultiply = inflate.findViewById(R.id.buttonMultiply)
        buttonDivide = inflate.findViewById(R.id.buttonDivide)
        textView = inflate.findViewById(R.id.textViewresult)
        buttonReset = inflate.findViewById(R.id.buttonReset)
        buttonAdd.setOnClickListener { navigateToFragmentTwo(buttonAdd.text.toString())}
        buttonSub.setOnClickListener { navigateToFragmentTwo(buttonSub.text.toString())}
        buttonMultiply.setOnClickListener { navigateToFragmentTwo(buttonMultiply.text.toString()) }
        buttonDivide.setOnClickListener { navigateToFragmentTwo(buttonDivide.text.toString()) }
        buttonReset.setOnClickListener {
            clearFragmentResult(MY_REQUEST_KEY)
            operationResult = null
            onReset()
        }
        if(stateOfFragmentOne == 0) {
            onReset()
        }
        else {
            textView.text = operationResult
            onResult()
        }
        return inflate
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.e("f1","f1 fun onsave")
        super.onSaveInstanceState(outState)
        outState.putInt("stateOfFragmentOne", stateOfFragmentOne)
        outState.putString("operationResult", operationResult)
        outState.putInt("mode", mode!!)
    }
//////////////////////////////
    private fun navigateToFragmentTwo(operationName: String){
        Log.e("f1","f1 function navigate to f2")
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        val fragmentTwo = FragmentTwo()
        val args = Bundle()
        args.putString("operationName",operationName)
        fragmentTwo.setArguments(args)
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e("f1","f1 navigate to f2 in landscape")
            Toast.makeText(activity, "landscape mode in f1", Toast.LENGTH_LONG).show()
            transaction.replace(R.id.my_fragmentholder2, fragmentTwo)
            transaction.addToBackStack(FragmentOne.toString())
        }
        else if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.e("f1","f1 navigate to f2 in portrait")
            Toast.makeText(activity, "portrait mode in f1", Toast.LENGTH_LONG).show()
            transaction.replace(R.id.my_fragmentholder,fragmentTwo)
            transaction.addToBackStack(FragmentOne.toString())
        }
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(MY_REQUEST_KEY){ _, result ->
            result.getString(MY_STRING_KEY)?.let { stringMine ->
                textView.text = stringMine
                operationResult = stringMine
                Log.e("f1","f1 after we get result")
                onResult()
            }
        }
    }

    private fun onResult(){
        Log.e("f1","f1 in result function")
        stateOfFragmentOne = 1
        textView.visibility = View.VISIBLE
        buttonReset.visibility = View.VISIBLE
        buttonAdd.visibility = View.GONE
        buttonSub.visibility = View.GONE
        buttonMultiply.visibility = View.GONE
        buttonDivide.visibility = View.GONE
    }

    private fun onReset(){
        Log.e("f1","f1 in reset function")
        stateOfFragmentOne = 0
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

    override fun onDestroy() {
        super.onDestroy()
        Log.e("qqqqqqqqq","f1 destroyed")
    }
}
