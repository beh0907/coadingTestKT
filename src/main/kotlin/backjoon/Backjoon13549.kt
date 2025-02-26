package backjoon

import java.util.PriorityQueue

//숨바꼭질 3
//https://www.acmicpc.net/problem/13549
fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(' ').map { it.toInt() }

    println(bfs(N, K))
}

private fun bfs(start: Int, goal: Int): Int {
    if (start == goal) return 0

    val queue = PriorityQueue<Node13549>()
    queue.add(Node13549(start, 0))

    // 방문 배열 - 방문했을 때의 최소 시간을 저장
    val dist = IntArray(100001) { Int.MAX_VALUE }
    dist[start] = 0

    while (queue.isNotEmpty()) {
        val current = queue.poll()
        val pos = current.pos
        val time = current.time

        // 현재 위치에 더 적은 시간으로 도달한 경우가 있다면 스킵
        if (dist[pos] < time) continue

        // 목표 도달 확인
        if (pos == goal) return time

        // 순간이동 (2*X, 시간 0)
        if (pos * 2 <= 100000 && time < dist[pos * 2]) {
            dist[pos * 2] = time
            queue.add(Node13549(pos * 2, time))
        }

        // 걷기 (X+1, 시간 1)
        if (pos + 1 <= 100000 && time + 1 < dist[pos + 1]) {
            dist[pos + 1] = time + 1
            queue.add(Node13549(pos + 1, time + 1))
        }

        // 걷기 (X-1, 시간 1)
        if (pos - 1 >= 0 && time + 1 < dist[pos - 1]) {
            dist[pos - 1] = time + 1
            queue.add(Node13549(pos - 1, time + 1))
        }
    }

    return -1
}

data class Node13549(
    val pos: Int = 0,
    val time: Int = 0
) : Comparable<Node13549> {
    override fun compareTo(other: Node13549): Int {
        return time.compareTo(other.time)
    }
}
