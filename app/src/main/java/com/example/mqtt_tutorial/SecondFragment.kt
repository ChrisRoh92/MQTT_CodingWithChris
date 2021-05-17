package com.example.mqtt_tutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            if(!second_et_publish.text.isNullOrEmpty() && !second_et_topic.text.isNullOrEmpty())
            {
                viewModel.publish(second_et_topic.text.toString(),second_et_publish.text.toString()){status ->
                    if(status)
                        printToast(requireContext(),"Publish war erfolgreich")
                    else
                    {
                        printToast(requireContext(),"Publish war NICHT erfolgreich")
                    }

                }
            }
        }


        viewModel = ViewModelProvider(requireActivity(),defaultViewModelProviderFactory).get(MainViewModel::class.java)
        viewModel.getMessages().observe(viewLifecycleOwner, Observer { data ->
            adapter.updateContent(data)
            rv.scrollToPosition(0)
        })

    }

    private fun initRecyclerView()
    {
        rv = second_rv
        adapter = MessageAdapter(ArrayList())
        val manager = LinearLayoutManager(rv.context,RecyclerView.VERTICAL,false)
        rv.layoutManager = manager
        rv.adapter = adapter
    }

    private fun topicHandling()
    {
        val topic = second_et_topic.text.toString()
        if(!topic.isNullOrEmpty())
        viewModel.subscribe(topic){ status ->
            if(status)
                printToast(requireContext(),"Subscription war erfolgreich")
            else
            {
                printToast(requireContext(),"Subscription war NICHT erfolgreich")
            }

        }
    }
}