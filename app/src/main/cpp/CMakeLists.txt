# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)

# Declares and names the project.

project("rpi4gpio")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        rpi4gpio

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        Rpi4Gpio.cpp

        /media/jijith/SSD/AndroidAutomotive/vendor/demo/hardware/interfaces/rpi4gpio/1.0/default/Android.bp
        /media/jijith/SSD/AndroidAutomotive/vendor/demo/hardware/interfaces/rpi4gpio/1.0/default/Rpi4gpio.cpp
        /media/jijith/SSD/AndroidAutomotive/vendor/demo/hardware/interfaces/rpi4gpio/1.0/default/Rpi4gpio.h
        /media/jijith/SSD/AndroidAutomotive/vendor/demo/hardware/interfaces/rpi4gpio/1.0/default/service.cpp
        )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        rpi4gpio

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})

include_directories(
        /media/jijith/SSD/AndroidAutomotive/out/soong/.intermediates/vendor/demo/hardware/interfaces/rpi4gpio/1.0/vendor.demo.rpi4gpio@1.0_genc++_headers/gen/
        /media/jijith/SSD/AndroidAutomotive/frameworks/native/include/
        /media/jijith/SSD/AndroidAutomotive/system/libhidl/base/include/
        /media/jijith/SSD/AndroidAutomotive/system/core/libutils/include
        /media/jijith/SSD/AndroidAutomotive/system/libhidl/transport/include
        /media/jijith/SSD/AndroidAutomotive/system/libhidl/base/include
        /media/jijith/SSD/AndroidAutomotive/out/soong/.intermediates/system/libhidl/transport/base/1.0/android.hidl.base@1.0_genc++_headers/gen
        /media/jijith/SSD/AndroidAutomotive/system/core/libcutils/include/
        /media/jijith/SSD/AndroidAutomotive/system/libhwbinder/include/
        /media/jijith/SSD/AndroidAutomotive/system/libhidl/base/include/
        /media/jijith/SSD/AndroidAutomotive/system/core/liblog/include/
        /media/jijith/SSD/AndroidAutomotive/out/soong/.intermediates/system/libhidl/transport/manager/1.0/android.hidl.manager@1.0_genc++_headers/gen
        /media/jijith/SSD/AndroidAutomotive/out/soong/.intermediates/hardware/interfaces/automotive/vehicle/2.0/android.hardware.automotive.vehicle@2.0_genc++_headers/gen/
        /media/jijith/SSD/AndroidAutomotive/out/soong/.intermediates/vendor/demo/hardware/interfaces/vehiclevendor/1.0/vendor.demo.vehiclevendor@1.0_genc++_headers/gen/
)