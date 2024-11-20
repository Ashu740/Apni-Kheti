package com.example.apnikheti.features.geminichatbot

import android.graphics.Bitmap
import com.example.apnikheti.features.geminichatbot.data.Chat

data class ChatState(
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)
