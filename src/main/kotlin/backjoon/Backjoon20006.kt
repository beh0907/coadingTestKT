package backjoon

import java.util.LinkedList

//랭킹전 대기열
//https://www.acmicpc.net/problem/20006
fun main() = with(System.`in`.bufferedReader()) {
    val (p, m) = readLine().split(' ').map(String::toInt)
    val rooms = LinkedList<LinkedList<User>>()
    var result = StringBuilder()

    repeat(p) {
        val str = readLine().split(' ')
        val user = User(str[0].toInt(), str[1])

        for (room in rooms) {
            val range = room.first().level.let { it - 10 .. it + 10 }
            if (user.level in range && room.size < m) {
                room.add(user)
                return@repeat
            }
        }

        val room = LinkedList<User>().apply { add(user) }
        rooms.add(room)
    }

    for (room in rooms) {
        if (room.size == m) result.append("Started!\n")
        else result.append("Waiting!\n")

        for (user in room.sortedBy { it.id }) result.append("${user.level} ${user.id}\n")
    }

    println(result)
}

data class User(
    val level: Int,
    val id: String
)
