package backjoon

import java.util.*

//Cheese
//https://www.acmicpc.net/problem/5558

private val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

private lateinit var map: Array<IntArray>

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val (x, y, cheeses) = readln().trim().split(" ").map { it.toInt() }
    map = Array(x) { IntArray(y) }

    var (startX, startY) = Pair(0, 0)

    repeat(x) { i ->

        readln().trim().toCharArray().onEachIndexed { j, ch ->

            when (ch) {
                'S' -> { //시작 지점
                    startX = i
                    startY = j

                    //시작 지점도 마찬가지로 빈공간 설정
                    map[i][j] = 0
                }

                '.' -> { // 빈공간
                    map[i][j] = 0
                }

                'X' -> { // 장애물
                    map[i][j] = -1
                }

                else -> { //치즈
                    map[i][j] = ch - '0'
                }
            }
        }
    }

    println(bfs(startX, startY, map, cheeses))
}

private fun bfs(startX: Int, startY: Int, map: Array<IntArray>, cheeses: Int): Int {
    //시작지점 방문 처리
    var visited = Array(map.size) { BooleanArray(map[0].size) }
    visited[startX][startY]

    //큐 삽입
    val queue: Queue<Rat> = LinkedList()
    queue.offer(Rat(startX, startY, 1, 0))

    while (queue.isNotEmpty()) {
        val rat = queue.poll()

        val hp = rat.hp

        //현재 체력이 치즈 개수보다 많다면 다 먹었다는 의미이므로 결과 리턴
        if (hp > cheeses) return rat.move

        for (dir in dirs) {
            val dx = rat.x + dir.first
            val dy = rat.y + dir.second

            //맵 밖 위치라면 continue
            if (dx !in map.indices || dy !in map[0].indices) continue

            //장애물이라면 continue
            if (map[dx][dy] == -1) continue

            //방문한 이력이 있다면 continue
            if (visited[dx][dy]) continue

            //이동할 칸이 치즈이며, 먹을 수 있는 상태라면
            if (map[dx][dy] in 1..hp) {
                //먹었다면 해당 위치에서 재시작하므로 큐와 방문 값을 초기화한다
                queue.clear()
                queue.offer(Rat(dx, dy, hp + 1, rat.move + 1))
                visited = Array(map.size) { BooleanArray(map[0].size) }

                //치즈를 먹었기 때문에 삭제처리한다
                map[dx][dy] = 0

                //새로 시작하기 때문에 방향 배열을 종료시킨다
                break
            } else { //빈 공간이거나 먹지 못하는 치즈라면
                //방문 처리
                visited[dx][dy] = true

                //이동 큐 추가
                queue.offer(Rat(dx,dy,hp, rat.move + 1))
            }

        }
    }

    return 0
}


data class Rat(val x: Int, val y: Int, val hp: Int, val move: Int)
