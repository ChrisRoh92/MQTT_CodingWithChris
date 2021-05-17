package com.example.mqtt_tutorial

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallback

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    // ViewModel anlegen:
    private lateinit var viewModel:MainViewModel





    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity(),defaultViewModelProviderFactory).get(MainViewModel::class.java)

//        val etName:EditText = first_et_name
//        val etPwd:EditText = first_et_pwd
        first_btn_login.setOnClickListener {
            // Davor prüfen, ob überall Eingaben gemacht wurden...
            if(checkInputs())
                connectToBroker()
            else
                printToast("Bitte alle Felder ausfüllen....")
        }

    }

    // Methode übernimmt Daten aus den Feldern und versucht Verbindung aufzubauen..
    private fun connectToBroker()
    {
        showViews(first_pb,first_tv_pb)
        blockInputs()
        // Get IP-Adresse:
        var address = "tcp://" + first_et_ip.text.toString()
        val port = first_et_port.text.toString()
        if(port != "")
            address = "$address:$port"
        val clientID = "ExampleAndroidClient"
        // Anlegen starten:
        viewModel.initClient(address,clientID){ status ->
            if(status)
            {
                val pwd = first_et_pwd.text.toString()
                val user = first_et_name.text.toString()
                // Connection:
                viewModel.connectClient(user,pwd){ status ->
                    hideViews(first_pb,first_tv_pb)
                    if(status)
                    {
                        unblockInputs()
                        findNavController().navigate(R.id.action_first_secpnd)
                    }
                    else
                    {
                        unblockInputs()
                        printToast("Verbindung konnte nicht hergestellt werden...")
                    }

                }
            }
            else
            {
                unblockInputs()
            }
        }


    }


    private fun blockInputs()
    {
        first_et_name.isEnabled = false
        first_et_pwd.isEnabled = false
        first_et_port.isEnabled = false
        first_et_ip.isEnabled = false
        first_btn_login.isEnabled = false
    }

    private fun unblockInputs()
    {
        first_et_name.isEnabled = true
        first_et_pwd.isEnabled = true
        first_et_port.isEnabled = true
        first_et_ip.isEnabled = true
        first_btn_login.isEnabled = true
    }



    private fun checkInputs():Boolean
    {
        // Prüfen ob überall was steht...
        var status = true
        if(first_et_name.text.isNullOrEmpty())
            status =  false
        else if(first_et_pwd.text.isNullOrEmpty())
            status =  false
        else if(first_et_port.text.isNullOrEmpty())
            status =  false
        else if(first_et_ip.text.isNullOrEmpty())
            status =  false
        return status
    }



    private fun showInput()
    {
        if(!first_et_name.text.isNullOrEmpty() && !first_et_pwd.text.isNullOrEmpty())
        {
//            printToast("${first_et_name.text}   ${first_et_pwd.text}")
            showOnCustomDialog(first_et_name.text.toString(),first_et_pwd.text.toString())
        }
        else
        {
            printToast("Bitte beide Felder ausfüllen")
        }
    }

    private fun showOnCustomDialog(user:String,pwd:String)
    {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_simple_input)

    }

    /*private fun showOnCustomAlertDialog(user:String,pwd:String)
    {
        val inflater = layoutInflater
        val builder = AlertDialog.Builder(requireContext()).apply {

            setView(inflater.inflate(R.layout.dialog_simple_input,null)).apply {
                setTitle("Ergebnis")
                setPositiveButton("Verstanden") { dialogInterface, i ->
                    dialogInterface.cancel()
                }
                setPositiveButton("Verstanden") { dialogInterface, i ->
                    dialogInterface.cancel()
                }
                val et = this.
            }





        }.create()
        builder.show()
    }*/

    private fun printToast(msg:String)
    {
        Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).apply {
            setGravity(Gravity.CENTER,0,0)
        }.show()
    }







    private fun hideViews(vararg views: View)
    {
        for(i in views)
            i.visibility = View.GONE
    }

    private fun showViews(vararg views: View)
    {
        for(i in views)
            i.visibility = View.VISIBLE
    }
}