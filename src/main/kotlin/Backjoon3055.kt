import java.util.*

//탈출
//https://www.acmicpc.net/problem/3055

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val (r, c) = readln().split(" ").map { it.toInt() }.toIntArray()
    val map = Array(r) { CharArray(c) }

    var start: Pair<Int, Int> = Pair(0, 0) // 고슴도치의 위치
    val waters: Queue<Pair<Int, Int>> = LinkedList() // 물의 위치

    //맵 정보 추가
    repeat(r) { i ->
        map[i] = readln().toCharArray()

        repeat(c) { j ->
            val ch = map[i][j]

            when (ch) {
                'S' -> {
                    map[i][j] = '.'
                    start = Pair(i, j)
                }

                '*' -> waters.add(Pair(i, j))
            }
        }
    }

    println(bfs(map, waters, start))
}

fun bfs(map: Array<CharArray>, waters: Queue<Pair<Int, Int>>, start: Pair<Int, Int>): Any {
    //인접한 4면 이동
    val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

    //x좌표, y좌표, 비용
    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
    queue.add(Triple(start.first, start.second, 0))

    val visited = Array(map.size) { BooleanArray(map[0].size) }
    visited[start.first][start.second] = true

    while (!queue.isEmpty()) {

        //물을 확장 시킨다
        //확장 예정된 위치에는 이동시킬 수 없기 때문에 물을 먼저 확장 한다
        repeat(waters.size) {
            val water = waters.poll()

            for (dir in dirs) {
                val dx = water.first + dir.first
                val dy = water.second + dir.second

                //맵 밖이라면 continue
                if (dx !in map.indices || dy !in map[0].indices) continue

                //확장 가능한 칸이 아니라면 continue
                if (map[dx][dy] != '.') continue

                //물 확장 처리
                map[dx][dy] = '*'

                //목록 추가
                waters.add(Pair(dx, dy))
            }
        }

        //고슴도치를 이동시킨다
        repeat(queue.size) {
            val current = queue.poll()

            for (dir in dirs) {
                val dx = current.first + dir.first
                val dy = current.second + dir.second

                //맵 밖이라면 continue
                if (dx !in map.indices || dy !in map[0].indices) continue

                //동굴을 찾았다면 결과 return
                if (map[dx][dy] == 'D') return current.third + 1

                //이미 이동했던 위치라면 continue
                if (visited[dx][dy]) continue

                //빈 공간이 아니라면 continue
                if (map[dx][dy] != '.') continue

                //방문 처리
                visited[dx][dy] = true

                //큐에 고슴도치 삽입
                queue.add(Triple(dx, dy, current.third + 1))
            }
        }
    }

    return "KAKTUS"
}
