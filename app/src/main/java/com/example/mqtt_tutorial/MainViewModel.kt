/*
 * Copyright (c) 2021 CodingWithChris - All Rights Reserved
 */

package com.example.mqtt_tutorial

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mqtt_tutorial.Constants.LOGTAG
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MainViewModel(application: Application): AndroidViewModel(application)
{
    private val context: Context = application.applicationContext
    // MQTT :
    private lateinit var mqttAndroidClient:MqttAndroidClient

    // LiveData:
    private var messages:MutableLiveData<ArrayList<Message>> = MutableLiveData(ArrayList())




    // Methode to connect MQTT Client:
    fun initClient(serverUri:String,clientID:String,callback:(status:Boolean)->Unit)
    {
        Log.d(LOGTAG,"serverUri = $serverUri \t clientID = $clientID")
        mqttAndroidClient = MqttAndroidClient(context,serverUri,clientID)
        callback(true)

    }

    fun connectClient(username:String, pwd:String,callback: (status: Boolean) -> Unit)
    {
        mqttAndroidClient.setCallback(object:MqttCallbackExtended{
            override fun connectionLost(cause: Throwable?) {
                //
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                Log.d(LOGTAG,"Topic = $topic \t Content Message = $message")

                addNewMessageToList(message.toString(),topic!!,getStringFromDate())

            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                //
            }

            override fun connectComplete(reconnect: Boolean, serverURI: String?) {
                //
            }

        })
        val options = MqttConnectOptions()
        options.userName = username
        options.password = pwd.toCharArray()
        try {
            mqttAndroidClient.connect(options,null,object:IMqttActionListener{
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    callback(true)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    exception!!.printStackTrace()
                    callback(false)
                }

            })
        } catch(e:MqttException)
        {
            e.printStackTrace()
        }
    }


    fun subscribe(topic:String,qos:Int = 1,callback:(status:Boolean)->Unit)
    {
        try{
            mqttAndroidClient.subscribe(topic,qos,null,object:IMqttActionListener{
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    callback(true)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    callback(false)
                }

            })
        } catch(e:MqttException)
        {
            e.printStackTrace()
        }
    }


    fun publish(topic:String,msg:String,qos:Int = 1,callback:(status:Boolean)->Unit)
    {
        try {
            val message = MqttMessage()
            message.payload = msg.toByteArray()
            message.qos = qos
            message.isRetained = false
            mqttAndroidClient.publish(topic,message,null,object :IMqttActionListener{
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    callback(true)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    callback(false)
                }

            })
        } catch(e:MqttException)
        {
            e.printStackTrace()
        }
    }

    fun addNewMessageToList(msg:String,topic:String,time:String)
    {
        val msg = Message(msg,topic,time)
        Log.d(LOGTAG,"MainViewModel - addNewMessageToList - $msg")
        var localMessages = messages.value!!
        localMessages.add(0,msg)

        messages.value = localMessages
    }

    fun getMessages():LiveData<ArrayList<Message>> = messages
}