package backjoon

//임스와 함께하는 미니게임
//https://www.acmicpc.net/problem/25757
fun main() = with(System.`in`.bufferedReader()) {
    val line = readLine().split(" ")
    val N = line[0].toInt()
    val GAME = when (line[1]) {
        "Y" -> 2
        "F" -> 3
        else -> 4 // O
    }

    //유저 할당
    val users = mutableSetOf<String>()
    repeat(N) { users.add(readLine()) }

    println(users.size / (GAME - 1))
}
