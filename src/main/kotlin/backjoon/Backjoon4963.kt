package backjoon

import java.util.*

//섬의 개수
//https://www.acmicpc.net/problem/4963
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    while (true) {
        //지도 사이즈를 얻어온다
        val (w, h) = readln().split(" ").map { it.toInt() }

        //사이즈가 0이라면 동작을 종료한다
        if (w == 0 && h == 0) return@with

        //맵 정보를 입력받는다
        val map = Array(h) { IntArray(w) }
        repeat(h) { i ->
            map[i] = readln().split(" ").map { it.toInt() }.toIntArray()
        }

        //맵 탐색
        var island = 0
        repeat(h) { i ->
            repeat(w) { j ->
                //바다라면 되돌린다
                if (map[i][j] == 0) return@repeat

                //섬을 찾았다
                island++

                //인접한 섬들을 모두 지운다
                bfs(map, i, j)
            }
        }
        println(island)
    }
}

//섬의 연결부분을 찾아 지운다
private fun bfs(map: Array<IntArray>, startW: Int, startH: Int) {
    //8방향 인접 이동
    val dx = intArrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
    val dy = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)

    //시작지점 방문처리
    map[startW][startH] = 0

    //큐에 위치 삽입
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(Pair(startW, startH))

    while (queue.isNotEmpty()) {
        //큐에서 위치정보를 가져온다
        val (w,h) = queue.poll()

        repeat(dx.size) { pos ->
            val moveX = w + dx[pos]
            val moveY = h + dy[pos]

            //이동할 위치가 맵 밖이라면 취소
            if (moveX < 0 || moveY < 0 || moveX >= map.size || moveY >= map[0].size) return@repeat

            //방문한 or 바다라면 continue
            if (map[moveX][moveY] == 0) return@repeat

            //방문 처리
            map[moveX][moveY] = 0

            //큐에 이동할 위치 정보 추가
            queue.offer(Pair(moveX, moveY))
        }
    }
}
