package com.example.calculatorwithfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_two.*

class FragmentTwo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Fragment 2","oncreateview called in fragment 2")
        // Inflate the layout for this fragment
        val inflate= inflater.inflate(R.layout.fragment_two, container, false)
        val argsFromOne = FragmentTwoArgs.fromBundle(requireArguments())
        val buttonResult:Button = inflate.findViewById(R.id.buttonResult)
        val num1:EditText = inflate.findViewById(R.id.editTextNumber)
        val num2:EditText = inflate.findViewById(R.id.editTextNumber2)
        buttonResult.text=argsFromOne.action
        buttonResult.setOnClickListener {
            val actionResult = operation(num1.text.toString().toDoubleOrNull(), num2.text.toString().toDoubleOrNull(),argsFromOne.action)
            val result = "Action :  ${argsFromOne.action}\nInput1 :  ${num1.text}\nInput2 :  ${num2.text}\nResult :  $actionResult"
            if (actionResult!=null)
                Navigation.findNavController(it).navigate(FragmentTwoDirections.actionFragmentTwoToFragmentOne(result))
        }
        return inflate
    }

    private fun operation(num1: Double?, num2: Double?, action : String):Double?{
        return if(num1 != null && num2 != null){
            when(action){
                "ADDITION" -> num1+num2
                "SUB" -> num1-num2
                "MULTIPLY" -> num1*num2
                else -> num1/num2
            }
        }
        else{
            Toast.makeText(activity, "enter valid number and try again", Toast.LENGTH_LONG).show()
            null
        }
    }

    override fun onAttach(context: Context) {
        Log.i("Fragment 2","onattach called in fragment 2")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("Fragment 2","onreate called in fragment 2")
        super.onCreate(savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i("Fragment 2","onactivitycreated called in fragment 2")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.i("Fragment 2","onstart called in fragment 2")
        super.onStart()
    }

    override fun onResume() {
        Log.i("Fragment 2","onResume called in fragment 2")
        super.onResume()
    }

    override fun onPause() {
        Log.i("Fragment 2","onPause called in fragment 2")
        super.onPause()
    }

    override fun onStop() {
        Log.i("Fragment 2","onStop called in fragment 2")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.i("Fragment 2","onDestroyView called in fragment 2")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i("Fragment 2","onDestroy called in fragment 2")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("Fragment 2","onDetach called in fragment 2")
        super.onDetach()
    }
}