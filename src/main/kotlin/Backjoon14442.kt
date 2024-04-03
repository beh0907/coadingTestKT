import java.util.*

//벽 부수고 이동하기 2
//https://www.acmicpc.net/problem/14442
private val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val (x, y, wall) = readln().split(" ").map { it.toInt() }.toIntArray()

    val map = Array(x) { IntArray(y) }

    //맵 정보 삽입
    repeat(x) { i ->
        map[i] = readln().toCharArray().map { it - '0' }.toIntArray()
    }

    //탐색 결과 출력
    println(bfs(wall, map))
}

//블록을 처리한다
private fun bfs(wall: Int, map: Array<IntArray>): Int {
    //시작이 곧 끝 위치라면 바로 종료
    if (map.size == 1 && map[0].size == 1) return 1

    //방문 처리
    val visited = Array(map.size) { Array(map[0].size) { BooleanArray(wall + 1) } }
    visited[0][0][0] = true

    //큐 정보 삽입
    val queue: Queue<Point14442> = LinkedList()
    queue.offer(Point14442(0, 0, 1, 0))

    while (queue.isNotEmpty()) {
        val point = queue.poll()

        for (dir in dirs) {
            val dx = point.x + dir.first
            val dy = point.y + dir.second
            val distance = point.distance

            //맵 밖이라면 continue
            if (dx !in map.indices || dy !in map[0].indices) continue

            //목표 좌표에 도달했다면 결과 리턴
            if (dx == map.size - 1 && dy == map[0].size - 1) return distance + 1

            //벽을 만났다면 부수기 위해 횟수를 증가
            val breakWall = if (map[dx][dy] == 1) point.breakWall + 1 else point.breakWall

            //기준치 이상으로 부수게 된다면 continue
            if (breakWall > wall) continue

            //이미 방문했다면 continue
            if (visited[dx][dy][breakWall]) continue

            //방문 처리
            visited[dx][dy][breakWall] = true

            //큐 삽입
            queue.offer(Point14442(dx, dy, distance + 1, breakWall))
        }
    }

    return -1
}

data class Point14442(val x: Int, val y: Int, val distance: Int, val breakWall: Int)
