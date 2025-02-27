package backjoon

import java.util.LinkedList
import java.util.Queue

//뱀과 사다리 게임
//https://www.acmicpc.net/problem/16928
fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(' ').map { it.toInt() }
    var map = IntArray(101)

    repeat(N + M) {
        val (start, end) = readLine().split(' ').map { it.toInt() }
        map[start] = end - start
    }

    println(bfs(map))
}

private fun bfs(map: IntArray): Int {
    val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
    queue.add(1 to 0)

    var visited = BooleanArray(101)
    visited[1] = true

    while (queue.isNotEmpty()) {
        val (pos, cnt) = queue.poll()

        for (i in 1..6) {
            var newPos = pos + i

            if (newPos == 100) return cnt + 1

            if (newPos !in map.indices) break
            if (visited[newPos]) continue

            visited[newPos] = true
            if (map[newPos] != 0) {
                newPos += map[newPos]
                visited[newPos] = true
            }

            queue.add(newPos to cnt + 1)
        }
    }

    return -1
}
