package backjoon

import java.io.BufferedWriter

private lateinit var graph: IntArray
private lateinit var visited: BooleanArray
private lateinit var finished: BooleanArray
private val result = mutableSetOf<Int>()

//숫자고르기
//https://www.acmicpc.net/problem/2668
fun main() = with(System.`in`.bufferedReader()) {
    val bw = BufferedWriter(System.out.bufferedWriter())
    val N = readLine().toInt()
    graph = IntArray(N + 1)
    visited = BooleanArray(N + 1)
    finished = BooleanArray(N + 1)

    // 1부터 N까지 입력 받기
    for (i in 1..N) {
        val value = readLine().toInt()
        graph[i] = value
    }

    // 모든 노드에 대해 DFS 실행
    for (i in 1..N) {
        if (!visited[i]) {
            dfs(i)
        }
    }

    bw.write("${result.size}\n")
    result.sorted().forEach { bw.write("$it\n") }

    bw.flush()
    bw.close()
}

// 단순화된 DFS 함수
private fun dfs(current: Int) {
    visited[current] = true
    val next = graph[current]

    if (!visited[next]) {
        dfs(next)
    } else if (!finished[next]) {
        // 방문했지만 아직 완료되지 않은 노드 = 현재 DFS 경로상의 노드 = 사이클 발견
        var node = next
        while (true) {
            result.add(node)
            node = graph[node]
            if (node == next) break
        }
    }

    // 현재 노드 탐색 완료 표시
    finished[current] = true
}
