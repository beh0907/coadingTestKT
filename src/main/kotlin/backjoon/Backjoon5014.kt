package backjoon

import java.util.*

//스타트링크
//https://www.acmicpc.net/problem/5014
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    //F = 건물 높이, S = 시작 층, G = 목표 층, U = 한번에 올라가는 층, D = 한번에 내려가는 층
    val (F, S, G, U, D) = readln().split(" ").map { it.toInt() }

    println(bfs(F, S - 1, G - 1, U, D))
}

private fun bfs(floor: Int, startFloor: Int, goalFloor: Int, up: Int, down: Int): String {
    //출발점 방문 처리
    val visited = BooleanArray(floor)
    visited[startFloor] = true

    //위치와 이동횟수
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(Pair(startFloor, 0))

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        if (current.first == goalFloor) return current.second.toString()

        val upStair = current.first + up
        val downStair = current.first - down

        //올라갈 위치가 건물 내부 층이며, 방문 이력이 없다면
        if (upStair in 0..<floor && !visited[upStair]) {
            //방문처리
            visited[upStair] = true

            //큐에 정보 삽입
            queue.offer(Pair(upStair, current.second + 1))
        }


        //내려갈 위치가 건물 내부 층이며, 방문 이력이 없다면
        if (downStair in 0..<floor && !visited[downStair]) {
            //방문처리
            visited[downStair] = true

            //큐에 정보 삽입
            queue.offer(Pair(downStair, current.second + 1))
        }
    }

    return "use the stairs"
}
