package backjoon

import java.util.PriorityQueue

//파티
//https://www.acmicpc.net/problem/1238
fun main() = with(System.`in`.bufferedReader()) {
    data class Node(val vertex: Int, val distance: Int) : Comparable<Node> {
        override fun compareTo(other: Node): Int = this.distance - other.distance
    }

    fun dijkstra(graph: Array<IntArray>, start: Int): IntArray {
        val distances = IntArray(graph.size) { Int.MAX_VALUE }
        distances[start] = 0

        val queue = PriorityQueue<Node>()
        queue.offer(Node(start, 0))

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val currentVertex = current.vertex
            val currentDistance = current.distance

            // 이미 처리된 노드라면 스킵
            if (distances[currentVertex] < currentDistance) continue

            // 현재 노드의 인접 노드들 확인
            for (next in 0 until graph.size) {
                // 연결되지 않은 노드 스킵
                if (graph[currentVertex][next] == Int.MAX_VALUE) continue

                val newDistance = currentDistance + graph[currentVertex][next]
                // 더 짧은 경로를 찾았다면 갱신
                if (newDistance < distances[next]) {
                    distances[next] = newDistance
                    queue.offer(Node(next, newDistance))
                }
            }
        }

        return distances
    }

    val (N, M, X) = readLine().split(" ").map(String::toInt)
    val graph = Array(N) { IntArray(N) { Int.MAX_VALUE } }
    val reverseGraph = Array(N) { IntArray(N) { Int.MAX_VALUE } }

    // 자기 자신으로의 거리는 0
    for (i in 0 until N) {
        graph[i][i] = 0
        reverseGraph[i][i] = 0
    }

    repeat(M) {
        val (start, end, time) = readLine().split(" ").map(String::toInt)
        graph[start - 1][end - 1] = time
        // 역방향 그래프도 만들어둠
        reverseGraph[end - 1][start - 1] = time
    }

    // X에서 각 마을로 가는 최단 경로 (돌아오는 경로)
    val fromX = dijkstra(graph, X - 1)

    // 각 마을에서 X로 가는 최단 경로 (역방향 그래프를 사용)
    val toX = dijkstra(reverseGraph, X - 1)

    // 각 마을에서 왕복하는 최대 시간 계산
    var result = 0
    for (i in 0 until N) {
        // i에서 X로 가고, X에서 i로 오는 왕복 거리
        val roundTrip = toX[i] + fromX[i]
        result = maxOf(result, roundTrip)
    }

    println(result)
}
