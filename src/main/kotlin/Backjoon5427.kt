import java.util.*

//불
//https://www.acmicpc.net/problem/5427
fun main() = with(System.`in`.bufferedReader()) {
    repeat(readln().toInt()) {
        val (w, h) = readln().split(" ").map { it.toInt() }.toIntArray()
        val map = Array(h) { CharArray(w) }

        var start: Pair<Int, Int> = Pair(0, 0) // 상근이의 시작 위치
        val fires: Queue<Pair<Int, Int>> = LinkedList() // 불의 위치

        //맵 정보 추가
        repeat(h) { i ->
            map[i] = readln().toCharArray()

            repeat(w) { j ->
                val ch = map[i][j]

                when (ch) {
                    '@' -> {
                        map[i][j] = '.'
                        start = Pair(i, j)
                    }

                    '*' -> fires.add(Pair(i, j))
                }
            }
        }

        println(bfs(start, map, fires))
    }
}


private fun bfs(start: Pair<Int, Int>, map: Array<CharArray>, fires: Queue<Pair<Int, Int>>): Any {
    //인접한 4면 이동
    val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

    //x좌표, y좌표, 비용
    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
    queue.add(Triple(start.first, start.second, 0))

    //시작 위치 방문 처리
    val visited = Array(map.size) { BooleanArray(map[0].size) }
    visited[start.first][start.second] = true

    while (!queue.isEmpty()) {
        //불을 확장 시킨다
        //확장 예정된 위치에는 이동시킬 수 없기 때문에 불을 먼저 확장 한다
        repeat(fires.size) {
            val fire = fires.poll()

            for (dir in dirs) {
                val dx = fire.first + dir.first
                val dy = fire.second + dir.second

                //맵 밖이라면 continue
                if (dx !in map.indices || dy !in map[0].indices) continue

                //확장 가능한 칸이 아니라면 continue
                if (map[dx][dy] != '.') continue

                //불 확장 처리
                map[dx][dy] = '*'

                //목록 추가
                fires.add(Pair(dx, dy))
            }
        }

        //상근이를 이동시킨다
        repeat(queue.size) {
            val current = queue.poll()

            //맵의 모서리 영역에 도달했다면 탈출 할 수 있으므로 결과 return
            if (current.first == 0 || current.first == map.size - 1 || current.second == 0 || current.second == map[0].size - 1) return current.third + 1

            for (dir in dirs) {
                val dx = current.first + dir.first
                val dy = current.second + dir.second

                //맵 밖이라면 continue
                if (dx !in map.indices || dy !in map[0].indices) continue

                //이미 이동했던 위치라면 continue
                if (visited[dx][dy]) continue

                //빈 공간이 아니라면 continue
                if (map[dx][dy] != '.') continue

                //방문 처리
                visited[dx][dy] = true

                //큐에 상근이 위치 삽입
                queue.add(Triple(dx, dy, current.third + 1))
            }
        }
    }

    return "IMPOSSIBLE"
}
