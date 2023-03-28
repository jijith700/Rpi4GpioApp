package com.sample.rpi4gpio

import android.car.Car
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.os.Bundle
import android.util.ArraySet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import vendor.demo.vehiclevendor.V1_0.VehicleProperty


class MainActivity : AppCompatActivity(), Car.CarServiceLifecycleListener,
    CarPropertyManager.CarPropertyEventCallback {

    private val TAG = "LedControl"
    private var ledNative: Rpi4Native? = null
    private var car: Car? = null
    private lateinit var carPropertyManager: CarPropertyManager
    private lateinit var btnLed: Button
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate")
        btnLed = findViewById(R.id.btnLed)
        tvStatus = findViewById(R.id.tvStatus)
        ledNative = Rpi4Native()
        ledNative!!.openDev()
        car = Car.createCar(applicationContext, null, 500, this)
        registerCar();

        btnLed.setOnClickListener(View.OnClickListener {
            if (btnLed.text == "OFF") {
                on()
            } else {
                off()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        ledNative!!.closeDev()
        Log.d(TAG, "onDestroy")
    }

    private fun registerCar() {
        carPropertyManager = car?.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager
        val propertyIds = ArraySet<Int>()
        propertyIds.add(VehicleProperty.RPI4_GPIO)
        val property = carPropertyManager.getIntProperty(VehicleProperty.RPI4_GPIO, 0)
        Log.i(TAG, "registerCar: property= $property")
        carPropertyManager.registerCallback(this, VehicleProperty.RPI4_GPIO, 0.0f)
    }

    override fun onLifecycleChanged(car: Car?, ready: Boolean) {
        Log.i(TAG, "onLifecycleChanged: ready= $ready")
        if (ready) {
            this.car = car
            registerCar()
        } else {
            this.car = null
        }
    }

    override fun onChangeEvent(carPropertyValue: CarPropertyValue<*>?) {
        Log.i(TAG, "onChangeEvent: carPropertyValue= ${carPropertyValue?.propertyId}")
        if (carPropertyValue?.propertyId == VehicleProperty.RPI4_GPIO) {
            val enabled = carPropertyValue.value
            Log.i(TAG, "onChangeEvent: enabled=$enabled")
            tvStatus.setText("enabled=$enabled")
            if (enabled == 1) {
                on()
            } else {
                off()
            }
        }
    }

    override fun onErrorEvent(p0: Int, p1: Int) {
        Log.i(TAG, "onErrorEvent: p0=$p0 p1=$p1")
    }

    private fun on() {
        Log.d(TAG, "turn on led")
        btnLed.text = "ON"
        ledNative!!.devOn()
    }

    private fun off() {
        Log.d(TAG, "turn off led")
        btnLed.text = "OFF"
        ledNative!!.devOff()
    }
}