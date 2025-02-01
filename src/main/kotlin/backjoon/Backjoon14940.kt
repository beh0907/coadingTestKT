package backjoon

import java.io.BufferedWriter
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

//쉬운 최단거리
//https://www.acmicpc.net/problem/14940
fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map(String::toInt)
    val bw = BufferedWriter(System.out.bufferedWriter())

    val map = Array(N) { IntArray(M) { 0 } }
    var start = 0 to 0

    repeat(N) { i ->
        val tokens = StringTokenizer(readLine())

        repeat(M) { j ->
            val token = tokens.nextToken().toInt()

            when (token) {
                2 -> {
                    start = i to j
                    map[i][j] = 0
                }

                else -> {
                    map[i][j] = token
                }
            }
        }
    }

    val result = bfs(map, start)
    repeat(N) { i ->
        repeat(M) { j ->

            val weight = if (map[i][j] == 0) 0
            else if (map[i][j] == 1 && result[i][j] == Int.MAX_VALUE) -1
            else result[i][j]

            bw.write("$weight ")
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
}

fun bfs(map: Array<IntArray>, start: Pair<Int, Int>): Array<IntArray> {
    val dirX = arrayOf(0, 0, 1, -1)
    val dirY = arrayOf(1, -1, 0, 0)

    val result = Array(map.size) { IntArray(map[0].size) { Int.MAX_VALUE } }
    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()

    result[start.first][start.second] = 0
    queue.add(Triple(start.first, start.second, 0))

    while (queue.isNotEmpty()) {
        val node = queue.poll()

        repeat(4) {
            val newX = node.first + dirX[it]
            val newY = node.second + dirY[it]

            if (newX !in result.indices || newY !in result[0].indices) return@repeat

            if (map[newX][newY] == 0) return@repeat

            val newWeight = node.third + map[newX][newY]

            if (newWeight >= result[newX][newY]) return@repeat

            result[newX][newY] = newWeight
            queue.add(Triple(newX, newY, newWeight))
        }
    }

    return result
}
