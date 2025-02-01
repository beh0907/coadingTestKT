package backjoon

import java.util.PriorityQueue
import java.util.StringTokenizer

//N번째 큰 수
//https://www.acmicpc.net/problem/2075
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val queue = PriorityQueue<Int> { o1, o2 -> o2 - o1 }

    repeat(N) { i ->
        val tokens = StringTokenizer(readLine())

        repeat(N) {
            queue.add(tokens.nextToken().toInt())
        }
    }

    repeat(N - 1) {
        queue.poll()
    }

    println(queue.peek())
}
