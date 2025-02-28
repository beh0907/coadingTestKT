package backjoon

import java.util.LinkedList
import java.util.StringTokenizer

val dirX = listOf<Int>(-1, -1, 0, 1, 1, 1, 0, -1)
val dirY = listOf<Int>(0, 1, 1, 1, 0, -1, -1, -1)

//현수막
//https://www.acmicpc.net/problem/14716
fun main() = with(System.`in`.bufferedReader()) {
    val (M, N) = readLine().split(' ').map(String::toInt)
    val map = Array(M) { IntArray(N) }
    var result = 0

    repeat(M) { i ->
        val tokens = StringTokenizer(readLine(), " ")

        repeat(N) { j ->
            map[i][j] = tokens.nextToken().toInt()
        }
    }

    repeat(M) { i ->
        repeat(N) { j ->
            if (map[i][j] == 1) {
                result++
                bfs14716(map, i to j)
            }
        }
    }

    println(result)
}

fun bfs14716(map: Array<IntArray>, start: Pair<Int, Int>) {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.offer(start)

    map[start.first][start.second] = 0

    while (queue.isNotEmpty()) {
        val node = queue.poll()

        repeat(8) {
            val newX = node.first + dirX[it]
            val newY = node.second + dirY[it]

            if (newX !in map.indices || newY !in map[0].indices) return@repeat

            if (map[newX][newY] == 0) return@repeat

            map[newX][newY] = 0
            queue.offer(newX to newY)
        }
    }
}
