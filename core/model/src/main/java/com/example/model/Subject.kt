package com.example.model

data class Subject(
    val id: Int = 0,
    val name: String = "",
    val place: String = "",
    val teacher: String = "",
    val notificationOn: Boolean = false
)

data class SubjectNotificationDecorator(val subject: Subject): NotificationDecorator{
    override fun selfId(): Int = subject.id

    override fun notificationTitle(): String {
        return "Sua aula de ${subject.name} já vai começar!"
    }

    override fun notificationBody(): String {
        return "${subject.name} começa já já"
    }
}
