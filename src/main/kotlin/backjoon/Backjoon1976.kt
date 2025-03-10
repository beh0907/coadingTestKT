package backjoon

import java.util.LinkedList
import java.util.StringTokenizer

//여행 가자
//https://www.acmicpc.net/problem/1976
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val M = readLine().toInt()

    val graph = Array(N) {
        val tokens = StringTokenizer(readLine())
        BooleanArray(N) {
            tokens.nextToken() == "1"
        }
    }
    val targets = HashSet<Int>()
    readLine().split(" ").map { targets.add(it.toInt() - 1) }

    fun bfs(graph: Array<BooleanArray>, targets: HashSet<Int>): Boolean {
        val start = targets.first()
        targets.remove(start)

        val queue = LinkedList<Int>()
        queue.offer(start)

        val visited = BooleanArray(graph.size)
        visited[start] = true

        while (queue.isNotEmpty()) {
            val node = queue.poll()

            //모두 방문했다면 완료
            if (targets.isEmpty()) return true

            for (i in graph.indices) {
                //연결되지 않은 도시라면 continue
                if (!graph[node][i]) continue

                //이미 방문한 도시라면 continue
                if (visited[i]) continue

                //목적지라면 리턴
                if (targets.contains(i)) targets.remove(i)

                visited[i] = true
                queue.offer(i)
            }
        }

        return false
    }


    println(
        if (bfs(graph, targets)) "YES" else "NO"
    )
}


