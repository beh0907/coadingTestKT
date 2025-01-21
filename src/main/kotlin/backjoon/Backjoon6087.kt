package backjoon

import java.util.*
import kotlin.math.abs

//레이저 통신
//https://www.acmicpc.net/problem/6087
private val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val (y, x) = readln().trim().split(" ").map { it.toInt() }
    val map = Array(x) { CharArray(y) }
    val targets = ArrayList<Node>()

    repeat(x) { i ->
        map[i] = readln().toCharArray()

        map[i].forEachIndexed { j, ch ->
            // 2개의 C중 아무거나 사용해도 상관없다
            if (ch == 'C') {
                targets.add(Node(i, j, -5, -1))
            }
        }
    }

    println(bfs(targets[0], targets[1], map))
}

private fun bfs(start: Node, goal: Node, map: Array<CharArray>): Int {
    val queue: PriorityQueue<Node> = PriorityQueue(compareBy { it.move }) // 우선순위 큐 선언

    // 방문 정보 초기화
    val visited = Array(map.size) { IntArray(map[0].size) { Int.MAX_VALUE } }

    // 시작지점 4방향을 큐에 삽입한다
    for (i in dirs.indices) queue.add(Node(start.x, start.y, i, 0))

    while (queue.isNotEmpty()) {
        val (x, y, dir, move) = queue.poll()

        //더 적은 값의 배치 정보가 있다면 continue
        if (move > visited[x][y]) continue

        // 목표를 찾았다면 결과 정보를 리턴한다
        if (x == goal.x && y == goal.y) {
            return move
        }

        for (i in dirs.indices) {
            val dx = x + dirs[i].first
            val dy = y + dirs[i].second

            // 맵 밖 위치라면 continue
            if (dx !in map.indices || dy !in map[0].indices) continue

            // 장애물이라면 continue
            if (map[dx][dy] == '*') continue

            //종/횡 동일한 라인이라면 continue
            if (abs(dir - i) == 2) continue

            // 방향을 꺾어야 한다면 1 증가
            val nextMirror = if (dir == i) move else move + 1

            //더 많은 거울을 배치하게 된다면 continue
            if (nextMirror > visited[dx][dy])

            //방문 값 추가
            visited[dx][dy] = nextMirror


            queue.offer(Node(dx,dy, i, nextMirror))
        }
    }

    return -1
}

data class Node(val x: Int, val y: Int, val dir: Int, val move: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return this.move - other.move
    }
}
