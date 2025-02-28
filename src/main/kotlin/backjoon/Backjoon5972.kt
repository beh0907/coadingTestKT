package backjoon

import java.util.PriorityQueue

//택배 배송
//https://www.acmicpc.net/problem/5972
fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(' ').map { it.toInt() }

    val graph = Array(N + 1) { mutableListOf<Pair<Int, Int>>() }

    repeat(M) {
        val (start, end, weight) = readLine().split(' ').map { it.toInt() }
        graph[start].add(Pair(end, weight))
        graph[end].add(Pair(start, weight))
    }

    println(dijkstra5972(graph, 1, N))
}

fun dijkstra5972(graph: Array<MutableList<Pair<Int, Int>>>, start: Int, end: Int): Int {
    val distances = IntArray(graph.size) { Int.MAX_VALUE }

    // 시작 노드 초기화
    distances[start] = 0

    // 우선순위 큐 (노드 번호, 거리)
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    queue.add(Pair(start, 0))

    while (queue.isNotEmpty()) {
        val (current, dist) = queue.poll()

        // 현재 거리가 이미 알려진 최단 거리보다 크면 스킵
        if (dist > distances[current]) continue

        // 목적지에 도달했다면 종료
        if (current == end) break

        // 인접 노드 탐색 (인접 리스트 방식)
        for ((next, weight) in graph[current]) {
            val newDist = dist + weight

            // 더 짧은 경로를 발견했다면 갱신
            if (newDist < distances[next]) {
                distances[next] = newDist
                queue.add(Pair(next, newDist))
            }
        }
    }

    return distances[end]
}
