package backjoon

import java.util.LinkedList
import java.util.StringTokenizer

//여행 가자
//https://www.acmicpc.net/problem/1976
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val M = readLine().toInt()
    var result = "YES"

    val graph = Array(N) {
        val tokens = StringTokenizer(readLine())
        BooleanArray(N) {
            tokens.nextToken() == "1"
        }
    }

    fun bfs(start: Int, end: Int, graph:Array<BooleanArray>): Boolean {
        val queue = LinkedList<Int>()
        queue.offer(start)

        val visited = BooleanArray(graph.size)
        visited[start] = true

        while (queue.isNotEmpty()) {
            val node = queue.poll()

            for (i in graph.indices) {
                //연결되지 않은 도시라면 continue
                if (!graph[node][i]) continue

                //이미 방문한 도시라면 continue
                if (visited[i]) continue

                //목적지라면 리턴
                if (i == end) return true

                visited[i] = true
                queue.offer(i)
            }
        }

        return false
    }

    val targets = readLine().split(" ").map { it.toInt() - 1 }
    for (i in 1 until M) {
        //같은 도시라면 이미 방문
        if (targets[i - 1] == targets[i]) continue

        if (!bfs(targets[i - 1], targets[i], graph)) {
            result = "NO"
            break
        }
    }


    println(result)
}

