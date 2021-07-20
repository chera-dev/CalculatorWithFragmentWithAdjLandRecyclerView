package com.example.calculatorwithfragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.clearFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FragmentOne : Fragment(), ItemActionListener {

    private var operationResult: String? = null
    private var stateOfFragmentOne: Int = 0

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ButtonAdapter

    private var buttonName = arrayOf<Any>(OPERATION.ADDITION,OPERATION.SUB,OPERATION.MULTIPLY,OPERATION.DIVIDE)
    private var resultValue = arrayOf<Any>("",OPERATION.RESET)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_one, container, false)

        recyclerView = inflate.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)

        adapter = ButtonAdapter(requireContext(),this,resultValue)
        recyclerView.adapter = adapter

        if (savedInstanceState != null) {
            stateOfFragmentOne = savedInstanceState.getInt("stateOfFragmentOne")
            operationResult = savedInstanceState.getString("operationResult")
        }

        if(stateOfFragmentOne == 0)
            onReset()
        else {
            resultValue[0]= operationResult.toString()
            onResult()
        }
        return inflate
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("stateOfFragmentOne", stateOfFragmentOne)
        outState.putString("operationResult", operationResult)
    }

    override fun onItemClicked(action: String) {
        if (action != "RESET")
            navigateToFragmentTwo(action)
        else {
            clearFragmentResult(MY_REQUEST_KEY)
            operationResult = null
            onReset()
        }
    }

    private fun navigateToFragmentTwo(operationName: String){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        val fragmentTwo = FragmentTwo()
        val args = Bundle()
        args.putString("operationName",operationName)
        fragmentTwo.arguments = args
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transaction.replace(R.id.my_fragmentholder2, fragmentTwo, "fragmentTwo")
            transaction.addToBackStack(FragmentOne.toString())
        }
        else if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            transaction.replace(R.id.my_fragmentholder,fragmentTwo, "fragmentTwo")
            transaction.addToBackStack(FragmentOne.toString())
        }
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(MY_REQUEST_KEY){ _, result ->
            result.getString(MY_STRING_KEY)?.let { stringMine ->
                resultValue[0] = stringMine
                operationResult = stringMine
                onResult()
            }
        }
    }

    private fun onResult(){
        stateOfFragmentOne = 1
        adapter.changeListData(resultValue)
    }

    private fun onReset(){
        stateOfFragmentOne = 0
        adapter.changeListData(buttonName)
    }

    companion object{
        const val MY_REQUEST_KEY = "myRequestKey"
        const val MY_STRING_KEY = "myStringKey"
    }

}
