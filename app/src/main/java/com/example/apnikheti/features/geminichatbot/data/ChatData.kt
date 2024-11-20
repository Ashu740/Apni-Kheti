package com.example.apnikheti.features.geminichatbot.data

import android.graphics.Bitmap
import com.example.apnikheti.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ChatData {
    private val apiKey = BuildConfig.API_KEY

    suspend fun getResponse(prompt: String): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-pro-002", apiKey = apiKey
        )

        try{
            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(prompt)
            }
            return Chat(prompt = response.text?: "error",
                bitmap = null,
                isFromUser = false)
        }catch (e : ResponseStoppedException) {
            return Chat(prompt = e.message?: "error",
                bitmap = null,
                isFromUser = false)
        }
    }

    suspend fun getResponseBitmap(prompt: String, bitmap: Bitmap): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-pro-002", apiKey = apiKey
        )

        try{
            val inputContent = content {
                image(bitmap)
                text(prompt)
            }
            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(inputContent)
            }
            return Chat(prompt = response.text?: "error",
                bitmap = null,
                isFromUser = false)
        }catch (e : Exception) {
            return Chat(prompt = e.message?: "error",
                bitmap = null,
                isFromUser = false)
        }
    }
}