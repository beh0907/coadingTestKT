package backjoon

import java.util.*

//Puyo Puyo
//https://www.acmicpc.net/problem/11559

private val map = Array(12) { CharArray(6) }

private var result = 0

private val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

private var isFlag = true

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {

    //맵 정보를 읽는다
    repeat(12) { i ->
        map[i] = readln().toCharArray()
    }


    while (true) {
        isFlag = false

        //모든 블록을 확인해 블록을 폭파시킨다
        repeat(12) { i ->
            repeat(6) { j ->
                //빈공간이라면 처리하지 않는다
                if (map[i][j] == '.') return@repeat

                bfs(i, j, map[i][j])
            }
        }

        //폭발 시키지 못한다면 종료
        if (!isFlag) break

        //블록 폭파가 완료되었다면 빈공간을 채워 내린다
        moveFloor()

        //반응한 횟수를 증가시킨다
        result++
    }

    println(result)
}

//블록을 처리한다
private fun bfs(x: Int, y: Int, color: Char) {
    //방문 처리
    val visited = Array(12) { BooleanArray(6) }
    visited[x][y] = true

    //동일한 블록 정보 목록
    val blocks = ArrayList<Pair<Int, Int>>()
    blocks.add(Pair(x, y))

    //큐 정보 삽입
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(Pair(x, y))

    while (queue.isNotEmpty()) {
        val pos = queue.poll()

        for (dir in dirs) {
            val dx = pos.first + dir.first
            val dy = pos.second + dir.second

            //맵 밖이라면 continue
            if (dx !in map.indices || dy !in map[0].indices) continue

            //방문 이력이 있다면 continue
            if (visited[dx][dy]) continue

            //동일한 색상의 블록이 아니라면 continue
            if (map[dx][dy] != color) continue

            //방문 처리
            visited[dx][dy] = true

            //블록 정보 추가
            blocks.add(Pair(dx, dy))
            queue.offer(Pair(dx, dy))
        }
    }

    //4개 이상 인접했다면 폭발
    if (blocks.size >= 4) {
        for (block in blocks) {
            map[block.first][block.second] = '.'
        }

        isFlag = true
    }
}

//블록을 바닥의 빈칸으로 이동시킨다
private fun moveFloor() {
    for (j in 0..5) {
        for (i in 11 downTo 0) {
            //블록이 아니라면 continue
            if (map[i][j] == '.') continue

            var dx = i + 1
            while (true) {
                //맵 밖이라면 break
                if (dx !in map.indices) break

                //이미 블록이 있다면 break
                if (map[dx][j] != '.') break

                //블록 이동
                map[dx][j] = map[dx - 1][j]
                map[dx - 1][j] = '.'

                dx++
            }
        }
    }
}
