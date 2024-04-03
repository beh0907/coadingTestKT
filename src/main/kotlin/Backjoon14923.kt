import java.util.*

//미로 탈출
//https://www.acmicpc.net/problem/14923
private val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val (x, y) = readln().split(" ").map { it.toInt() }.toIntArray()
    val (startX, startY) = readln().split(" ").map { it.toInt() - 1 }.toIntArray()
    val (endX, endY) = readln().split(" ").map { it.toInt() - 1 }.toIntArray()
    val map = Array(x) { IntArray(y) }

    //맵 정보 삽입
    repeat(x) { map[it] = readln().split(" ").map { it.toInt() }.toIntArray() }

    //탐색 결과 출력
    println(bfs(startX, startY, endX, endY, map))
}

//블록을 처리한다
private fun bfs(startX: Int, startY: Int, endX: Int, endY: Int, map: Array<IntArray>): Int {
    //방문 처리
    val visited = Array(map.size) { Array(map[0].size) { BooleanArray(2) } }
    visited[startX][startY][0] = true

    //큐 정보 삽입
    val queue: Queue<Point14923> = LinkedList()
    queue.offer(Point14923(startX, startY, 0, 0))

    while (queue.isNotEmpty()) {
        val point = queue.poll()

        for (dir in dirs) {
            val dx = point.x + dir.first
            val dy = point.y + dir.second
            val distance = point.distance
            val breakWall = point.breakWall

            //맵 밖이라면 continue
            if (dx !in map.indices || dy !in map[0].indices) continue

            //목표 좌표에 도달했다면 결과 리턴
            if (dx == endX && dy == endY) return distance + 1

            //방문한 이력이 없는 빈 공간이라면
            if (map[dx][dy] == 0 && !visited[dx][dy][breakWall]) {
                visited[dx][dy][breakWall] = true

                queue.offer(Point14923(dx, dy, distance + 1, breakWall))
            }
            //벽이 있지만 돌파기능을 아직 사용한 적이 없다면
            else if (map[dx][dy] == 1 && breakWall == 0) {
                visited[dx][dy][1] = true

                queue.offer(Point14923(dx, dy, distance + 1, 1))
            }
        }
    }

    return -1
}

data class Point14923(val x: Int, val y: Int, val distance: Int, val breakWall: Int)
