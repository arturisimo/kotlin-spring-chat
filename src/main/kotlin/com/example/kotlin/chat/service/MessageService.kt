package com.example.kotlin.chat.service

import com.example.kotlin.chat.dto.MessageVM

interface MessageService {

    fun latest(): List<MessageVM>

    fun after(messageId: String): List<MessageVM>

    fun post(message: MessageVM)
}
