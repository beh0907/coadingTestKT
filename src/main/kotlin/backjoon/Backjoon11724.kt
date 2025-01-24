package backjoon

import java.util.LinkedList
import java.util.Queue

//연결 요소의 개수
//https://www.acmicpc.net/problem/11724
fun main() = with(System.`in`.bufferedReader()) {
    //지도 사이즈를 얻어온다
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { ArrayList<Int>() }
    val visited = BooleanArray(n)

    repeat(m) {
        val (start, end) = readln().split(" ").map { it.toInt() - 1 }

        //연결이므로 양방향
        graph[start].add(end)
        graph[end].add(start)
    }


    var result = 0
    repeat(n) {
        if (visited[it]) return@repeat

        //탐색 시작
        bfs(graph, visited, it)

        //연결요소++
        result++
    }
    println(result)
}

private fun bfs(graph: Array<ArrayList<Int>>, visited: BooleanArray, start: Int) {
    val queue:Queue<Int> = LinkedList()
    queue.offer(start)

    //방문 처리
    visited[start] = true

    while (queue.isNotEmpty()) {
        val pos = queue.poll()

        for (target in graph[pos]) {
            //방문이력이 있다면 continue
            if (visited[target]) continue

            //방문 처리
            visited[target] = true

            //큐 삽입
            queue.offer(target)
        }
    }
}
