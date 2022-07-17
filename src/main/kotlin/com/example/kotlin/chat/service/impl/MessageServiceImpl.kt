package com.example.kotlin.chat.service.impl

import com.example.kotlin.chat.dto.MessageVM
import com.example.kotlin.chat.repository.MessageRepository
import com.example.kotlin.chat.service.MessageService
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import com.example.kotlin.chat.extensions.toDto
import com.example.kotlin.chat.extensions.toEntity

@Service
@Primary
class MessageServiceImpl(val messageRepository: MessageRepository) : MessageService {

    override fun latest(): List<MessageVM> = messageRepository.findLatest().map { it.toDto() }

    override fun after(lastMessageId: String): List<MessageVM> =
        messageRepository.findLatest(lastMessageId).map { it.toDto() }

    override fun post(message: MessageVM) {
        messageRepository.save(message.toEntity())
    }

}
