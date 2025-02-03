package backjoon

import java.util.PriorityQueue
import kotlin.math.min

//지름길
//https://www.acmicpc.net/problem/1446
fun main() = with(System.`in`.bufferedReader()) {
    val (N, D) = readLine().split(' ').map(String::toInt)
    val queue = PriorityQueue<Node1446>()
    val array = IntArray(D + 1) { it }

    repeat(N) {
        val (start, end, weight) = readLine().split(' ').map(String::toInt)

        //목적지를 초과하면 패스
        if (end > D) return@repeat

        //지름길이 더 멀다면 패스
        if(weight >= end - start) return@repeat

        queue.add(Node1446(start, end, weight))
    }

    while (queue.isNotEmpty()) {
        val node = queue.poll()

        //현재 거리보다 더 짧은 길이라면
        if (array[node.end] > array[node.start] + node.weight) {
            array[node.end] = array[node.start] + node.weight
            for (i in node.end + 1..D) {
                array[i] = min(array[i], array[i - 1] + 1)
            }
        }
    }

    println(array[D])
}

data class Node1446(
    val start: Int,
    val end: Int,
    val weight: Int
) : Comparable<Node1446> {
    override fun compareTo(other: Node1446): Int {
        return end - other.end
    }
}
