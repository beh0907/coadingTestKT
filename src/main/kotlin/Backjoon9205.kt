import java.util.*
import kotlin.math.abs

//맥주 마시면서 걸어가기
//https://www.acmicpc.net/problem/9205
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val cases = readln().toInt()

    repeat(cases) { case ->
        val n = readln().toInt()

        //전체 위치 정보를 저장한다
        val locations = ArrayList<Pair<Int, Int>>()

        //상근이의 위치를 첫 시작지점에 추가한다
        val (startX, startY) = readln().trim().split(" ").map { it.toInt() }.toIntArray()
        locations.add(Pair(startX, startY))

        //편의점의 위치를 추가한다
        repeat(n) { i ->
            val (storeX, storeY) = readln().trim().split(" ").map { it.toInt() }.toIntArray()
            locations.add(Pair(storeX, storeY))
        }

        //마지막 락 페스티벌 위치를 추가한다
        val (endX, endY) = readln().trim().split(" ").map { it.toInt() }.toIntArray()
        locations.add(Pair(endX, endY))

        //최종 결과를 출력
        println(bfs(locations))
    }
}

fun bfs(locations: ArrayList<Pair<Int, Int>>): String {
    //큐에 상근이의 위치를 추가한다
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(locations[0])

    //상근이의 위치를 방문 처리 한다
    val visited = BooleanArray(locations.size)
    visited[0] = true

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        locations.forEachIndexed { index, next ->
            //방문 이력이 있다면 continue
            if (visited[index]) return@forEachIndexed

            //편의점을 맥주 한박스로 이동할 수 없다면 continue
            if (abs(current.first - next.first) + abs(current.second - next.second) > 1000) return@forEachIndexed

            //최종 목적지로 이동할 수 있다면 종료
            if (index == locations.size - 1) return "happy"

            //방문 처리
            visited[index] = true

            //큐에 다음 이동할 위치 저장
            queue.offer(next)
        }
    }

    return "sad"
}
