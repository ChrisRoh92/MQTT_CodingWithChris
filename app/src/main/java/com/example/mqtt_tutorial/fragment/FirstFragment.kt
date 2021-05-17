/*
 * Copyright (c) 2021 CodingWithChris - All Rights Reserved
 */

package com.example.mqtt_tutorial.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mqtt_tutorial.MainViewModel
import com.example.mqtt_tutorial.R
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    // ViewModel anlegen:
    private lateinit var viewModel: MainViewModel





    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity(),defaultViewModelProviderFactory).get(
            MainViewModel::class.java)


        first_btn_login.setOnClickListener {
            TODO("Check if Inputs are not empty or null and connect to Broker")


        }

    }

    // Methode übernimmt Daten aus den Feldern und versucht Verbindung aufzubauen..
    private fun connectToBroker()
    {
        TODO("Show the Views, while trying to connect and Block the inputs")


        TODO("Init the MQTT Client in the MainViewModel with the contents of the Edittexts")
        // Hint: If Connecting was succesfull, navigate to SecondFragment



    }


    private fun blockInputs()
    {
        TODO("Block all EditTexts and Buttons while trying to connect")
    }

    private fun unblockInputs()
    {
        TODO("Unblock all EditTexts and Buttons AFTER trying to connect")
    }



    private fun checkInputs():Boolean
    {
        TODO("Check here if all EditTexts are Empty")
        return false
    }





    private fun hideViews(vararg views: View)
    {

        TODO("Make the the Progressbar and the TextView Visibility.GONE")

    }

    private fun showViews(vararg views: View)
    {
        TODO("Make the the Progressbar and the TextView Visibility.VISIBLE")
    }
}