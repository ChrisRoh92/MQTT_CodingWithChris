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
    // TODO("Create an global variable for an MqttAndroidClient")
    private lateinit var mqttAndroidClient:MqttAndroidClient

    // LiveData:
    // TODO("Create MutableLiveData<ArrayList<Message>>, where Message is a Dataclass")







    // TODO("Create a Method, to init a Client")

    // TODO("Create a method, to connect the Client to the Broker")

    // TODO("Create a method, to subscribe to a topic")

    // TODO("Create a method to publish a Message to a topic")

    // TODO("Create a method, to add a received Message to the MutableliveData<ArrayList<Message>>

    // TODO("Create a Getter Method for the MutableliveData<ArrayList<Message>>


}