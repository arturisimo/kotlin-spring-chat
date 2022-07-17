package com.example.kotlin.chat.extensions

import com.example.kotlin.chat.dto.MessageVM
import com.example.kotlin.chat.dto.UserVM
import com.example.kotlin.chat.entity.ContentType
import com.example.kotlin.chat.entity.Message
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import java.net.URL

fun Message.toDto(): MessageVM = MessageVM(contentType.render(content), UserVM(username, URL(userAvatarImageLink)), sent, id)

fun MessageVM.toEntity(contentType: ContentType = ContentType.MARKDOWN): Message =
    Message(content, contentType, sent, user.name, user.avatarImageLink.toString(), id)

fun ContentType.render(content: String): String = when (this) {
    ContentType.PLAIN -> content
    ContentType.MARKDOWN -> {
        val flavour = CommonMarkFlavourDescriptor()
        HtmlGenerator(content, MarkdownParser(flavour).buildMarkdownTreeFromString(content), flavour).generateHtml()
    }
}
