package com.sample.rpi4gpio

class Rpi4Native {

    external fun openDev(): Int
    external fun closeDev(): Int
    external fun devOn(): Int
    external fun devOff(): Int

    companion object {
        // Used to load the 'rpi4gpio' library on application startup.
        init {
            System.loadLibrary("rpi4gpio")
        }
    }
}