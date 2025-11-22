package at.uastw.contactsapp.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {

    val duration = measureTimeMillis {
//        repeat(5000){
//            println("a")
//        }

        // runBlocking: start a coroutine for testing
        runBlocking {
            // a suspend function can be called inside a suspend function or a coroutine
            printWeatherReport()
        }
    }

    println("The program took $duration ms")
}

suspend fun printWeatherReport() {

    // print the thread name
    println("1: Thread:" + Thread.currentThread().name)

    // allow one or multiple subcoroutines
    coroutineScope {

        println("2: Thread:" + Thread.currentThread().name)

        // async: start a subcoroutine, but allowing to get the return value via await()
        val weatherDeferred = async(Dispatchers.IO){
            println("3: Thread:" + Thread.currentThread().name)
            getWeather()
        }
        val temperatureDeferred = async{
            println("4: Thread:" + Thread.currentThread().name)
            getTemperature()
        }
        val weather = weatherDeferred.await()
        val temperature = temperatureDeferred.await()

        // launch: start a subcoroutine, but no return value
        launch{
            delay(400)
            println("hiho")
        }

        println("The weather is $weather and the temperature is $temperature")
    }
}

suspend fun getWeather(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(2000)
    return "12 degrees"
}