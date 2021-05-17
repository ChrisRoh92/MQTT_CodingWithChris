package com.example.mqtt_tutorial.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mqtt_tutorial.MainViewModel
import com.example.mqtt_tutorial.MessageAdapter
import com.example.mqtt_tutorial.R
import com.example.mqtt_tutorial.printToast
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    // ViewModel:
    private lateinit var viewModel: MainViewModel

    // RecyclerView Stuff:
    private lateinit var rv:RecyclerView
    private lateinit var adapter: MessageAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        second_btn_save.setOnClickListener {
            topicHandling()
        }

        second_btn_send.setOnClickListener {
            TODO("Get the Topic from top and the Message from the Bottom and publish via the viewModel...")

        }


        viewModel = ViewModelProvider(requireActivity(),defaultViewModelProviderFactory).get(
            MainViewModel::class.java)

        TODO("Create an Observer for the MessageList")


    }

    private fun initRecyclerView()
    {
        TODO("Init the RecyclerView...")

    }

    private fun topicHandling()
    {
        TODO("Handle the Logic to subscribe to a Topic")

    }
}