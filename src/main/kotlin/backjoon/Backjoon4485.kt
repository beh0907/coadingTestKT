package backjoon

import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    var cnt = 0

    while (true) {
        val N = readLine().toInt()
        cnt++

        //0이면 종료
        if (N == 0) break

        //배열 값 할당
        val map = Array(N) { IntArray(N) }
        repeat(N) {
            map[it] = readLine().split(" ").map(String::toInt).toIntArray()
        }

        bw.write("Problem ${cnt}: ${dijkstra4485(map, N)}\n")
    }

    bw.flush()
    bw.close()
}

fun dijkstra4485(map: Array<IntArray>, N: Int): Int {
    val dirX = listOf(0, 0, 1, -1)
    val dirY = listOf(1, -1, 0, 0)

    //(x,y,weight) 큐
    val queue = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
    queue.add(Triple(0, 0, map[0][0]))

    //결과 표
    val result = Array(N) { IntArray(N) { Int.MAX_VALUE } }
    result[0][0] = map[0][0]

    while (queue.isNotEmpty()) {
        val (x, y, weight) = queue.poll()

        for (i in 0..3) {
            val newX = x + dirX[i]
            val newY = y + dirY[i]

            //맵을 벗어나는 위치를 참조한다면 continue
            if (newX !in map[0].indices || newY !in map.indices) continue

            //기존에 저장된 가중치보다 더 작은 값을 가진다면 저장하고 큐에 추가
            val newWeight = weight + map[newX][newY]
            if (result[newX][newY] > newWeight) {
                result[newX][newY] =  newWeight
                queue.add(Triple(newX, newY, newWeight))
            }
        }
    }

    //최종 목적지 결과값 리턴
    return result[N - 1][N - 1]
}
